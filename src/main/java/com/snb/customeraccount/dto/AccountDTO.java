package com.snb.customeraccount.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

/**
 * The account DTO
 */

public class AccountDTO {
    @JsonIgnore
    private Long id;

    @JsonProperty("account_number")
    private String accountNumber;

    private BigDecimal balance;

    private List<TransactionDTO> transactions;

    // Private constructor to prevent direct instantiation
    private AccountDTO() {}

    public static class Builder {
        private Long id;
        private String accountNumber;
        private BigDecimal balance;
        private List<TransactionDTO> transactions;

        public Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder withTransactions(List<TransactionDTO> transactions) {
            this.transactions = transactions;
            return this;
        }

        public AccountDTO build() {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.id = this.id;
            accountDTO.accountNumber = this.accountNumber;
            accountDTO.balance = this.balance;
            accountDTO.transactions = this.transactions;
            return accountDTO;
        }
    }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * @return the transactions
	 */
	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

    
}
