package com.foo.accountmanagement.repo;

import com.foo.accountmanagement.controller.util.MockDataGenerator;
import com.foo.accountmanagement.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountRepoIT {
    @Autowired
    private AccountRepo accountRepo;

    @Test
    public void shouldBeAbleToReturnAllAccounts() {
        final Account generateAccount = MockDataGenerator.generateAccount();
        accountRepo.save(generateAccount);

        final List<Account> accounts = accountRepo.findAll();

        assertNotNull(accounts);
        assertEquals(1, accounts.size());

    }

    @Test
    public void shouldReturnAnEmptyListWhenNoAccountFound() {
        final List<Account> accounts = accountRepo.findAll();

        assertNotNull(accounts);
        assertEquals(0, accounts.size());

    }

}