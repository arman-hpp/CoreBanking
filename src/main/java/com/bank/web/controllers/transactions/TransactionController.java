package com.bank.web.controllers.transactions;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.errors.ControllerDefaultErrors;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.accounts.AccountSearchInputDto;
import com.bank.dtos.transactions.TransactionDto;
import com.bank.enums.accounts.AccountTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bank.services.accounts.AccountService;
import com.bank.services.transactions.TransactionService;
import com.bank.services.users.AuthenticationService;
import com.bank.utils.utils.ConvertorUtils;

@Controller
@RequestMapping("/transaction")
@Layout(title = "Transactions", value = "layouts/default")
public class TransactionController {
    private final TransactionService _transactionService;
    private final AccountService _accountService;
    private final AuthenticationService _authenticationService;

    public TransactionController(TransactionService transactionService,
                                 AccountService accountService,
                                 AuthenticationService authenticationService) {
        _transactionService = transactionService;
        _accountService = accountService;
        _authenticationService = authenticationService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(@RequestParam(name = "account_id", required = false) String accountId, Model model) {
        var accountIdLong = ConvertorUtils.tryParseLong(accountId, null);

        try {
            var currentUserId = _authenticationService.loadCurrentUserId().orElse(null);
            if (currentUserId == null) {
                return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.IllegalAccess);
            }

            var transactionDtoList = _transactionService.loadUserTransactions(currentUserId);
            model.addAttribute("transactionDtoList", transactionDtoList);

            if (accountIdLong == null) {
                model.addAttribute("accountSearchInputDto", new AccountSearchInputDto());
                model.addAttribute("transactionDto", new TransactionDto());

            } else {
                model.addAttribute("accountSearchInputDto", new AccountSearchInputDto(accountIdLong));
                var foundAccount = _accountService.loadAccount(accountIdLong);

                if(_authenticationService.isUserAdmin()) {
                    if(foundAccount.getAccountType() == AccountTypes.CustomerAccount) {
                        return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.IllegalAccess);
                    }
                } else
                {
                    if(foundAccount.getAccountType() == AccountTypes.BankAccount) {
                        return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.IllegalAccess);
                    }
                }

                var transactionDto = new TransactionDto();
                transactionDto.setAccountCustomerName(foundAccount.getCustomerName());
                transactionDto.setAccountBalance(foundAccount.getBalance());
                transactionDto.setAccountCurrency(foundAccount.getCurrency());
                transactionDto.setAccountId(foundAccount.getId());
                transactionDto.setCurrency(foundAccount.getCurrency());
                model.addAttribute("transactionDto", transactionDto);
            }

            return "views/general/transaction";
        } catch (Exception ex) {
            return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(ex);
        }
    }

    @PostMapping("/searchAccount")
    public String searchAccountSubmit(@ModelAttribute AccountSearchInputDto accountSearchInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        return "redirect:/transaction/index?account_id=" + accountSearchInputDto.getAccountId();
    }

    @PostMapping("/addTransaction")
    public String addSubmit(@ModelAttribute TransactionDto transactionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        try {
            var currentUserId = _authenticationService.loadCurrentUserId().orElse(null);
            if (currentUserId == null) {
                return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(ControllerDefaultErrors.IllegalAccess);
            }

            transactionDto.setUserId(currentUserId);

            _transactionService.doTransaction(transactionDto);

            return "redirect:/transaction/index";
        } catch (Exception ex) {
            return "redirect:/transaction/index?error=" + ControllerErrorParser.getError(ex);
        }
    }
}