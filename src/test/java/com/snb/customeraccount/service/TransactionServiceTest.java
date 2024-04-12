package com.snb.customeraccount.service;

import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Transaction;
import com.snb.customeraccount.repository.TransactionRepository;
import com.snb.customeraccount.service.impl.TransactionServiceImpl;
import com.snb.customeraccount.util.ObjectConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Spy
    ObjectConverter objectSerializer;
    private Account account1;
    private Transaction transaction1;
    private List<Transaction> transactionList1;

    @BeforeEach
    public void setUp() {
        account1 = new Account();
        account1.setBalance(BigDecimal.ONE);

        transaction1 = new Transaction();
        transaction1.setTransactionNumber("test transaction number");
        transaction1.setRecipient("test recipient");
        transaction1.setAmount(BigDecimal.ONE);
        transaction1.setSendingDate(Instant.now());
        transactionList1 = new ArrayList<>();
        transactionList1.add(transaction1);

        account1.setTransactions(transactionList1);
    }

    @Test
    public void whenSendTransaction_andParamsAreValid_thenReturnTransactionDTO() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction1);
        TransactionDTO transactionDTO = transactionService.sendTransaction(account1,
                transaction1.getAmount(), "test description");

        assertEquals(transactionDTO.getAmount(), transaction1.getAmount());
    }

    @Test
    public void whenFetchCustomerInfo_andExceptionIsTriggered_thenTriggerException() {

        when(transactionRepository.save(any(Transaction.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () ->
                transactionService.sendTransaction(account1, BigDecimal.ONE, "test"));
    }
}
