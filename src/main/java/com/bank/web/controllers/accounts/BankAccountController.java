package com.bank.web.controllers.accounts;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bank.services.accounts.AccountService;

@Controller
@RequestMapping("/bank_account")
@Layout(title = "Bank Accounts", value = "layouts/default")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class BankAccountController {
    private final AccountService _accountService;

    public BankAccountController(AccountService accountService) {
        _accountService = accountService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(Model model) {
        try {
            var bankAccountsList = _accountService.loadBankAccounts();
            model.addAttribute("accountDtoList", bankAccountsList);

            return "views/admin/bank_account";
        } catch (Exception ex) {
            return "redirect:/branch_transaction/index?error=" + ControllerErrorParser.getError(ex);
        }
    }
}
