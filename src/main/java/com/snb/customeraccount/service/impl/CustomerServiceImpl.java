package com.snb.customeraccount.service.impl;

import com.snb.customeraccount.dto.AccountDTO;
import com.snb.customeraccount.dto.CustomerDTO;
import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.exception.CustomerNotFoundException;
import com.snb.customeraccount.exception.ValueNotValidException;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Customer;
import com.snb.customeraccount.model.Transaction;
import com.snb.customeraccount.repository.CustomerRepository;
import com.snb.customeraccount.service.CustomerService;
import com.snb.customeraccount.util.ObjectConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    private final ObjectConverter objectSerializer;


    /**
	 * @param customerRepository
	 * @param objectSerializer
	 */
	public CustomerServiceImpl(CustomerRepository customerRepository, ObjectConverter objectSerializer) {
		super();
		this.customerRepository = customerRepository;
		this.objectSerializer = objectSerializer;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public CustomerDTO fetchCustomerInfo(Long id) {

        LOGGER.debug("fetchCustomerInfo - id:{}", id);

        if (id == null || id == 0) {
            throw new ValueNotValidException();
        }

        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {

            List<AccountDTO> accountDTOS = new ArrayList<>();
            List<TransactionDTO> transactionDTOS;

            for (Account account : existingCustomer.get().getAccounts()) {

                transactionDTOS = new ArrayList<>();

                for (Transaction transaction : account.getTransactions()) {
                    transactionDTOS.add(objectSerializer.toTransactionDTO(transaction));
                }

                AccountDTO accountDTO = objectSerializer.toAccountDTO(account);
                accountDTO.setTransactions(transactionDTOS);

                accountDTOS.add(accountDTO);
            }

            LOGGER.debug("Customer found: {} {}", existingCustomer.get().getName(), existingCustomer.get().getSurname());

            CustomerDTO customerDTO = objectSerializer.toCustomerDTO(existingCustomer.get());
            customerDTO.setAccounts(accountDTOS);

            return customerDTO;
        }

        throw new CustomerNotFoundException(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDTO> fetchAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        customerRepository.findAll()
                .forEach(customer -> customerDTOS.add(objectSerializer.toCustomerDTO(customer)));

        return customerDTOS;
    }
}
