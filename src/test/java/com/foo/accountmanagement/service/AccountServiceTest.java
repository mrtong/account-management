package com.foo.accountmanagement.service;

import com.foo.accountmanagement.controller.util.MockDataGenerator;
import com.foo.accountmanagement.exception.AccountNotFoundException;
import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.pojo.AccountVO;
import com.foo.accountmanagement.repo.AccountRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    private AccountService accountService;
    @Mock
    private AccountRepo accountRepo;

    @Test
    public void shouldReturnAccountVOList() {
        final List<Account> accounts = MockDataGenerator.generateAccountList();

        when(accountRepo.findAll()).thenReturn(accounts);

        accountService = new AccountService(accountRepo);
        final List<AccountVO> allAccounts = accountService.findAllAccounts();

        assertNotNull(allAccounts);
        assertEquals(2, allAccounts.size());

        final AccountVO accountVO = allAccounts.get(0);
        assertNotNull(accountVO);
        assertTrue(accountVO instanceof  AccountVO);
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldRaiseExceptionWhenNoAccountsFound(){

        when(accountRepo.findAll()).thenReturn(new ArrayList<>());

        accountService = new AccountService(accountRepo);
        final List<AccountVO> allAccounts = accountService.findAllAccounts();
    }
}