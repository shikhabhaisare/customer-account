package com.snb.customeraccount.controller;

import com.snb.customeraccount.dto.AccountDTO;
import com.snb.customeraccount.dto.CustomerDTO;
import com.snb.customeraccount.dto.request.AccountReqDTO;
import com.snb.customeraccount.service.AccountService;
import com.snb.customeraccount.service.CustomerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle web operations
 */
@Controller
@RequestMapping("/web/v1")
public class WebController {

    private final CustomerService customerService;

    private final AccountService accountService;
    

    /**
	 * @param customerService
	 * @param accountController
	 */
	public WebController(CustomerService customerService, AccountService accountService) {
		super();
		this.customerService = customerService;
		this.accountService = accountService;
	}

	@GetMapping("/customers")
    public String showAllCustomers(Model model) {
        List<CustomerDTO> customers = customerService.fetchAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String showCustomerAccounts(Model model, @PathVariable Long id) {
        CustomerDTO customerInfo = customerService.fetchCustomerInfo(id);

        model.addAttribute("customerId", customerInfo.getId());
        model.addAttribute("name", customerInfo.getName());
        model.addAttribute("surname", customerInfo.getSurname());
        model.addAttribute("accounts", customerInfo.getAccounts());

        AccountReqDTO accountReqDTO = AccountReqDTO.builder()
                .customerId(id)
                .build();
        model.addAttribute("accountReqDTO", accountReqDTO);
        return "accounts";
    }

    @GetMapping("/customers/{customerId}/accounts/{accountId}")
    public String showAccountTransactions(Model model,
                                          @PathVariable Long customerId,
                                          @PathVariable Long accountId) {

        CustomerDTO customerInfo = customerService.fetchCustomerInfo(customerId);

        AccountDTO found = customerInfo.getAccounts().stream()
                .filter(account -> accountId.equals(account.getId()))
                .findFirst()
                .orElse(null);

        model.addAttribute("customerId", customerId);
        model.addAttribute("customerName", customerInfo.getName());
        model.addAttribute("customerSurname", customerInfo.getSurname());
        model.addAttribute("accountNumber", found.getAccountNumber());
        model.addAttribute("transactions", found.getTransactions());
        return "transactions";
    }

    @PostMapping("/accounts")
    public String openAccount(@ModelAttribute("accountReqDTO") AccountReqDTO accountReqDTO) {
        accountService.openAccount(accountReqDTO);
        return "redirect:/web/v1/customers/" + accountReqDTO.getCustomerId();
    }
}
