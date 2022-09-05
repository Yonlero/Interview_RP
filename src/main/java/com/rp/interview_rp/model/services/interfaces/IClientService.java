package com.rp.interview_rp.model.services.interfaces;

import com.rp.interview_rp.dtos.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IClientService {
    Page<ClientDTO> findAll(Pageable pageable);

    ClientDTO findObjectById(String uuid);

    ClientDTO createObject(ClientDTO newClientDTO);
}