package com.rp.interview_rp.model.services;

import com.rp.interview_rp.dtos.ClientDTO;
import com.rp.interview_rp.model.entities.ClientEntity;
import com.rp.interview_rp.model.repositories.IClientRepository;
import com.rp.interview_rp.model.services.interfaces.IClientService;
import com.rp.interview_rp.model.services.interfaces.IService;
import com.rp.interview_rp.model.utils.ConvertObjects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

import static com.rp.interview_rp.model.utils.ConvertObjects.convertClientDtoToEntity;
import static com.rp.interview_rp.model.utils.ConvertObjects.convertClientEntityToDto;

@Service
public record ClientService(IClientRepository repository) implements IClientService, IService {
    @Override
    public Page<ClientDTO> findAll(Pageable pageable) {
        List<ClientDTO> outputList = repository.findAll(pageable).stream()
                .map(ConvertObjects::convertClientEntityToDto).toList();
        return new PageImpl<>(outputList);
    }

    @Override
    public ClientDTO findObjectById(String uuid) {
        return convertClientEntityToDto(
                repository.findById(UUID.fromString(uuid)).orElseThrow(
                        () -> {
                            throw new NotFoundException("ID not found");
                        }));
    }

    @Override
    public ClientDTO createObject(ClientDTO newClientDTO) {
        ClientEntity newClient = convertClientDtoToEntity(newClientDTO);
        return convertClientEntityToDto(repository.save(newClient));
    }
}