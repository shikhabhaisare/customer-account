package com.snb.customeraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snb.customeraccount.model.Customer;

/**
 * The customer repository used to interact with the related DB table
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}