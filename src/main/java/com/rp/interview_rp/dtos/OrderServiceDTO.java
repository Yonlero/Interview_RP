package com.rp.interview_rp.dtos;

import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.entities.EquipmentEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceDTO {
    private UUID id;
    private ClientEntity client;
    private Set<EquipmentEntity> equipments;
    private OrderStatus status;
    private String description;
    private String order_problems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
