package com.bank.repos;

import com.bank.models.BaseEntity;
import org.modelmapper.internal.util.Assert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends CrudRepository<T, ID> {
}