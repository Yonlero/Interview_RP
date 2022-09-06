package com.rp.interview_rp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.entities.OrderServiceEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import com.rp.interview_rp.model.repositories.IOrderServiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceControllerTest {
    @MockBean
    IOrderServiceRepository orderServiceRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllOrderService() throws Exception {
        when(orderServiceRepository.findAll(PageRequest.of(0, 5))).thenReturn(getExpectedResponse());
        mockMvc.perform(get("/rp/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.content.[0].client.id")
                        .value(getExpectedResponse().stream().toList().get(0).getClient().getId().toString()))
                .andExpect(jsonPath("$.content.[0].status")
                        .value(getExpectedResponse().stream().toList().get(0).getStatus().toString()))
                .andDo(print());
    }

    @Test
    void getPendentOrderService() throws Exception {
        when(orderServiceRepository.findAllByStatusIs(PageRequest.of(0, 5), OrderStatus.PENDING)).thenReturn(getExpectedResponse());
        mockMvc.perform(get("/rp/orders/pendents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.content.[0].client.id")
                        .value(getExpectedResponse().stream().toList().get(0).getClient().getId().toString()))
                .andExpect(jsonPath("$.content.[0].status")
                        .value(getExpectedResponse().stream().toList().get(0).getStatus().toString()))
                .andDo(print());
    }

    @Test
    void postCreateNewOrderService() throws Exception {
        when(orderServiceRepository.save(getExpectedResponse().getContent().get(0))).thenReturn(getExpectedResponse().getContent().get(0));
        mockMvc.perform(post("/rp/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getExpectedResponse().getContent().get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.client.id")
                        .value(getExpectedResponse().stream().toList().get(0).getClient().getId().toString()))
                .andExpect(jsonPath("$.status")
                        .value(getExpectedResponse().stream().toList().get(0).getStatus().toString()))
                .andDo(print());
    }

    @Test
    void putUpdateOrderService() throws Exception {
        when(orderServiceRepository.findById(getExpectedResponse().getContent().get(0).getId())).thenReturn(Optional.of(getExpectedResponse().getContent().get(0)));
        when(orderServiceRepository.save(getExpectedResponse().getContent().get(0))).thenReturn(getExpectedResponse().getContent().get(0));
        mockMvc.perform(put("/rp/orders/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getExpectedResponse().getContent().get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.client.id")
                        .value(getExpectedResponse().stream().toList().get(0).getClient().getId().toString()))
                .andExpect(jsonPath("$.status")
                        .value(getExpectedResponse().stream().toList().get(0).getStatus().toString()))
                .andDo(print());
    }

    @Test
    void getConsultOrderServiceById() throws Exception {
        when(orderServiceRepository.findById(getExpectedResponse().getContent().get(0).getId())).thenReturn(Optional.of(getExpectedResponse().getContent().get(0)));
        mockMvc.perform(get("/rp/orders/eb7a8d0b-7c57-4b24-a426-c24578a65aea"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.client.id")
                        .value(getExpectedResponse().stream().toList().get(0).getClient().getId().toString()))
                .andExpect(jsonPath("$.status")
                        .value(getExpectedResponse().stream().toList().get(0).getStatus().toString()))
                .andDo(print());
    }

    private ClientEntity createClient() {
        return ClientEntity.builder().id(UUID.fromString("c9d9fd45-3768-4073-9383-3b4f09ef5cf5"))
                .name("Test_Name_1")
                .email("Test_1@Test.com")
                .cellphone("11111111111")
                .build();
    }

    private PageImpl<OrderServiceEntity> getExpectedResponse() {
        return new PageImpl<>(List.of(
                OrderServiceEntity.builder().id(UUID.fromString("eb7a8d0b-7c57-4b24-a426-c24578a65aea"))
                        .client(createClient())
                        .status(OrderStatus.PENDING)
                        .responsible("Tec_1").build(),
                OrderServiceEntity.builder().id(UUID.fromString("85347581-aa87-41bb-8afb-3f6677833563")).client(createClient()).status(OrderStatus.PENDING).responsible("Tec_2").build()
        ));
    }
}