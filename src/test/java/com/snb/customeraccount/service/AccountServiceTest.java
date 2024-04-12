package com.snb.customeraccount.service;

import com.snb.customeraccount.dto.AccountDTO;
import com.snb.customeraccount.dto.TransactionDTO;
import com.snb.customeraccount.dto.request.AccountReqDTO;
import com.snb.customeraccount.exception.CustomerNotFoundException;
import com.snb.customeraccount.exception.ValueNotValidException;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Customer;
import com.snb.customeraccount.model.Transaction;
import com.snb.customeraccount.repository.AccountRepository;
import com.snb.customeraccount.repository.CustomerRepository;
import com.snb.customeraccount.service.impl.AccountServiceImpl;
import com.snb.customeraccount.service.impl.TransactionServiceImpl;
import com.snb.customeraccount.util.ObjectConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionServiceImpl transactionService;

    @InjectMocks
    AccountServiceImpl accountService;

    @Spy
    ObjectConverter objectSerializer;

    @Spy
    Validator validator;

    private Customer customer1;
    private Account account1;
    private List<Account> accountList1;
    private Transaction transaction1;
    private List<Transaction> transactionList1;

    private AccountReqDTO accountReqDTO1;
    private AccountReqDTO accountReqDTO2;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer();
        customer1.setName("testName");
        customer1.setSurname("testSurname");

        account1 = new Account();
        account1.setId(1L);
        account1.setBalance(BigDecimal.TEN);
        accountList1 = new ArrayList<>();
        accountList1.add(account1);

        transaction1 = new Transaction();
        transaction1.setAmount(BigDecimal.ONE);
        transaction1.setSendingDate(Instant.now());
        transactionList1 = new ArrayList<>();
        transactionList1.add(transaction1);

        account1.setTransactions(transactionList1);
        customer1.setAccounts(accountList1);

        accountReqDTO1 = AccountReqDTO.builder()
                .customerId(1L)
                .initialCredit(BigDecimal.TEN)
                .build();

        accountReqDTO2 = AccountReqDTO.builder()
                .customerId(100L)
                .initialCredit(BigDecimal.TEN)
                .build();
    }

    @Test
    public void whenOpenAccount_andParamIsValid_thenReturnAccount() {
        when(customerRepository.findById(accountReqDTO1.getCustomerId())).thenReturn(Optional.of(customer1));
        when(accountRepository.save(any(Account.class))).thenReturn(account1);
        when(transactionService.sendTransaction(any(Account.class),
                any(BigDecimal.class), anyString())).thenReturn(TransactionDTO.builder().build());
        AccountDTO accountDTO = accountService.openAccount(accountReqDTO1);

        assertEquals(accountDTO.getBalance(), accountReqDTO1.getInitialCredit());
    }

    @Test
    public void whenOpenAccount_andNotFound_thenTriggerException() {

        CustomerNotFoundException thrown = assertThrows(CustomerNotFoundException.class,
                () -> accountService.openAccount(accountReqDTO2));

        assertTrue(thrown.getMessage()
                .contains(String.format("Customer '%d' not found", accountReqDTO2.getCustomerId())));
    }

    @Test
    public void whenOpenAccount_andParamIsNotValid_thenTriggerException() {

        ValueNotValidException thrown = assertThrows(ValueNotValidException.class,
                () -> accountService.openAccount(null));

        assertTrue(thrown.getMessage().contains("The value is not valid"));
    }

    @Test
    public void whenOpenAccount_andExceptionIsTriggered_thenTriggerException() {

        when(accountRepository.save(any(Account.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> accountService.openAccount(accountReqDTO1));
    }
}
