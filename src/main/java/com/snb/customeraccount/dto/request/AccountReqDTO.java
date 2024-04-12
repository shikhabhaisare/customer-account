package com.snb.customeraccount.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * The account request DTO containing the user information
 */
@Getter
@Setter
public class AccountReqDTO {

    @JsonProperty("customer_id")
    @NotNull
    private Long customerId;

    @JsonProperty("initial_credit")
    @NotNull
    @PositiveOrZero
    private BigDecimal initialCredit;

    private AccountReqDTO() {}

    public static Builder builder() {
        return new Builder();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public static class Builder {
        private Long customerId;
        private BigDecimal initialCredit;

        private Builder() {}

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder initialCredit(BigDecimal initialCredit) {
            this.initialCredit = initialCredit;
            return this;
        }

        public AccountReqDTO build() {
            AccountReqDTO accountReqDTO = new AccountReqDTO();
            accountReqDTO.customerId = this.customerId;
            accountReqDTO.initialCredit = this.initialCredit;
            return accountReqDTO;
        }
    }
}
