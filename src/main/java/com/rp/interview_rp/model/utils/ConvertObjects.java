package com.rp.interview_rp.model.utils;

import com.rp.interview_rp.dtos.ClientDTO;
import com.rp.interview_rp.dtos.OrderServiceDTO;
import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.entities.OrderServiceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvertObjects {
    static ModelMapper modelMapper;

    public ConvertObjects(ModelMapper modelMapper) {
        ConvertObjects.modelMapper = modelMapper;
    }

    public static ClientDTO convertClientEntityToDto(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientDTO.class);
    }

    public static ClientEntity convertClientDtoToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }

    public static OrderServiceDTO convertOrderEntityToDto(OrderServiceEntity orderServiceEntity) {
        return modelMapper.map(orderServiceEntity, OrderServiceDTO.class);
    }

    public static OrderServiceEntity convertOrderDtoToEntity(OrderServiceDTO orderServiceDTO) {
        return modelMapper.map(orderServiceDTO, OrderServiceEntity.class);
    }
}