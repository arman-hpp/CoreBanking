package com.bank.services.customers;

import com.bank.dtos.customers.CustomerDto;
import com.bank.exceptions.DomainException;
import com.bank.models.BaseFilter;
import com.bank.models.FilterSpecification;
import com.bank.models.customers.Customer;
import com.bank.repos.customers.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository _customerRepository;
    private final ModelMapper _modelMapper;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        _customerRepository = customerRepository;
        _modelMapper = modelMapper;
    }

    @Cacheable("customers")
    public List<CustomerDto> loadCustomers() {
        log.info("loadCustomers called");

        var customers = _customerRepository.findAllByOrderByIdDesc();
        var customerDtoList = new ArrayList<CustomerDto>();
        for (var customer : customers) {
            customerDtoList.add(_modelMapper.map(customer, CustomerDto.class));
        }

        return customerDtoList;
    }

    @Cacheable(value = "customer", key = "#customerId")
    public CustomerDto loadCustomer(Long customerId) {
        var customer = _customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new DomainException("error.customer.notFound");

        return _modelMapper.map(customer, CustomerDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "customers", allEntries = true)
    }, put = {
            @CachePut(cacheNames = "customer", key = "#customerDto.id")
    })
    public CustomerDto addOrEditCustomer(CustomerDto customerDto) {
        if (customerDto.getId() == null || customerDto.getId() <= 0) {
            return addCustomer(customerDto);
        } else {
            return editCustomer(customerDto);
        }
    }

    private CustomerDto addCustomer(CustomerDto customerDto) {
        var customer = _modelMapper.map(customerDto, Customer.class);
        _customerRepository.save(customer);

        customerDto.setId(customer.getId());
        return customerDto;
    }

    private CustomerDto editCustomer(CustomerDto customerDto) {
        var customer = _customerRepository.findById(customerDto.getId()).orElse(null);
        if (customer == null)
            throw new DomainException("error.customer.notFound");

        _modelMapper.map(customerDto, customer);
        _customerRepository.save(customer);

        return customerDto;
    }

    @Caching(evict = {
            @CacheEvict(value = "customers", allEntries = true),
            @CacheEvict(cacheNames = "customer", key = "#customerId")
    })
    public void removeCustomer(Long customerId) {
        var customer = _customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new DomainException("error.customer.notFound");

        try
        {
            _customerRepository.delete(customer);
        }
        catch (DataIntegrityViolationException ex)
        {
            throw new DomainException("error.public.dependent.entity");
        }
    }

    public Page<CustomerDto> loadCustomerByFilter(List<BaseFilter> filterList, int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "Id"));
        var spec = new FilterSpecification<Customer>(filterList);
        var customers = _customerRepository.findAll(spec, pageable);
        return customers.map(customer -> _modelMapper.map(customer, CustomerDto.class));
    }
}
