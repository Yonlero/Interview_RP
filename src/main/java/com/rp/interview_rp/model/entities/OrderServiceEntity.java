package com.rp.interview_rp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rp.interview_rp.model.entities.interfaces.IEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "tb_order_service")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "client", allowSetters = true)
public class OrderServiceEntity implements IEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    @ManyToMany(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinTable(name = "order_equipments",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "equipment_id")}
    )
    private Set<EquipmentEntity> equipments;
    private OrderStatus status;
    private String description;
    private String order_problems;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}