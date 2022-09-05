package com.rp.interview_rp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rp.interview_rp.model.entities.interfaces.IEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
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
@JsonIgnoreProperties(value = {"client"}, allowSetters = true)
public class OrderServiceEntity implements IEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    @ManyToMany(cascade = CascadeType.ALL, fetch = EAGER)
    @JoinTable(name = "order_equipments", joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "equipment_id")})
    private Set<EquipmentEntity> equipments;
    private OrderStatus status;
    @NotBlank(message = "Responsible filed mustn't be null or blank")
    private String responsible;
    private String problem_description;
    private String solution_description;
    private String order_problems;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderServiceEntity that = (OrderServiceEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}