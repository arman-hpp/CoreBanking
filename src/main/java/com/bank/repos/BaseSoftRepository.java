package com.bank.repos;

import com.bank.models.BaseSoftEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseSoftRepository<T extends BaseSoftEntity, ID> extends CrudRepository<T, ID> {
    List<T> findAllByDeletedFalse();

    List<T> findAllByDeletedTrue();

    List<T> findByIdAndDeletedFalse(ID id);

    default void softDelete(T entity) {
        if(entity == null)
            throw new RuntimeException("The entity must not be null!");

        entity.setDeleted(true);
        save(entity);
    }

    default void softDeleteById(ID id) {
        if (id == null)
            throw new RuntimeException("The entity Id must not be null!");

        softDelete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No %s entity with id %s exists!", "", id), 1)));
    }
}