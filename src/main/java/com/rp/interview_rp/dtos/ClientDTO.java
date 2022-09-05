package com.rp.interview_rp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rp.interview_rp.model.entities.AddressEntity;
import com.rp.interview_rp.model.entities.EquipmentEntity;
import com.rp.interview_rp.model.entities.OrderServiceEntity;
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
public class ClientDTO {
    private UUID id;
    private String name;
    private String cellphone;
    private String email;
    private Set<EquipmentEntity> equipments;
    private Set<OrderServiceEntity> orders;
    private AddressEntity address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    private LocalDateTime updatedAt;
}