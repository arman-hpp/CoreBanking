package com.bank.models;

import com.bank.enums.FilterComparators;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilterSpecification<T extends BaseEntity> implements Specification<T>{

    private final List<BaseFilter> filters;

    public FilterSpecification(List<BaseFilter> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<T> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        var predicates = getPredicateList(root, criteriaBuilder);
        return criteriaBuilder.and(predicates);
    }

    private Predicate[] getPredicateList(Root<T> root, CriteriaBuilder criteriaBuilder)
    {
        var predicates = new ArrayList<Predicate>();
        filters.forEach(filter ->
        {
            var predicate = getPredicate(filter, root, criteriaBuilder);
            predicates.add(predicate);
        });

        return predicates.toArray(new Predicate[0]);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Predicate getPredicate(BaseFilter filter, Root<T> root, CriteriaBuilder criteriaBuilder){
        if (!(filter.getValue() instanceof Comparable || filter.getValue() instanceof Collection)) {
            throw new IllegalStateException("This library only support primitive types and date/time types in the list: " +
                    "Integer, Long, Double, Float, Short, BidDecimal, Character, String, Byte, Boolean" +
                    ", Date, Time, TimeStamp, Calendar");
        }

        if(filter.getComparator().equals(FilterComparators.EQUAL)) {
            return criteriaBuilder.equal(root.get(filter.getName()), filter.getValue());
        } else if(filter.getComparator().equals(FilterComparators.NOT_EQUAL)) {
            return criteriaBuilder.notEqual(root.get(filter.getName()), filter.getValue());
        } else if (filter.getComparator().equals(FilterComparators.LESS_THAN)) {
            return criteriaBuilder.lessThan(root.get(filter.getName()), (Comparable) filter.getValue());
        } else if (filter.getComparator().equals(FilterComparators.LESS_THAN_OR_EQUAL_TO)) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(filter.getName()), (Comparable) filter.getValue());
        } else if (filter.getComparator().equals(FilterComparators.GREATER_THAN)) {
            return criteriaBuilder.greaterThan(root.get(filter.getName()), (Comparable) filter.getValue());
        } else if (filter.getComparator().equals(FilterComparators.GREATER_THAN_OR_EQUAL_TO)) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getName()), (Comparable) filter.getValue());
        } else if (filter.getComparator().equals(FilterComparators.BETWEEN)) {
            return criteriaBuilder.between(root.get(filter.getName()), (Comparable) filter.getValue(), (Comparable) filter.getValue2());
        } else if (filter.getComparator().equals(FilterComparators.CONTAIN)) {
            return criteriaBuilder.like(root.get(filter.getName()), '%' + String.valueOf(filter.getValue()) + '%');
        } else if (filter.getComparator().equals(FilterComparators.NOT_CONTAIN)) {
            return criteriaBuilder.notLike(root.get(filter.getName()), '%' + String.valueOf(filter.getValue()) + '%');
        } else if (filter.getComparator().equals(FilterComparators.START_WITH)) {
            return criteriaBuilder.like(root.get(filter.getName()), String.valueOf(filter.getValue()) + '%');
        } else if (filter.getComparator().equals(FilterComparators.END_WITH)) {
            return criteriaBuilder.notLike(root.get(filter.getName()), '%' + String.valueOf(filter.getValue()));
        } else if (filter.getComparator().equals(FilterComparators.IN)) {
            return root.get(filter.getName()).in(filter.getValue());
        } else if (filter.getComparator().equals(FilterComparators.IS_NULL)) {
            return root.get(filter.getName()).isNull();
        } else if (filter.getComparator().equals(FilterComparators.IS_NOT_NULL)) {
            return root.get(filter.getName()).isNotNull();
        } else if (filter.getComparator().equals(FilterComparators.IS_EMPTY)) {
            return criteriaBuilder.equal(root.get(filter.getName()), "");
        } else if (filter.getComparator().equals(FilterComparators.IS_NOT_EMPTY)) {
            return criteriaBuilder.equal(root.get(filter.getName()), "");
        } else {
            return criteriaBuilder.equal(root.get(filter.getName()), filter.getValue());
        }
    }
}