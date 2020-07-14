package com.foo.accountmanagement.service;

import com.foo.accountmanagement.exception.AccountNotFoundException;
import com.foo.accountmanagement.exception.SystemBusyException;
import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.pojo.AccountVO;
import com.foo.accountmanagement.repo.AccountRepo;
import com.foo.accountmanagement.util.VOEntityConvertor;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountService {
    private AccountRepo accountRepo;

    public AccountService(final AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @CachePut(cacheNames = "find_All_Accounts")
    @HystrixCommand(fallbackMethod = "timeoutInFindAllAccounts")
    public List<AccountVO> findAllAccounts() {
        final List<Account> accountList = accountRepo.findAll();
        if (accountList.isEmpty()) {
            throw new AccountNotFoundException("No Account was found.");
        }

        log.info("Totally found [{}] accounts.", accountList.size());
        final List<AccountVO> accountVOList =
                accountList.stream().map(s -> VOEntityConvertor.entity2VO(s)).collect(Collectors.toList());

        return accountVOList;
    }

    public List<AccountVO> timeoutInFindAllAccounts() {
        throw new SystemBusyException("The system is busy at the moment, maybe try it later.");
    }

}
