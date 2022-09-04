package com.rp.interview_rp.controller;

import com.rp.interview_rp.controller.interfaces.IClientController;
import com.rp.interview_rp.controller.interfaces.IController;
import com.rp.interview_rp.dtos.ClientDTO;
import com.rp.interview_rp.model.services.interfaces.IClientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/rp")
public class ClientController implements IController, IClientController {
    private final IClientService service;

    public ClientController(IClientService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/clients")
    @ApiResponse(responseCode = "200", description = "Return a page with N clients")
    public ResponseEntity<Page<ClientDTO>> getAllClients(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        log.info("Getting clients - ClientController");
        return ResponseEntity.ok(service.findAll(PageRequest.of(page, size)));
    }

    @Override
    @GetMapping("/clients/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a ClientDTO"),
            @ApiResponse(responseCode = "404", description = "Not Found Id")
    })
    public ResponseEntity<ClientDTO> getFindClientById(@PathVariable String id) {
        log.info("Getting clients - ClientController");
        return ResponseEntity.ok(service.findObjectById(id));
    }

    @Override
    @PostMapping("/clients/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create a new Client and return it"),
            @ApiResponse(responseCode = "204", description = "Invalid Body")
    })
    public ResponseEntity<ClientDTO> postCreateNewClient(@RequestBody(required = true) ClientDTO newClientDTO) {
        log.info("Creating a new client - ClientController");
        ClientDTO newClient = service.createObject(newClientDTO);
        return ResponseEntity.created(buildUri(newClient)).body(newClient);
    }

    private URI buildUri(ClientDTO clientDTO) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clientDTO.getId())
                .toUri();
    }
}