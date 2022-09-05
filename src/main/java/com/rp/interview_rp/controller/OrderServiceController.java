package com.rp.interview_rp.controller;

import com.rp.interview_rp.controller.interfaces.IController;
import com.rp.interview_rp.controller.interfaces.IOrderServiceController;
import com.rp.interview_rp.dtos.OrderServiceDTO;
import com.rp.interview_rp.model.exceptions.NullEntityException;
import com.rp.interview_rp.model.services.OrderServiceImp;
import com.rp.interview_rp.model.services.interfaces.IOrderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static com.rp.interview_rp.model.enums.OrderStatus.PENDING;

@Slf4j
@RestController
@RequestMapping("/rp/orders")
public class OrderServiceController implements IController, IOrderServiceController {
    private final IOrderService service;

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
    @GetMapping("/pendents")
    @ApiResponse(responseCode = "200", description = "Return a page with N orders")
    public ResponseEntity<Page<OrderServiceDTO>> getPendentOrderService(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        log.info("Getting clients - OrderController");
        return ResponseEntity.ok(service.findPendentOrderService(PageRequest.of(page, size)));
    }

    @Override
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create a new order and return it"),
            @ApiResponse(responseCode = "204", description = "Invalid Body")
    })
    public ResponseEntity<OrderServiceDTO> postCreateNewOrderService(@Valid @RequestBody(required = true) OrderServiceDTO orderServiceDTO) {
        log.info("Creating a new order - OrderController");
        orderServiceDTO.setStatus(PENDING);
        if (orderServiceDTO.getClient() == null || orderServiceDTO.getResponsible() == null) {
            throw new NullEntityException("Check your body fields, Client ID and Responsible can't be null");
        }
        OrderServiceDTO newOrder = service.createNewOrderService(orderServiceDTO);
        return ResponseEntity.created(buildUri(newOrder)).body(newOrder);
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a OrderServiceDTO"),
            @ApiResponse(responseCode = "404", description = "Not Found Id"),
            @ApiResponse(responseCode = "400", description = "Invalid param request")
    })
    public ResponseEntity<OrderServiceDTO> getConsultOrderServiceById(@Valid @PathVariable String id) {
        return ResponseEntity.ok(service.findConsultOrderServiceById(UUID.fromString(id)));
    }

    private URI buildUri(OrderServiceDTO orderServiceDTO) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderServiceDTO.getId())
                .toUri();
    }
}