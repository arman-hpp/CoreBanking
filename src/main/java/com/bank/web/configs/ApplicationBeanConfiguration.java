package com.bank.web.configs;

import com.bank.web.extensions.exporters.ExporterFactory;
import com.bank.web.extensions.exporters.IExporterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bank.services.loans.AmortizationLoanCalculator;
import com.bank.services.loans.DefaultLoanValidator;
import com.bank.services.loans.interfaces.ILoanCalculator;
import com.bank.services.loans.interfaces.ILoanValidator;
import com.bank.services.transactions.RandomTraceNoGenerator;
import com.bank.services.transactions.interfaces.ITraceNoGenerator;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ITraceNoGenerator traceNoGenerator() {
        return new RandomTraceNoGenerator();
    }

    @Bean
    public ILoanCalculator loanCalculator() {
        return new AmortizationLoanCalculator();
    }

    @Bean
    public ILoanValidator loanValidator() {
        return new DefaultLoanValidator();
    }

    @Bean
    public IExporterFactory exporterFactory() {
        return new ExporterFactory();
    }
}
