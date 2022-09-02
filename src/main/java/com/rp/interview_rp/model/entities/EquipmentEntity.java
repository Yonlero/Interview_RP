package com.rp.interview_rp.model.entities;

import com.rp.interview_rp.model.entities.interfaces.IEntity;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "tb_equipment")
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentEntity implements IEntity {
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
    @NotNull(message = "Type can't be null")
    private String type;
    @NotNull(message = "Brand can't be null")
    private String brand;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderService order;
}