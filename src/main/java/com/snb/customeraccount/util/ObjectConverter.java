package com.snb.customeraccount.util;

import com.snb.customeraccount.dto.AccountDTO;
import com.snb.customeraccount.dto.CustomerDTO;
import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Customer;
import com.snb.customeraccount.model.Transaction;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Converter to convert entities to DTOs
 *
 */
@Component
public class ObjectConverter {

    private static final String PATTERN_FORMAT = "dd-MM-yyyy HH:mm:ss";

    //entity to DTO 
    public CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO.Builder()
                .withId(customer.getId())
                .withName(customer.getName())
                .withSurname(customer.getSurname())
                .build();
    }

    public AccountDTO toAccountDTO(Account account) {
        return new AccountDTO.Builder()
                .withId(account.getId())
                .withAccountNumber(account.getAccountNumber())
                .withBalance(account.getBalance())
                .build();
    }

    public TransactionDTO toTransactionDTO(Transaction transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return new TransactionDTO.Builder()
                .withTransactionNumber(transaction.getTransactionNumber())
                .withAmount(transaction.getAmount())
                .withRecipient(transaction.getRecipient())
                .withDescription(transaction.getDescription())
                .withSendingDate(formatter.format(transaction.getSendingDate()))
                .build();
    }
}