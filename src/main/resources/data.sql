insert into ACCOUNTS (account_name,account_number,account_type,balance_date,currency,opening_available_balance) values('AUSaving123',12345,'Savings',{ts '2019-09-17 18:47:52.69'},'AUD',42112);
insert into ACCOUNTS (account_name,account_number,account_type,balance_date,currency,opening_available_balance) values('SGSaving123',54321,'Savings',{ts '2020-01-2 18:47:52.69'},'HKD',6542);

insert into TRANSACTIONS (account_number,credit_amount,debit_amount,debit_credit, value_date) values (12345,12.32,1.11,'Debit',{ts '2019-09-17 18:47:52.69'});
insert into TRANSACTIONS (account_number,credit_amount,debit_amount,debit_credit, value_date) values (12345,321.1,2.1,'Debit',{ts '2016-12-11 18:47:52.69'});
insert into TRANSACTIONS (account_number,credit_amount,debit_amount,debit_credit, value_date) values (12345,213,213.1,'Credit',{ts '2020-12-11 18:47:52.69'});
