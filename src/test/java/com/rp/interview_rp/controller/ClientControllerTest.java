package com.rp.interview_rp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.repositories.IClientRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    @MockBean
    IClientRepository clientService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllClients() throws Exception {
        when(clientService.findAll(PageRequest.of(0, 5))).thenReturn(getExpectedResponse());
        mockMvc.perform(get("/rp/clients"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.content.[0].name").value(getExpectedResponse().stream().toList().get(0).getName()))
                .andExpect(jsonPath("$.content.[0].id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.content.[0].email")
                        .value(getExpectedResponse().stream().toList().get(0).getEmail()))
                .andExpect(jsonPath("$.content.[0].cellphone")
                        .value(getExpectedResponse().stream().toList().get(0).getCellphone()))
                .andDo(print());
    }

    @Test
    public void testCreateNewClient() throws Exception {
        when(clientService.save(getExpectedResponse().getContent().get(0))).thenReturn(getExpectedResponse().getContent().get(0));
        mockMvc.perform(post("/rp/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getExpectedResponse().getContent().get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(getExpectedResponse().getContent().get(0).getId().toString()))
                .andExpect(jsonPath("$.name").value(getExpectedResponse().getContent().get(0).getName()))
                .andExpect(jsonPath("$.cellphone").value(getExpectedResponse().getContent().get(0).getCellphone()))
                .andExpect(jsonPath("$.email").value(getExpectedResponse().getContent().get(0).getEmail()))
                .andDo(print());
    }

    @Test
    void getFindClientById() throws Exception {
        when(clientService.findById(getExpectedResponse().getContent().get(0).getId())).thenReturn(Optional.of(getExpectedResponse().getContent().get(0)));
        mockMvc.perform(get("/rp/clients/c9d9fd45-3768-4073-9383-3b4f09ef5cf5"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.name").value(getExpectedResponse().stream().toList().get(0).getName()))
                .andExpect(jsonPath("$.id")
                        .value(getExpectedResponse().stream().toList().get(0).getId().toString()))
                .andExpect(jsonPath("$.email")
                        .value(getExpectedResponse().stream().toList().get(0).getEmail()))
                .andExpect(jsonPath("$.cellphone")
                        .value(getExpectedResponse().stream().toList().get(0).getCellphone()))
                .andDo(print());
    }

    private PageImpl<ClientEntity> getExpectedResponse() {
        return new PageImpl<>(List.of(
                ClientEntity.builder().id(UUID.fromString("c9d9fd45-3768-4073-9383-3b4f09ef5cf5")).name("Test_Name_1")
                        .email("Test_1@Test.com").cellphone("11111111111").build(),
                ClientEntity.builder().id(UUID.fromString("dd1f9341-dfab-46a5-bf08-28baaa5687db")).name("Test_Name_2")
                        .email("Test_2@Test.com").cellphone("22222222222").build()));
    }
}