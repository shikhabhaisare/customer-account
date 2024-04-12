package com.snb.customeraccount.service.impl;

import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Transaction;
import com.snb.customeraccount.repository.TransactionRepository;
import com.snb.customeraccount.service.TransactionService;
import com.snb.customeraccount.util.ObjectConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final TransactionRepository transactionRepository;

    private final ObjectConverter objectSerializer;


    public TransactionServiceImpl(TransactionRepository transactionRepository, ObjectConverter objectSerializer) {
		super();
		this.transactionRepository = transactionRepository;
		this.objectSerializer = objectSerializer;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionDTO sendTransaction(Account account, BigDecimal amount, String description) {

        LOGGER.debug("sendTransaction - account:{} | amount:{}", account, amount);

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setRecipient(account.getAccountNumber());
        transaction.setAccount(account);
        transaction.setDescription(description);

        transaction = transactionRepository.save(transaction);

        return objectSerializer.toTransactionDTO(transaction);
    }
}
