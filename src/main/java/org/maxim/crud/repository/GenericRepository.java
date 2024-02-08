package org.maxim.crud.repository;

import org.maxim.crud.model.Post;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,ID> {
    Optional<T> save(T target);
    Optional<T> update(T target);
    Optional<T> getId(ID id);
    Optional<List<T>> getAll();
    Optional<Post> deleteById(ID id);

}