package com.rp.interview_rp.controller.interfaces;

import com.rp.interview_rp.dtos.OrderServiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IOrderServiceController {
    ResponseEntity<Page<OrderServiceDTO>> getAllOrderService(Integer page, Integer size);

    ResponseEntity<Page<OrderServiceDTO>> getPendentOrderService(Integer page, Integer size);

    ResponseEntity<OrderServiceDTO> postCreateNewOrderService(OrderServiceDTO orderServiceDTO);

    ResponseEntity<OrderServiceDTO> getConsultOrderServiceById(String uuid);
}