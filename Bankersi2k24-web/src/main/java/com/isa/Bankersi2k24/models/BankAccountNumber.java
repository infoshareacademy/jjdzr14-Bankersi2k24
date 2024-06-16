package com.isa.Bankersi2k24.models;

import java.util.Objects;

public class BankAccountNumber {

    /**
     * CCAAAAAAAABBBBBBBBBBBBBBBB
     * CC - control sum
     * AAAA AAAA - sort code
     * BBBB BBBB BBBB BBBB - individual bank account number (in 2 parts)
     */
    private Integer controlSum;
    private Integer sortCode;
    private Integer individualNumber_1;
    private Integer individualNumber_2;

    public BankAccountNumber() {
    }

    public void setBankAccountNumber(Integer controlSum, Integer sortCode, Integer individualNumber_1, Integer individualNumber_2) {
        this.controlSum = controlSum;
        this.sortCode = sortCode;
        this.individualNumber_1 = individualNumber_1;
        this.individualNumber_2 = individualNumber_2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountNumber that = (BankAccountNumber) o;
        return controlSum == that.controlSum && sortCode == that.sortCode && individualNumber_1 == that.individualNumber_1 && individualNumber_2 == that.individualNumber_2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(controlSum, sortCode, individualNumber_1, individualNumber_2);
    }

    @Override
    public String toString() {
        return String.join(" ", controlSum.toString(),
                sortCode.toString(),
                individualNumber_1.toString(),
                individualNumber_2.toString());
    }
}
