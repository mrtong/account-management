package com.foo.accountmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionVO implements Serializable {
    @Id
    private Integer id;

    private Integer accountNumber;

    private String accountName;

    private String currency;

    private Date valueDate;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private String debitCredit;

    private String transactionNarrative;
}
