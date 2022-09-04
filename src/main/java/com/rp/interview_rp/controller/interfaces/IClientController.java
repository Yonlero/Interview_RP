package com.rp.interview_rp.controller.interfaces;

import com.rp.interview_rp.dtos.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IClientController {
    ResponseEntity<Page<ClientDTO>> getAllClients(Integer page, Integer size);

    ResponseEntity<ClientDTO> getFindClientById(String uuid);

    ResponseEntity<ClientDTO> postCreateNewClient(ClientDTO newClientDto);
}