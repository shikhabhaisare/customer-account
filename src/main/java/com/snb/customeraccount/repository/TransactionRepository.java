package com.snb.customeraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snb.customeraccount.model.Transaction;

/**
 * The transaction repository used to interact with the related DB table
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
