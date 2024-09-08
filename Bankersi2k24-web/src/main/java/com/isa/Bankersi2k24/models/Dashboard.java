package com.isa.Bankersi2k24.models;


import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    private Integer numberOfAccounts;
    private List<BankAccount> accounts = new ArrayList<>();
    private Map<Currencies, BigDecimal> quotaPerCurrency = new HashMap<>();;
    private String userName;

}
