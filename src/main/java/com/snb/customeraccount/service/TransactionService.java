package com.snb.customeraccount.service;

import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.model.Account;

import java.math.BigDecimal;

/**
 * Transactions services
 *
 */
public interface TransactionService {

    /**
     * Service to send a transaction
     *
     * @param account     The customer account
     * @param amount      The transaction amount
     * @param description The transaction description
     * @return The transaction sent
     */
    TransactionDTO sendTransaction(Account account, BigDecimal amount, String description);

}
