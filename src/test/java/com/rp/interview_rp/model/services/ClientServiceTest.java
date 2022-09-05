package com.rp.interview_rp.model.services;

import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.repositories.IClientRepository;
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
public class ClientServiceTest {
    @Mock
    IClientRepository clientRepository;

    @Test
    public void testFindAll() {
        when(clientRepository.findAll(PageRequest.of(0, 5))).thenReturn(getExpectedResponse());
        Page<ClientEntity> result = clientRepository.findAll(PageRequest.of(0, 5));
        assertThat(result.getTotalElements()).isEqualTo(getExpectedResponse().getTotalElements());
        assertThat(result.getTotalPages()).isEqualTo(getExpectedResponse().getTotalPages());
        assertThat(result.stream().toList().toString()).isEqualTo(getExpectedResponse().stream().toList().toString());
    }

    @Test
    public void testCreateNewClient() {
        when(clientRepository.save(getExpectedResponse().get().findFirst().get())).thenReturn(
                getExpectedResponse().get().findFirst().get());
        ClientEntity result = clientRepository.save(getExpectedResponse().get().findFirst().get());
        assertThat(result).isEqualTo(getExpectedResponse().get().findFirst().get());
    }

    @Test
    void findObjectById() {
        when(clientRepository.findById(getExpectedResponse().getContent().get(0).getId())).thenReturn(Optional.of(getExpectedResponse().getContent().get(0)));
        Optional<ClientEntity> result = clientRepository.findById(getExpectedResponse().getContent().get(0).getId());
        assertThat(result.get().toString()).isEqualTo(getExpectedResponse().getContent().get(0).toString());
    }

    private PageImpl<ClientEntity> getExpectedResponse() {
        return new PageImpl<>(List.of(
                ClientEntity.builder().id(UUID.fromString("c9d9fd45-3768-4073-9383-3b4f09ef5cf5")).name("Test_Name_1")
                        .email("Test_1@Test.com").cellphone("11111111111").build(),
                ClientEntity.builder().id(UUID.fromString("dd1f9341-dfab-46a5-bf08-28baaa5687db")).name("Test_Name_2")
                        .email("Test_2@Test.com").cellphone("22222222222").build()));
    }
}