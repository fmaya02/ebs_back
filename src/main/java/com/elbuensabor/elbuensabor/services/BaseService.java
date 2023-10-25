package com.elbuensabor.elbuensabor.services;
import com.elbuensabor.elbuensabor.entities.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.io.Serializable;
import java.util.List;
// public interface BaseService <E> {
public interface BaseService  <E extends BaseEntity,ID extends Serializable> {

    public List<E> findALL() throws Exception;

    public E findById(ID id) throws Exception;

    public E save(E entity) throws Exception;

    public E update(ID id, E entity) throws Exception;

    public boolean delete(ID id) throws Exception;

    public Page<E> findAll(Pageable pageable) throws Exception;

}