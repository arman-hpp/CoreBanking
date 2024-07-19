package com.bank.web.controllers.loans;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.accounts.AccountSearchInputDto;
import com.bank.dtos.loans.LoanDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bank.services.loans.LoanService;
import com.bank.utils.utils.ConvertorUtils;

import java.util.ArrayList;

@Controller
@RequestMapping("/account_loan")
@Layout(title = "Account Loans", value = "layouts/default")
public class AccountLoanController {
    private final LoanService _loanService;

    public AccountLoanController(LoanService loanService) {
        _loanService = loanService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(@RequestParam(name = "account_id", required = false) String accountId,
                           Model model) {
        var accountIdLong = ConvertorUtils.tryParseLong(accountId, null);

        try {
            if (accountIdLong == null) {
                model.addAttribute("accountSearchInputDto", new AccountSearchInputDto());
                model.addAttribute("loanDtoList", new ArrayList<LoanDto>());
            } else {
                model.addAttribute("accountSearchInputDto", new AccountSearchInputDto(accountIdLong));

                var loanDtoList = _loanService.loadLoansByAccountId(accountIdLong);
                model.addAttribute("loanDtoList", loanDtoList);
            }

            return "views/general/account_loan";
        } catch (Exception ex) {
            return "redirect:/account_loan/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

    @PostMapping("/searchAccount")
    public String searchAccountSubmit(@ModelAttribute AccountSearchInputDto accountSearchInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/account_loan/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        var accountId = accountSearchInputDto.getAccountId();
        if (accountId == null) {
            return "redirect:/account_loan/index";
        }

        return "redirect:/account_loan/index?account_id=" + accountId;
    }
}
