package com.rp.interview_rp.model.entities;

import com.rp.interview_rp.model.entities.interfaces.IEntity;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity implements IEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Cellphone can't be null")
    private String cellphone;
    @NotNull(message = "Email can't be null")
    @Column(unique = true)
    @Email
    private String email;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = {PERSIST, REFRESH, MERGE})
    private Set<OrderServiceEntity> orders = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}