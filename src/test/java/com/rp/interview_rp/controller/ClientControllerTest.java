package com.rp.interview_rp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.interview_rp.dtos.ClientDTO;
import com.rp.interview_rp.model.services.interfaces.IClientService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {
    @MockBean
    IClientService clientService;
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
    @Disabled
    public void testCreateNewClient() throws Exception {
        when(clientService.createObject(getExpectedResponse().get().findFirst().get()))
                .thenReturn(getExpectedResponse().get().findFirst().get());
        mockMvc.perform(post("/rp/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getExpectedResponse().get().findFirst().get())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private PageImpl<ClientDTO> getExpectedResponse() {
        return new PageImpl<>(List.of(
                ClientDTO.builder().id(UUID.fromString("c9d9fd45-3768-4073-9383-3b4f09ef5cf5")).name("Test_Name_1")
                        .email("Test_1@Test.com").cellphone("11111111111").build(),
                ClientDTO.builder().id(UUID.fromString("dd1f9341-dfab-46a5-bf08-28baaa5687db")).name("Test_Name_2")
                        .email("Test_2@Test.com").cellphone("22222222222").build()));
    }
}