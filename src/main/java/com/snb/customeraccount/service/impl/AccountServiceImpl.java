package com.snb.customeraccount.service.impl;

import com.snb.customeraccount.dto.AccountDTO;
import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.dto.request.AccountReqDTO;
import com.snb.customeraccount.exception.CustomerNotFoundException;
import com.snb.customeraccount.exception.ValueNotValidException;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Customer;
import com.snb.customeraccount.repository.AccountRepository;
import com.snb.customeraccount.repository.CustomerRepository;
import com.snb.customeraccount.service.AccountService;
import com.snb.customeraccount.service.TransactionService;
import com.snb.customeraccount.util.AccountUtils;
import com.snb.customeraccount.util.ObjectConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    static final String INIT_TRANS_DESC = "initial credit";

    private final AccountRepository accountRepository;

    private final Validator validator;

    private final CustomerRepository customerRepository;

    private final TransactionService transactionService;

    private final ObjectConverter objectSerializer;
    
    

    /**
	 * @param accountRepository
	 * @param validator
	 * @param customerRepository
	 * @param transactionService
	 * @param objectSerializer
	 */
	public AccountServiceImpl(AccountRepository accountRepository, Validator validator,
			CustomerRepository customerRepository, TransactionService transactionService,
			ObjectConverter objectSerializer) {
		super();
		this.accountRepository = accountRepository;
		this.validator = validator;
		this.customerRepository = customerRepository;
		this.transactionService = transactionService;
		this.objectSerializer = objectSerializer;
	}



	/**
     * {@inheritDoc}
     */
    @Override
    public AccountDTO openAccount(AccountReqDTO accountReqDTO) {

        LOGGER.debug("openAccount - accountReqDTO:{}", accountReqDTO);

        if (accountReqDTO == null) {
            throw new ValueNotValidException();
        }

        Set<ConstraintViolation<AccountReqDTO>> violations = validator.validate(accountReqDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<AccountReqDTO> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }

            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        Optional<Customer> existingCustomer = customerRepository.findById(accountReqDTO.getCustomerId());
        if (existingCustomer.isPresent()) {

            Account account = new Account();
            account.setCustomer(existingCustomer.get());
            account.setAccountNumber(AccountUtils.accountNumberGenerator());
            account.setBalance(accountReqDTO.getInitialCredit());
            account = accountRepository.save(account);

            List<TransactionDTO> transactionDTOs = new ArrayList<>();
            if (accountReqDTO.getInitialCredit() != null &&
                    accountReqDTO.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {

                transactionDTOs.add(transactionService.sendTransaction(account,
                        accountReqDTO.getInitialCredit(), INIT_TRANS_DESC));
            }

            AccountDTO accountDTO = objectSerializer.toAccountDTO(account);
            accountDTO.setTransactions(transactionDTOs);

            return accountDTO;
        }

        throw new CustomerNotFoundException(accountReqDTO.getCustomerId());
    }
}
