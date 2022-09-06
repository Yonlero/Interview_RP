package com.rp.interview_rp.model.services.interfaces;

import com.rp.interview_rp.dtos.OrderServiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IOrderService {
    Page<OrderServiceDTO> findAllOrderService(Pageable pageable);

    Page<OrderServiceDTO> findPendentOrderService(Pageable pageable);

    OrderServiceDTO findConsultOrderServiceById(UUID id);

    OrderServiceDTO createNewOrderService(OrderServiceDTO newOrder);

    OrderServiceDTO updateOrderService(OrderServiceDTO orderServiceDTO);
}