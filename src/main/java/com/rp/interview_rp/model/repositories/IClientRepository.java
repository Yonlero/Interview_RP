package com.rp.interview_rp.model.repositories;

import com.rp.interview_rp.model.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IClientRepository extends PagingAndSortingRepository<ClientEntity, UUID> {
    Page<ClientEntity> findAll(Pageable pageable);
}