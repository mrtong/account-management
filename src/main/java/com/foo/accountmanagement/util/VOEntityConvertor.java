package com.foo.accountmanagement.util;

import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.model.Transaction;
import com.foo.accountmanagement.pojo.AccountVO;
import com.foo.accountmanagement.pojo.TransactionVO;

public class VOEntityConvertor {

    private VOEntityConvertor() {
    }

    public static TransactionVO entity2VO(
            final Transaction transaction,
            final Account account){
        return TransactionVO.builder()
                .accountName(account.getAccountName())
                .accountNumber(account.getAccountNumber())
                .creditAmount(transaction.getCreditAmount())
                .currency(account.getCurrency())
                .debitAmount(transaction.getDebitAmount())
                .debitCredit(transaction.getDebitCredit())
                .id(transaction.getId())
                .transactionNarrative(transaction.getTransactionNarrative())
                .valueDate(transaction.getValueDate())
                .build();
    }

    public static AccountVO entity2VO(final Account account){
        return AccountVO.builder()
                .accountName(account.getAccountName())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balanceDate(account.getBalanceDate())
                .currency(account.getCurrency())
                .id(account.getId())
                .openingAvailableBalance(account.getOpeningAvailableBalance())
                .build();
    }
}
