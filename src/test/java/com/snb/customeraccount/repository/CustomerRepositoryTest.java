package com.snb.customeraccount.repository;

import com.snb.customeraccount.model.Customer;
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
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@WebAppConfiguration
@Transactional
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer();
        customer1.setName("testName");
        customer1.setSurname("testSurname");
    }

    @Test
    public void whenFindById_thenReturnCustomer() {

        entityManager.persist(customer1);
        entityManager.flush();

        Optional<Customer> found = customerRepository.findById(customer1.getId());

        Assert.assertNotNull(found.get());
        Assert.assertEquals(found.get().getName(), customer1.getName());
    }
}
