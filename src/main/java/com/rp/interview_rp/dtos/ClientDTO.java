package com.rp.interview_rp.dtos;

import com.rp.interview_rp.model.entities.AddressEntity;
import com.rp.interview_rp.model.entities.EquipmentEntity;
import com.rp.interview_rp.model.entities.OrderService;
import com.rp.interview_rp.model.entities.interfaces.IEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements IEntity {
    private UUID id;
    private String name;
    private String cellphone;
    private String email;
    private Set<EquipmentEntity> equipments;
    private Set<OrderService> orders;
    private AddressEntity address;
}