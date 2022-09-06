package com.rp.interview_rp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.UUID;

public class AddressDTO implements Serializable {
    private UUID id;
    private String number;
    private String district;
    private String street;
    private String city;
    @JsonIgnore
    private ClientDTO clientDTO;
}