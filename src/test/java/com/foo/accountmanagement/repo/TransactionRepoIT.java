package com.foo.accountmanagement.repo;

import com.foo.accountmanagement.controller.util.MockDataGenerator;
import com.foo.accountmanagement.model.Transaction;
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
public class TransactionRepoIT {

    @Autowired
    private TransactionRepo transactionRepo;

    @Test
    public void shouldBeAbleToGetAllTransactions(){
        final List<Transaction> generateTransactions = MockDataGenerator.generateTransactions();
        transactionRepo.saveAll(generateTransactions);

        final List<Transaction> allFoundTransactions = transactionRepo.findAll();

        assertNotNull(allFoundTransactions);
        assertEquals(3, allFoundTransactions.size());

    }

    @Test
    public void shouldBeAbleToGetAllTransactionsByAccountNumber_whenFound(){
        final List<Transaction> generateTransactions = MockDataGenerator.generateTransactions();
        transactionRepo.saveAll(generateTransactions);

        final List<Transaction> allFoundTransactions = transactionRepo.findAllByAccountNumber(123);

        assertNotNull(allFoundTransactions);
        assertEquals(2, allFoundTransactions.size());

    }

    @Test
    public void shouldBeAbleToGetAllTransactionsByAccountNumber_whenNotFound(){
        final List<Transaction> generateTransactions = MockDataGenerator.generateTransactions();
        transactionRepo.saveAll(generateTransactions);

        final List<Transaction> allFoundTransactions = transactionRepo.findAllByAccountNumber(2);

        assertNotNull(allFoundTransactions);
        assertEquals(0, allFoundTransactions.size());

    }

}