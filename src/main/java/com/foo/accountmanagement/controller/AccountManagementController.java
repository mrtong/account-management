package com.foo.accountmanagement.controller;

import com.foo.accountmanagement.pojo.AccountVO;
import com.foo.accountmanagement.pojo.TransactionVO;
import com.foo.accountmanagement.service.AccountService;
import com.foo.accountmanagement.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/")
public class AccountManagementController {
    private AccountService accountService;
    private TransactionService transactionService;

    public AccountManagementController(
            final AccountService accountService,
            final TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<List<AccountVO>> getAllAccounts() {
        final List<AccountVO> allAccounts = accountService.findAllAccounts();

        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<TransactionVO>> getAllTransactionsByAccountId(
            @RequestParam(value = "accountNumber") Integer accountNumber) {
        final List<TransactionVO> allTransactionsByAccountId
                = transactionService.findTransactionsByAccountNumber(accountNumber);

        return new ResponseEntity<>(allTransactionsByAccountId, HttpStatus.OK);
    }
}
