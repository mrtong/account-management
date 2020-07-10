package com.foo.accountmanagement.controller.util;

import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.model.CreditDebitEnum;
import com.foo.accountmanagement.model.Transaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class MockDataGenerator {

    private MockDataGenerator() {
    }
    public static Account generateAccount(){
        final Account account = Account.builder()
                .accountName("ANZ")
                .accountType("owner")
                .balanceDate(new Date())
                .currency("AUD")
                .openingAvailableBalance(new BigDecimal("12.34"))
                .accountNumber(123)
                .build();

        return  account;
    }
    public static List<Account> generateAccountList(){
        final Account account1 = Account.builder()
                .accountName("ANZ")
                .accountType("owner")
                .balanceDate(new Date())
                .currency("AUD")
                .openingAvailableBalance(new BigDecimal("12.34"))
                .accountNumber(123)
                .build();
        final Account account2 = Account.builder()
                .accountName("Google")
                .accountType("vendor")
                .balanceDate(new Date())
                .currency("USD")
                .openingAvailableBalance(new BigDecimal("12.34"))
                .accountNumber(321)
                .build();

        return  Arrays.asList(account1,account2);
    }

    public static List<Transaction> generateTransactions(){
        final Transaction transaction1 = Transaction.builder()
                .transactionNarrative("")
                .id(1)
                .creditAmount(new BigDecimal("12.3"))
                .debitAmount(new BigDecimal("44.1"))
                .debitCredit(CreditDebitEnum.Credit.toString())
                .valueDate(new Date())
                .accountNumber(1)
                .build();

        final Transaction transaction2 = Transaction.builder()
                .transactionNarrative("")
                .id(2)
                .creditAmount(new BigDecimal("12.3"))
                .debitAmount(new BigDecimal("44.1"))
                .debitCredit(CreditDebitEnum.Credit.toString())
                .valueDate(new Date())
                .accountNumber(123)
                .build();

        final Transaction transaction3 = Transaction.builder()
                .transactionNarrative("")
                .id(3)
                .creditAmount(new BigDecimal("121.3"))
                .debitAmount(new BigDecimal("3.1"))
                .debitCredit(CreditDebitEnum.Credit.toString())
                .valueDate(new Date())
                .accountNumber(123)
                .build();

        return Arrays.asList(transaction1, transaction2, transaction3);
    }

    public static List<Transaction> generateTransactionsSameAccountNumber(){
        final Transaction transaction2 = Transaction.builder()
                .transactionNarrative("")
                .id(2)
                .creditAmount(new BigDecimal("12.3"))
                .debitAmount(new BigDecimal("44.1"))
                .debitCredit(CreditDebitEnum.Credit.toString())
                .valueDate(new Date())
                .accountNumber(123)
                .build();

        final Transaction transaction3 = Transaction.builder()
                .transactionNarrative("")
                .id(3)
                .creditAmount(new BigDecimal("121.3"))
                .debitAmount(new BigDecimal("3.1"))
                .debitCredit(CreditDebitEnum.Credit.toString())
                .valueDate(new Date())
                .accountNumber(123)
                .build();

        return Arrays.asList(transaction2, transaction3);
    }
}
