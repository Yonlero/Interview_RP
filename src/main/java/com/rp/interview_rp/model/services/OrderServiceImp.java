package com.rp.interview_rp.model.services;

import com.rp.interview_rp.dtos.OrderServiceDTO;
import com.rp.interview_rp.model.entities.OrderServiceEntity;
import com.rp.interview_rp.model.repositories.IOrderServiceRepository;
import com.rp.interview_rp.model.services.interfaces.IOrderService;
import com.rp.interview_rp.model.services.interfaces.IService;
import com.rp.interview_rp.model.utils.ConvertObjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

import static com.rp.interview_rp.model.enums.OrderStatus.PENDING;
import static com.rp.interview_rp.model.utils.ConvertObjects.convertOrderDtoToEntity;
import static com.rp.interview_rp.model.utils.ConvertObjects.convertOrderEntityToDto;

@Service
public record OrderServiceImp(IOrderServiceRepository repository) implements IService, IOrderService {

    @Override
    public Page<OrderServiceDTO> findAllOrderService(Pageable pageable) {
        List<OrderServiceDTO> outputList = repository.findAll(pageable).stream()
                .map(ConvertObjects::convertOrderEntityToDto).toList();
        return new PageImpl<>(outputList);
    }

    @Override
    public Page<OrderServiceDTO> findPendentOrderService(Pageable pageable) {
        List<OrderServiceDTO> outputList = repository.findAllByStatusIs(pageable, PENDING).stream()
                .map(ConvertObjects::convertOrderEntityToDto).toList();
        return new PageImpl<>(outputList);
    }

    @Override
    public OrderServiceDTO findConsultOrderServiceById(UUID id) {
        return convertOrderEntityToDto(repository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("Id not found");
                })
        );
    }

    @Override
    public OrderServiceDTO createNewOrderService(OrderServiceDTO newOrder) {
        OrderServiceEntity newOrderEntity = convertOrderDtoToEntity(newOrder);
        return convertOrderEntityToDto(repository.save(newOrderEntity));
    }
}