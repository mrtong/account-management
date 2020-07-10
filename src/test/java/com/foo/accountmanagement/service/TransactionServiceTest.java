package com.foo.accountmanagement.service;

import com.foo.accountmanagement.controller.util.MockDataGenerator;
import com.foo.accountmanagement.exception.TransactionNotFoundException;
import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.model.Transaction;
import com.foo.accountmanagement.pojo.TransactionVO;
import com.foo.accountmanagement.repo.AccountRepo;
import com.foo.accountmanagement.repo.TransactionRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    private TransactionService transactionService;

    @Mock
    private TransactionRepo transactionRepo;

    @Mock
    private AccountRepo accountRepo;

    @Test
    public void shouldReturnTransactionVOListWhenAccountNumberFound() {
        final Account account = MockDataGenerator.generateAccount();
        final List<Transaction> transactionList =
                MockDataGenerator.generateTransactionsSameAccountNumber();

        when(transactionRepo.findAllByAccountNumber(anyInt())).thenReturn(transactionList);
        when(accountRepo.findByAccountNumber(anyInt())).thenReturn(account);

        transactionService = new TransactionService(transactionRepo, accountRepo);

        final List<TransactionVO> transactionsByAccountNumber = transactionService.findTransactionsByAccountNumber(1);

        assertNotNull(transactionsByAccountNumber);
        assertEquals(2, transactionsByAccountNumber.size());

        final TransactionVO transactionVO = transactionsByAccountNumber.get(0);

        assertNotNull(transactionVO);
        assertTrue(transactionVO instanceof TransactionVO);

    }

    @Test(expected = TransactionNotFoundException.class)
    public void shouldReturnTransactionVOListEvenAccountNumberNotFound() {
        final Account account = MockDataGenerator.generateAccount();
        final List<Transaction> transactionList =
                MockDataGenerator.generateTransactionsSameAccountNumber();

        when(transactionRepo.findAllByAccountNumber(anyInt())).thenReturn(new ArrayList<>());
        when(accountRepo.findByAccountNumber(anyInt())).thenReturn(account);

        transactionService = new TransactionService(transactionRepo, accountRepo);

        final List<TransactionVO> transactionsByAccountNumber = transactionService.findTransactionsByAccountNumber(1);

    }
}