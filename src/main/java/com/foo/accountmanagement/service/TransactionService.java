package com.foo.accountmanagement.service;

import com.foo.accountmanagement.exception.TransactionNotFoundException;
import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.model.Transaction;
import com.foo.accountmanagement.pojo.TransactionVO;
import com.foo.accountmanagement.repo.AccountRepo;
import com.foo.accountmanagement.repo.TransactionRepo;
import com.foo.accountmanagement.util.VOEntityConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService {
    private TransactionRepo transactionRepo;
    private AccountRepo accountRepo;

    public TransactionService(
            final TransactionRepo transactionRepo,
            final AccountRepo accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    @CachePut(cacheNames = "find_All_Trx_By_accountNumber", key = "#accountNumber")
    public List<TransactionVO> findTransactionsByAccountNumber(final Integer accountNumber) {
        final Account account = accountRepo.findByAccountNumber(accountNumber);
        final List<Transaction> transactionList
                = transactionRepo.findAllByAccountNumber(accountNumber);
        if (transactionList.isEmpty()) {
            throw new TransactionNotFoundException("No Transaction found for this accountNumber " + accountNumber);
        }
        log.debug("Totally [{}] transactions were found.", transactionList.size());

        return transactionList
                .stream()
                .map(s -> VOEntityConvertor.entity2VO(s, account))
                .collect(Collectors.toList());
    }
}
