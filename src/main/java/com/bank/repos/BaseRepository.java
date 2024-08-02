package com.bank.repos;

import com.bank.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

//@NoRepositoryBean
//public interface BaseRepository<T extends BaseEntity, ID> extends CrudRepository<T, ID> {
//}

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}