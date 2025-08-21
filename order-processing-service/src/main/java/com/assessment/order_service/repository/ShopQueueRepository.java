package com.assessment.order_service.repository;

import com.assessment.order_service.model.Customer;
import com.assessment.order_service.model.Shop;
import com.assessment.order_service.model.ShopQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopQueueRepository extends JpaRepository<ShopQueue, Long> {
    List<ShopQueue> findByShopAndStatusOrderByQueueNumberAsc(Shop shop, String status);
    List<ShopQueue> findByCustomer(Customer customer);
    List<ShopQueue> findByShop(Shop shop);
    Optional<ShopQueue> findByQueueNumber(int queueNumber);
    Optional<ShopQueue> findByShopAndCustomerAndStatusIn(Shop shop, Customer customer, List<String> statuses);

}
