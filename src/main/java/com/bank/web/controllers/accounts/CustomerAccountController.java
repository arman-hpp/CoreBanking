package com.bank.web.controllers.accounts;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.accounts.AccountDto;
import com.bank.dtos.customers.CustomerSearchInputDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bank.services.accounts.AccountService;
import com.bank.utils.utils.ConvertorUtils;

import java.util.ArrayList;

@Controller
@RequestMapping("/customer_account")
@Layout(title = "Customer Accounts", value = "layouts/default")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class CustomerAccountController {
    private final AccountService _accountService;

    public CustomerAccountController(AccountService accountService) {
        _accountService = accountService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(@RequestParam(name = "customer_id", required = false) String customerId,
                           Model model) {
        var customerIdLong = ConvertorUtils.tryParseLong(customerId, null);

        try {
            if (customerIdLong == null) {
                model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto());
                model.addAttribute("accountDtoList", new ArrayList<AccountDto>());
            } else {
                model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto(customerIdLong));
                var accountDtoList = _accountService.loadCustomerAccounts(customerIdLong);
                model.addAttribute("accountDtoList", accountDtoList);
            }

            return "views/user/customer_account";
        } catch (Exception ex) {
            return "redirect:/customer_account/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

    @PostMapping("/searchCustomer")
    public String searchCustomerSubmit(@ModelAttribute CustomerSearchInputDto customerSearchInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/customer_account/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        var customerId = customerSearchInputDto.getCustomerId();
        if (customerId == null) {
            return "redirect:/customer_account/index";
        }

        return "redirect:/customer_account/index?customer_id=" + customerId;
    }
}