package com.bank.web.controllers.transactions;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bank.services.transactions.TransactionService;

@Controller
@RequestMapping("/branch_transaction")
@Layout(title = "Branch Transactions", value = "layouts/default")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class BranchTransactionController {
    private final TransactionService _transactionService;

    public BranchTransactionController(TransactionService transactionService) {
        _transactionService = transactionService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(Model model) {
        try {
            var transactionDtoList = _transactionService.loadLastBranchTransactions();
            model.addAttribute("transactionDtoList", transactionDtoList);

            return "views/admin/branch_transaction";
        } catch (Exception ex) {
            return "redirect:/branch_transaction/index?error=" + ControllerErrorParser.getError(ex);
        }
    }
}
