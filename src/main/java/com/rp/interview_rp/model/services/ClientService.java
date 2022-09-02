package com.rp.interview_rp.model.services;

import com.rp.interview_rp.dtos.ClientDTO;
import com.rp.interview_rp.model.repositories.IClientRepository;
import com.rp.interview_rp.model.services.interfaces.IClientService;
import com.rp.interview_rp.model.services.interfaces.IService;
import com.rp.interview_rp.model.utils.ConvertObjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public record ClientService(IClientRepository repository) implements IClientService, IService {
    @Override
    public Page<ClientDTO> findAll(Pageable pageable) {
        List<ClientDTO> outputList = repository.findAll(pageable).stream()
                .map(ConvertObjects::convertClientEntityToDto).toList();
        return new PageImpl<>(outputList);
    }

    @Override
    public ClientDTO findObjectById() {
        return null;
    }

    @Override
    public ClientDTO createObject() {
        return null;
    }

    @Override
    public ClientDTO updateObject() {
        return null;
    }

    @Override
    public void deleteObject() {

    }
}