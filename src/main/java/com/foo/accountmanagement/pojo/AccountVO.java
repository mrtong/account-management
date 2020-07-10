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
public class AccountVO implements Serializable {
    @Id
    private int id;

    private Integer accountNumber;

    private String accountName;

    private String accountType;

    private Date balanceDate;

    private String currency;

    private BigDecimal openingAvailableBalance;
}
