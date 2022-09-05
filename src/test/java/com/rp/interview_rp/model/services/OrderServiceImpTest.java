package com.rp.interview_rp.model.services;

import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.entities.OrderServiceEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import com.rp.interview_rp.model.repositories.IOrderServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImpTest {
    @Mock
    IOrderServiceRepository repository;

    @Test
    void findAllOrderService() {
        when(repository.findAll(PageRequest.of(0, 5))).thenReturn(getExpectedResponse());
        Page<OrderServiceEntity> result = repository.findAll(PageRequest.of(0, 5));
        assertThat(result.getTotalElements()).isEqualTo(getExpectedResponse().getTotalElements());
        assertThat(result.getTotalPages()).isEqualTo(getExpectedResponse().getTotalPages());
        assertThat(result.stream().toList().toString()).isEqualTo(getExpectedResponse().stream().toList().toString());
    }

    @Test
    void findPendentOrderService() {
        when(repository.findAllByStatusIs(PageRequest.of(0, 5), OrderStatus.PENDING)).thenReturn(getExpectedResponse());
        Page<OrderServiceEntity> result = repository.findAllByStatusIs(PageRequest.of(0, 5), OrderStatus.PENDING);
        assertThat(result.getTotalElements()).isEqualTo(getExpectedResponse().getTotalElements());
        assertThat(result.getTotalPages()).isEqualTo(getExpectedResponse().getTotalPages());
        assertThat(result.stream().toList().toString()).isEqualTo(getExpectedResponse().stream().toList().toString());
        assertThat(result.stream().map(OrderServiceEntity::getStatus).toList().toString()).isEqualTo(getExpectedResponse().stream().map(OrderServiceEntity::getStatus).toList().toString());
    }

    @Test
    void findConsultOrderServiceById() {
        when(repository.findById(getExpectedResponse().getContent().get(0).getId())).thenReturn(Optional.of(getExpectedResponse().getContent().get(0)));
        Optional<OrderServiceEntity> result = repository.findById(UUID.fromString("eb7a8d0b-7c57-4b24-a426-c24578a65aea"));
        assertThat(result.get()).isEqualTo(getExpectedResponse().getContent().get(0));
    }

    @Test
    void createNewOrderService() {
        when(repository.save(getExpectedResponse().getContent().get(0))).thenReturn(getExpectedResponse().getContent().get(0));
        OrderServiceEntity result = repository.save(getExpectedResponse().getContent().get(0));
        assertThat(result.toString()).isEqualTo(getExpectedResponse().getContent().get(0).toString());
    }

    private ClientEntity createClient() {
        return ClientEntity.builder().id(UUID.fromString("c9d9fd45-3768-4073-9383-3b4f09ef5cf5")).name("Test_Name_1")
                .email("Test_1@Test.com").cellphone("11111111111").build();
    }

    private PageImpl<OrderServiceEntity> getExpectedResponse() {
        return new PageImpl<>(List.of(
                OrderServiceEntity.builder().id(UUID.fromString("eb7a8d0b-7c57-4b24-a426-c24578a65aea")).client(createClient()).status(OrderStatus.PENDING).build(),
                OrderServiceEntity.builder().id(UUID.fromString("85347581-aa87-41bb-8afb-3f6677833563")).client(createClient()).status(OrderStatus.PENDING).build()
        ));
    }
}