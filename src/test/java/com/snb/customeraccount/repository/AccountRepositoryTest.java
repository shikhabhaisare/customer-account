package com.snb.customeraccount.repository;

import com.snb.customeraccount.model.Account;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@WebAppConfiguration
@Transactional
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    private Account account1;

    @BeforeEach
    public void setUp() {
        account1 = new Account();
        account1.setAccountNumber("test account");
        account1.setBalance(BigDecimal.TEN);
    }

    @Test
    public void whenStoreAccount_thenReturnAccount() {

        entityManager.persist(account1);
        entityManager.flush();

        Account stored = accountRepository.save(account1);

        Assert.assertNotNull(stored);
        Assert.assertEquals(stored.getAccountNumber(), account1.getAccountNumber());
    }
}
