package com.rp.interview_rp.model.services.interfaces;

import com.rp.interview_rp.model.entities.interfaces.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T extends IEntity> {
    Page<T> findAll(Pageable pageable);

    T findObjectById();

    T createObject();

    T updateObject();

    void deleteObject();
}