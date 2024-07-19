package com.bank.web.controllers.loans;

import com.bank.web.extensions.errors.ControllerErrorParser;
import com.bank.web.extensions.thymeleaf.Layout;
import com.bank.dtos.loans.LoanInterestSearchDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bank.services.loans.LoanService;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/loan_interest")
@Layout(title = "Loan Interests", value = "layouts/default")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class LoanInterestsController {
    private final LoanService _loanService;

    public LoanInterestsController(LoanService loanService) {
        _loanService = loanService;
    }

    @GetMapping({"/","/index"})
    public String loadForm(Model model) {
        var fromDateTime = LocalDateTime.now().minusYears(1).withHour(0).withMinute(0).withSecond(1);
        var toDateTime = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        model.addAttribute("loanInterestSearchDto", new LoanInterestSearchDto(fromDateTime, toDateTime));

        return "views/admin/loan_interest";
    }

    @PostMapping("/calcInterests")
    public String calcInterestsSubmit(@ModelAttribute LoanInterestSearchDto loanInterestSearchDto,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/loan_interest/index?error=" + ControllerErrorParser.getError(bindingResult);
        }

        var future = _loanService
                .loadLoanSumInterests(loanInterestSearchDto.fromDate, loanInterestSearchDto.toDate);

        try {
            var loanInterestStatisticsDtoList = future.get();
            model.addAttribute("loanInterestStatisticsDtoList", loanInterestStatisticsDtoList);
        } catch(ExecutionException | InterruptedException ex)  {
            ControllerErrorParser.setError(bindingResult, ex);
        }

        return "views/admin/loan_interest";
    }

}
