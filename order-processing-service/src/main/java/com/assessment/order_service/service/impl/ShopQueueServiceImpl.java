package com.assessment.order_service.service.impl;

import com.assessment.order_service.dto.ShopQueueDTO;
import com.assessment.order_service.exception.ResourceNotFoundException;
import com.assessment.order_service.mapper.ShopQueueMapper;
import com.assessment.order_service.model.Customer;
import com.assessment.order_service.model.Shop;
import com.assessment.order_service.model.ShopQueue;
import com.assessment.order_service.repository.CustomerRepository;
import com.assessment.order_service.repository.ShopQueueRepository;
import com.assessment.order_service.repository.ShopRepository;
import com.assessment.order_service.service.ShopQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopQueueServiceImpl implements ShopQueueService {

    private final ShopQueueRepository shopQueueRepository;
    private final ShopQueueMapper shopQueueMapper;
    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<ShopQueueDTO> getQueueByShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId).get();
        List<ShopQueue> shopQueues = shopQueueRepository.findByShopAndStatusOrderByQueueNumberAsc(shop, "WAITING");
        return shopQueues.stream().map(shopQueueMapper::toDto).toList();
    }

    @Override
    public List<ShopQueueDTO> getQueueByCustomer(Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        List<ShopQueue> shopQueues = shopQueueRepository.findByCustomer(customer);
        return shopQueues.stream().map(shopQueueMapper::toDto).toList();

    }

    @Override
    public ShopQueueDTO createQueueEntry(Long shopId, Long customerId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found: " + shopId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        // ✅ Check if customer is already in the queue (WAITING or IN_PROGRESS)
        Optional<ShopQueue> existingQueue = shopQueueRepository
                .findByShopAndCustomerAndStatusIn(
                        shop,
                        customer,
                        List.of("WAITING", "IN_PROGRESS")
                );

        if (existingQueue.isPresent()) {
            return shopQueueMapper.toDto(existingQueue.get());
        }

        // ✅ Check if shop queue has reached its max
        List<ShopQueue> shopQueues = shopQueueRepository.findByShop(shop);
        if (activeQueueCount >= shop.getMaxQueueSize()) {
            throw new RuntimeException("Shop queue is full. Please wait before placing a new order.");
        }



        List<ShopQueue> completedQueueList = shopQueueRepository.findByShopAndStatusOrderByQueueNumberAsc(shop, "COMPLETED");

        int assignedQueueNumber;
        if (!completedQueueList.isEmpty()) {
            assignedQueueNumber = completedQueueList.get(0).getQueueNumber();

            shopQueueRepository.delete(completedQueueList.get(0));
        } else {
            List<ShopQueue> waitingQueue = shopQueueRepository.findByShopAndStatusOrderByQueueNumberAsc(shop, "WAITING");
            assignedQueueNumber = waitingQueue.size() + 1;
        }

        ShopQueue queue = ShopQueue.builder()
                .shop(shop)
                .customer(customer)
                .queueNumber(assignedQueueNumber)
                .status("WAITING")
                .build();

        ShopQueue savedQueue = shopQueueRepository.save(queue);
        return shopQueueMapper.toDto(savedQueue);
    }

    @Override
    public void updateQueueStatus(int queueNumber, String status) {
        ShopQueue queue = shopQueueRepository.findByQueueNumber(queueNumber)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        queue.setStatus(status);
        shopQueueRepository.save(queue);
    }

}
