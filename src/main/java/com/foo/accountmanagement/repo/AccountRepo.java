package com.foo.accountmanagement.repo;

import com.foo.accountmanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    //assume in the accounts table, account_number is unique
    Account findByAccountNumber(int accountNumber);
}
