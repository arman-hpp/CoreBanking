package com.bank.web.controllers.loans;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.customers.CustomerSearchInputDto;
import com.bank.dtos.loans.LoanDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bank.services.loans.LoanService;
import com.bank.utils.utils.ConvertorUtils;

import java.util.ArrayList;

@Controller
@RequestMapping("/customer_loan")
@Layout(title = "Customer Loans", value = "layouts/default")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class CustomerLoanController {
    private final LoanService _loanService;

    public CustomerLoanController(LoanService loanService) {
        _loanService = loanService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(@RequestParam(name = "customer_id", required = false) String customerId, Model model) {
        var customerIdLong = ConvertorUtils.tryParseLong(customerId, null);

        try {
            if (customerIdLong == null) {
                model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto());
                model.addAttribute("loanDtoList", new ArrayList<LoanDto>());
            } else {
                model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto(customerIdLong));
                var loanDtoList = _loanService.loadLoansByCustomerId(customerIdLong);
                model.addAttribute("loanDtoList", loanDtoList);
            }

            return "views/user/customer_loan";
        } catch (Exception ex) {
            return "redirect:/customer_loan/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

    @PostMapping("/searchCustomer")
    public String searchCustomerSubmit(@ModelAttribute CustomerSearchInputDto customerSearchInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/customer_loan/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        var customerId = customerSearchInputDto.getCustomerId();
        if (customerId == null) {
            return "redirect:/customer_loan/index";
        }

        return "redirect:/customer_loan/index?customer_id=" + customerId;
    }
}
