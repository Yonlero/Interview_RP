package com.rp.interview_rp.model.utils;

import com.rp.interview_rp.dtos.ClientDTO;
import com.rp.interview_rp.dtos.EquipmentDTO;
import com.rp.interview_rp.dtos.OrderServiceDTO;
import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.entities.EquipmentEntity;
import com.rp.interview_rp.model.entities.OrderServiceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class ConvertObjects {
    static ModelMapper modelMapper;

    public ConvertObjects(ModelMapper modelMapper) {
        ConvertObjects.modelMapper = modelMapper;
    }

    private static Set<EquipmentEntity> convertColletionOfEquipmentDtoToEntity(Set<EquipmentDTO> equipmentDTOs) {
        Objects.requireNonNull(equipmentDTOs, "List of equipments is null");
        Set<EquipmentEntity> equipmentEntities = new HashSet<>();
        equipmentDTOs.forEach(
                equipment -> {
                    EquipmentEntity equipmentEntity = EquipmentEntity.builder()
                            .id(equipment.getId())
                            .brand(equipment.getBrand())
                            .type(equipment.getType())
                            .createdAt(equipment.getCreatedAt())
                            .updatedAt(equipment.getUpdatedAt())
                            .build();
                    equipmentEntities.add(equipmentEntity);
                });
        return equipmentEntities;
    }

    private static Set<EquipmentDTO> convertColletionOfEquipmentEntityToDTO(Set<EquipmentEntity> equipmentEntities) {
        Objects.requireNonNull(equipmentEntities, "List of equipments is null");
        Set<EquipmentDTO> equipmentDTOs = new HashSet<>();
        equipmentEntities.forEach(
                equipment -> {
                    EquipmentDTO equipmentDTO = EquipmentDTO.builder()
                            .id(equipment.getId())
                            .brand(equipment.getBrand())
                            .type(equipment.getType())
                            .createdAt(equipment.getCreatedAt())
                            .updatedAt(equipment.getUpdatedAt())
                            .build();
                    equipmentDTOs.add(equipmentDTO);
                });
        return equipmentDTOs;
    }

    public static ClientDTO convertClientEntityToDto(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientDTO.class);
    }

    public static ClientEntity convertClientDtoToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }

    public static OrderServiceDTO convertOrderEntityToDto(OrderServiceEntity orderServiceEntity) {
        return OrderServiceDTO.builder()
                .id(orderServiceEntity.getId())
                .equipments(convertColletionOfEquipmentEntityToDTO(orderServiceEntity.getEquipments()))
                .client(convertClientEntityToDto(orderServiceEntity.getClient()))
                .status(orderServiceEntity.getStatus())
                .responsible(orderServiceEntity.getResponsible())
                .order_problems(orderServiceEntity.getOrder_problems())
                .problem_description(orderServiceEntity.getProblem_description())
                .createdAt(orderServiceEntity.getCreatedAt())
                .updatedAt(orderServiceEntity.getUpdatedAt())
                .build();
    }

    public static OrderServiceEntity convertOrderDtoToEntity(OrderServiceDTO orderServiceDTO) {
        return OrderServiceEntity.builder()
                .id(orderServiceDTO.getId())
                .equipments(convertColletionOfEquipmentDtoToEntity(orderServiceDTO.getEquipments()))
                .client(convertClientDtoToEntity(orderServiceDTO.getClient()))
                .status(orderServiceDTO.getStatus())
                .responsible(orderServiceDTO.getResponsible())
                .order_problems(orderServiceDTO.getOrder_problems())
                .problem_description(orderServiceDTO.getProblem_description())
                .createdAt(orderServiceDTO.getCreatedAt())
                .updatedAt(orderServiceDTO.getUpdatedAt())
                .build();
    }
}