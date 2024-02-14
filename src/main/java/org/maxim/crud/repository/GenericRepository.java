package org.maxim.crud.repository;

import org.maxim.crud.model.Post;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,ID> {
    List<T> getAll();
    T getById(ID id);
    T save(T t);
    T update(T t);
    boolean deleteById(ID id);
}

