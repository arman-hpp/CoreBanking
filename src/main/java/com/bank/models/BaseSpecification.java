package com.bank.models;


import com.bank.dtos.FilterDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BaseSpecification<T extends BaseEntity> {
    public Specification<T> columnEqual(List<FilterDto> filterDTOList) {
        return (root, query, criteriaBuilder) -> {
           var predicates = new ArrayList<Predicate>();
            filterDTOList.forEach(filter ->
            {
                var predicate = criteriaBuilder.equal(root.get(filter.getColumnName()),filter.getColumnValue());
                predicates.add(predicate);
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}