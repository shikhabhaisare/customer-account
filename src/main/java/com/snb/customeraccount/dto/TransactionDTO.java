package com.snb.customeraccount.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * The transaction DTO
 */
public class TransactionDTO {
    @JsonProperty("transaction_number")
    private String transactionNumber;

    private BigDecimal amount;

    private String recipient;

    private String description;

    @JsonProperty("sending_date")
    private String sendingDate;

    // Private constructor to prevent direct instantiation
    private TransactionDTO() {}
    
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String transactionNumber;
        private BigDecimal amount;
        private String recipient;
        private String description;
        private String sendingDate;

        public Builder() {}

        public Builder withTransactionNumber(String transactionNumber) {
            this.transactionNumber = transactionNumber;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withSendingDate(String sendingDate) {
            this.sendingDate = sendingDate;
            return this;
        }

        public TransactionDTO build() {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.transactionNumber = this.transactionNumber;
            transactionDTO.amount = this.amount;
            transactionDTO.recipient = this.recipient;
            transactionDTO.description = this.description;
            transactionDTO.sendingDate = this.sendingDate;
            return transactionDTO;
        }
    }

    // Getters for fields
    public String getTransactionNumber() {
        return transactionNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getDescription() {
        return description;
    }

    public String getSendingDate() {
        return sendingDate;
    }
    
    
}
