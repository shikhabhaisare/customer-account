package com.snb.customeraccount.service;

import com.snb.customeraccount.dto.AccountDTO;
import com.snb.customeraccount.dto.request.AccountReqDTO;

/**
 * Account services
 *
 */
public interface AccountService {

    /**
     * Service to open an account
     *
     * @param accountReqDTO The customer information to open the account
     * @return the created account
     */
    AccountDTO openAccount(AccountReqDTO accountReqDTO);
}
