package com.snb.customeraccount.util;

import java.util.Random;

public class AccountUtils {

    public static String accountNumberGenerator() {
        Random rand = new Random();
        StringBuilder accountNumber = new StringBuilder("ABN");
        for (int i = 0; i < 10; i++)
        {
            int digit = rand.nextInt(10);
            accountNumber.append((digit));
        }
        return accountNumber.toString();
    }
}