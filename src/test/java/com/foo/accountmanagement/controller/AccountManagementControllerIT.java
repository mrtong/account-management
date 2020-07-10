package com.foo.accountmanagement.controller;

import com.foo.accountmanagement.controller.util.MockDataGenerator;
import com.foo.accountmanagement.model.Account;
import com.foo.accountmanagement.model.Transaction;
import com.foo.accountmanagement.pojo.AccountVO;
import com.foo.accountmanagement.pojo.TransactionVO;
import com.foo.accountmanagement.service.AccountService;
import com.foo.accountmanagement.service.TransactionService;
import com.foo.accountmanagement.util.VOEntityConvertor;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AccountManagementControllerIT {
    @InjectMocks
    private AccountManagementController mockedController;

    private MockMvc mvc;

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountService accountService;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(mockedController).build();
    }

    @Test
    public void givenAccountID_whenFoundTransaction_thenReturnAllFoundTransactions() throws Exception {
        final List<Transaction> generateTransactions = MockDataGenerator.generateTransactionsSameAccountNumber();
        final Account account = MockDataGenerator.generateAccount();
        final List<TransactionVO> transactionVOList =
                generateTransactions.stream().map(s -> VOEntityConvertor.entity2VO(s, account)).collect(Collectors.toList());
        when(transactionService.findTransactionsByAccountNumber(anyInt())).thenReturn(transactionVOList);

        final MvcResult mvcResult = mvc.perform(get("/transactions?accountNumber=12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString();
        Gson gson = new Gson();
        final List<TransactionVO> returnedTransactions = gson.fromJson(response, List.class);

        assertNotNull(returnedTransactions);
        assertEquals(2, returnedTransactions.size());


    }

    @Test
    public void shouldBeAbleToListAllAccounts() throws Exception {
        final List<Account> accountList = MockDataGenerator.generateAccountList();
        final List<AccountVO> accountVOList = accountList.stream().map(s -> VOEntityConvertor.entity2VO(s)).collect(Collectors.toList());
        when(accountService.findAllAccounts()).thenReturn(accountVOList);
        final MvcResult mvcResult = mvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final String response = mvcResult.getResponse().getContentAsString();
        Gson gson = new Gson();
        final List<AccountVO> returnedTransactions = gson.fromJson(response, List.class);
    }

    @Test
    public void givenAccountID_whenNotFound_thenRaiseException() throws Exception {
        when(transactionService.findTransactionsByAccountNumber(anyInt())).thenReturn(new ArrayList<>());

        final MvcResult mvcResult = mvc.perform(get("/transactions?accountNumber=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        final String response = mvcResult.getResponse().getContentAsString();
        Gson gson = new Gson();
        final List<TransactionVO> returnedTransactions = gson.fromJson(response, List.class);

        assertNotNull(returnedTransactions);
        assertEquals(0, returnedTransactions.size());
    }
}