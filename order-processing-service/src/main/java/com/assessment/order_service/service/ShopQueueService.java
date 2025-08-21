package com.assessment.order_service.service;

import com.assessment.order_service.dto.ShopQueueDTO;

import java.util.List;

public interface ShopQueueService {
    List<ShopQueueDTO> getQueueByShop(Long shopId);
    List<ShopQueueDTO> getQueueByCustomer(Long customerId);
    ShopQueueDTO createQueueEntry(Long shopId, Long customerId);
     void updateQueueStatus(int queueNumber, String status);


}
