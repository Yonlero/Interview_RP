package com.rp.interview_rp.model.repositories;

import com.rp.interview_rp.model.entities.OrderService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrderServiceRepository extends PagingAndSortingRepository<OrderService, UUID> {
}
