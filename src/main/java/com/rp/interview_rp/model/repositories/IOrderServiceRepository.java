package com.rp.interview_rp.model.repositories;

import com.rp.interview_rp.model.entities.OrderServiceEntity;
import com.rp.interview_rp.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrderServiceRepository extends PagingAndSortingRepository<OrderServiceEntity, UUID> {
    Page<OrderServiceEntity> findAll(Pageable pageable);
    Page<OrderServiceEntity> findAllByStatusIs(Pageable pageable, OrderStatus status);
}