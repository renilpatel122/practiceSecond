package com.assessment.order_service.service.impl;

import com.assessment.order_service.dto.*;
import com.assessment.order_service.exception.ResourceNotFoundException;
import com.assessment.order_service.mapper.*;
import com.assessment.order_service.model.*;
import com.assessment.order_service.repository.OrderRepository;
import com.assessment.order_service.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final ShopService shopService;
    private final ShopMapper shopMapper;
    private final CustomerMapper customerMapper;
    private final ShopQueueService shopQueueService;
    private final MenuItemService menuItemService;
    private final InventoryItemService inventoryItemService;
    private final MenuItemMapper menuItemMapper;

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {

        Customer customer = customerMapper.toEntity(customerService.getCustomerById(orderDTO.getCustomerId()));
        Shop shop = shopMapper.toEntity(shopService.getShopById(orderDTO.getShopId()));

        Order order = toEntity(orderDTO, shop, customer);

        ShopQueueDTO queueEntry = shopQueueService.createQueueEntry(shop.getId(), customer.getId());
        order.setQueueNumber(queueEntry.getQueueNumber());
        order.setStatus(queueEntry.getStatus());
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDto(savedOrder);
    }

    private Order toEntity(OrderDTO orderDTO, Shop shop, Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setShop(shop);

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(orderItemDTO -> {
                    MenuItem menuItem = menuItemMapper.toEntity(
                            menuItemService.getMenuItemById(orderItemDTO.getMenuItemId()), shop);

                    InventoryItemDTO inventory = inventoryItemService.getInventoryItemByMenuItem(menuItem.getId());
                    if (inventory == null) {
                        throw new RuntimeException("Inventory not found for MenuItem ID: " + menuItem.getId());
                    }

                    if (inventory.getAvailableQuantity() < orderItemDTO.getQuantity()) {
                        throw new RuntimeException("Insufficient stock for MenuItem ID: " + menuItem.getId());
                    }

                    inventory.setAvailableQuantity(inventory.getAvailableQuantity() - orderItemDTO.getQuantity());
                    inventoryItemService.updateInventoryItem(inventory);

                    return OrderItemMapper.toEntity(orderItemDTO, order, menuItem);
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public List<OrderDTO> getOrdersByCustomer(Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return orderRepository.findByCustomer(customer)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByShop(Long shopId) {
        Shop shop = shopMapper.toEntity(shopService.getShopById(shopId));
        return orderRepository.findByShop(shop)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus("COMPLETED");
        orderRepository.save(order);

        shopQueueService.updateQueueStatus(order.getQueueNumber(), "COMPLETED");

        return orderMapper.toDto(order);
    }

}
