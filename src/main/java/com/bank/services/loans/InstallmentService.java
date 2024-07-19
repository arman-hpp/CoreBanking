package com.bank.services.loans;

import com.bank.repos.loans.InstallmentRepository;
import com.bank.dtos.loans.InstallmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class InstallmentService {
    private final InstallmentRepository _installmentRepository;
    private final ModelMapper _modelMapper;

    public InstallmentService(InstallmentRepository installmentRepository, ModelMapper modelMapper) {
        _installmentRepository = installmentRepository;
        _modelMapper = modelMapper;
    }

    public List<InstallmentDto> loadInstallments(Long loanId) {
        var installments = _installmentRepository.findByLoanIdOrderByInstallmentNo(loanId);
        var installmentDtoList = new ArrayList<InstallmentDto>();
        for(var installment : installments) {
            installmentDtoList.add(_modelMapper.map(installment, InstallmentDto.class));
        }

        return installmentDtoList;
    }

    public List<InstallmentDto> loadPaidInstallments(Long loanId) {
        var installments = _installmentRepository.findByLoanIdAndPaidTrueOrderByInstallmentNo(loanId);
        var installmentDtoList = new ArrayList<InstallmentDto>();
        for(var installment : installments) {
            installmentDtoList.add(_modelMapper.map(installment, InstallmentDto.class));
        }

        return installmentDtoList;
    }

    public List<InstallmentDto> loadNotPaidInstallments(Long loanId) {
        var installments = _installmentRepository.findByLoanIdAndPaidFalseOrderByInstallmentNo(loanId);
        var installmentDtoList = new ArrayList<InstallmentDto>();
        for(var installment : installments) {
            installmentDtoList.add(_modelMapper.map(installment, InstallmentDto.class));
        }

        return installmentDtoList;
    }
}