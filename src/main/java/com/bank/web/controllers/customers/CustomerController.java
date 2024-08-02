package com.bank.web.controllers.customers;

import com.bank.utils.web.RequestParamsBuilder;
import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.errors.ControllerDefaultErrors;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.customers.CustomerDto;
import com.bank.dtos.customers.CustomerSearchInputDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bank.services.customers.CustomerService;
import com.bank.utils.utils.ConvertorUtils;

@Controller
@RequestMapping("/customer")
@Layout(title = "Customers", value = "layouts/default")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class CustomerController {
    private final CustomerService _customerService;

    public CustomerController(CustomerService customerService) {
        _customerService = customerService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(
            @RequestParam(required = false, name = "customer_id") String customerId,
            @RequestParam(name = "search", defaultValue = "false") String search,
            Model model) {

        var customerIdLong = ConvertorUtils.tryParseLong(customerId, null);
        var searchBool = ConvertorUtils.tryParseBool(search, false);


        try {
            if (customerIdLong == null) {
                var customerDtoList = _customerService.loadCustomers();
                model.addAttribute("customerDtoList", customerDtoList);
                model.addAttribute("customerDto", new CustomerDto());
                model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto());
            } else {
                var foundCustomer = _customerService.loadCustomer(customerIdLong);
                if(searchBool)
                {
                    model.addAttribute("customerDtoList", foundCustomer);
                    model.addAttribute("customerDto", new CustomerDto());
                    model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto(customerIdLong));
                }
                else {
                    var customerDtoList = _customerService.loadCustomers();
                    model.addAttribute("customerDtoList", customerDtoList);
                    model.addAttribute("customerDto", foundCustomer);
                    model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto());
                }
            }

            return "views/user/customer";
        }catch (Exception ex) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

//    @GetMapping("/index/{id}")
//    public String loadFormById(@PathVariable String id, Model model) {
//        var idLong = ConvertorUtils.tryParseLong(id, -1L);
//        if (idLong <= 0) {
//            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.InvalidInputParameters);
//        }
//
//        try {
//            var foundCustomer = _customerService.loadCustomer(idLong);
//            model.addAttribute("customerDto", foundCustomer);
//
//            var customers = _customerService.loadCustomers();
//            model.addAttribute("customerDtoList", customers);
//
//            model.addAttribute("customerSearchInputDto", new CustomerSearchInputDto());
//
//            return "views/user/customer";
//        } catch (Exception ex) {
//            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ex);
//        }
//    }

    @PostMapping("/addCustomer")
    public String addSubmit(@ModelAttribute CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        try {
            _customerService.addOrEditCustomer(customerDto);

            return "redirect:/customer/index";
        } catch (Exception ex) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

    @PostMapping("/deleteCustomer/{id}")
    public String deleteSubmit(@PathVariable String id) {
        var idLong = ConvertorUtils.tryParseLong(id, -1L);
        if (idLong <= 0) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.InvalidInputParameters);
        }

        try {
            _customerService.removeCustomer(idLong);

            return "redirect:/customer/index";
        } catch (Exception ex) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

    @PostMapping("/editCustomer/{id}")
    public String editSubmit(@PathVariable String id) {
        var idLong = ConvertorUtils.tryParseLong(id, -1L);
        if (idLong <= 0) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.InvalidInputParameters);
        }

        // return "redirect:/customer/index/" + idLong;
        return "redirect:/customer/index?customer_id=" + idLong;
    }

    @PostMapping("/searchCustomer")
    public String searchSubmit(@ModelAttribute CustomerSearchInputDto customerSearchInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/customer/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        var customerId = customerSearchInputDto.getCustomerId();
        if (customerId == null) {
            return "redirect:/customer/index";
        }

        return new RequestParamsBuilder("redirect:/customer/index")
                .Add("customer_id", customerId)
                .Add("search", "true")
                .toString();
    }
}
