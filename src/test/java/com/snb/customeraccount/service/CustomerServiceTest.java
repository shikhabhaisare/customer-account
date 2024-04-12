package com.snb.customeraccount.service;

import com.snb.customeraccount.dto.CustomerDTO;
import com.snb.customeraccount.exception.CustomerNotFoundException;
import com.snb.customeraccount.exception.ValueNotValidException;
import com.snb.customeraccount.model.Account;
import com.snb.customeraccount.model.Customer;
import com.snb.customeraccount.model.Transaction;
import com.snb.customeraccount.repository.CustomerRepository;
import com.snb.customeraccount.service.impl.CustomerServiceImpl;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Spy
    ObjectConverter objectSerializer;

    private Customer customer1;
    private Account account1;
    private List<Account> accountList1;
    private Transaction transaction1;
    private List<Transaction> transactionList1;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer();
        customer1.setName("testName");
        customer1.setSurname("testSurname");

        account1 = new Account();
        account1.setBalance(BigDecimal.ONE);
        accountList1 = new ArrayList<>();
        accountList1.add(account1);

        transaction1 = new Transaction();
        transaction1.setAmount(BigDecimal.ONE);
        transaction1.setSendingDate(Instant.now());
        transactionList1 = new ArrayList<>();
        transactionList1.add(transaction1);

        account1.setTransactions(transactionList1);
        customer1.setAccounts(accountList1);
    }

    @Test
    public void whenFetchCustomerInfo_andParamIsValid_thenReturnCustomerInfo() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));
        CustomerDTO customerDTO = customerService.fetchCustomerInfo(1L);

        assertEquals(customerDTO.getName(), customer1.getName());
    }

    @Test
    public void whenFetchCustomerInfo_andNotFound_thenTriggerException() {

        Long customerId = 100L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        CustomerNotFoundException thrown = assertThrows(CustomerNotFoundException.class,
                () -> customerService.fetchCustomerInfo(customerId));

        assertTrue(thrown.getMessage().contains(String.format("Customer '%d' not found", customerId)));
    }

    @Test
    public void whenFetchCustomerInfo_andParamIsNotValid_thenTriggerException() {

        ValueNotValidException thrown = assertThrows(ValueNotValidException.class,
                () -> customerService.fetchCustomerInfo(null));

        assertTrue(thrown.getMessage().contains("The value is not valid"));
    }

    @Test
    public void whenFetchCustomerInfo_andExceptionIsTriggered_thenTriggerException() {

        when(customerRepository.save(any(Customer.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> customerService.fetchCustomerInfo(1L));
    }
}
