package com.rp.interview_rp.model.entities;

import com.rp.interview_rp.model.entities.interfaces.IEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_order_service")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderService implements IEntity {
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
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
    @OneToMany(mappedBy = "order")
    private Set<EquipmentEntity> equipments;
    private OrderStatus status;
    private String description;
    private String order_problems;
}