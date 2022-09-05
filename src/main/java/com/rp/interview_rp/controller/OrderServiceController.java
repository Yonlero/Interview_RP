package com.rp.interview_rp.controller;

import com.rp.interview_rp.controller.interfaces.IController;
import com.rp.interview_rp.controller.interfaces.IOrderServiceController;
import com.rp.interview_rp.dtos.OrderServiceDTO;
import com.rp.interview_rp.model.services.OrderServiceImp;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.rp.interview_rp.model.enums.OrderStatus.PENDING;

@Slf4j
@RestController
@RequestMapping("/rp/orders")
public class OrderServiceController implements IController, IOrderServiceController {
    private final OrderServiceImp service;

    public OrderServiceController(OrderServiceImp service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Return a page with N orders")
    public ResponseEntity<Page<OrderServiceDTO>> getAllOrderService(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        log.info("Getting clients - OrderController");
        return ResponseEntity.ok(service.findAllOrderService(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<Page<OrderServiceDTO>> getPendentOrderService(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        return null;
    }

    @Override
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create a new order and return it"),
            @ApiResponse(responseCode = "204", description = "Invalid Body")
    })
    public ResponseEntity<OrderServiceDTO> postCreateNewOrderService(@RequestBody(required = true) OrderServiceDTO orderServiceDTO) {
        log.info("Creating a new order - OrderController");
        orderServiceDTO.setStatus(PENDING);
        OrderServiceDTO newOrder = service.createNewOrderService(orderServiceDTO);
        return ResponseEntity.created(buildUri(newOrder)).body(newOrder);
    }

    @Override
    public ResponseEntity<OrderServiceDTO> getConsultOrderServiceById(String uuid) {
        return null;
    }

    private URI buildUri(OrderServiceDTO orderServiceDTO) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderServiceDTO.getId())
                .toUri();
    }
}