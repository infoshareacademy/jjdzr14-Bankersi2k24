package com.isa.Bankersi2k24.models;

import com.isa.Bankersi2k24.DAO.FileName;
import com.isa.Bankersi2k24.DAO.Serializable;

import java.io.Serial;
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
    private Integer individualNumber_3;
    private Integer individualNumber_4;

    public BankAccountNumber() {

    }

    public void setBankAccountNumber(
            Integer controlSum,
            Integer sortCode,
            Integer individualNumber_1,
            Integer individualNumber_2,
            Integer individualNumber_3,
            Integer individualNumber_4) {
        this.controlSum = controlSum;
        this.sortCode = sortCode;
        this.individualNumber_1 = individualNumber_1;
        this.individualNumber_2 = individualNumber_2;
        this.individualNumber_3 = individualNumber_3;
        this.individualNumber_4 = individualNumber_4;
    }

    public Integer getControlSum() {
        return controlSum;
    }

    public void setControlSum(Integer controlSum) {
        this.controlSum = controlSum;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public Integer getIndividualNumber_1() {
        return individualNumber_1;
    }

    public void setIndividualNumber_1(Integer individualNumber_1) {
        this.individualNumber_1 = individualNumber_1;
    }

    public Integer getIndividualNumber_2() {
        return individualNumber_2;
    }

    public void setIndividualNumber_2(Integer individualNumber_2) {
        this.individualNumber_2 = individualNumber_2;
    }

    public Integer getIndividualNumber_3() {
        return individualNumber_3;
    }

    public void setIndividualNumber_3(Integer individualNumber_3) {
        this.individualNumber_3 = individualNumber_3;
    }

    public Integer getIndividualNumber_4() {
        return individualNumber_4;
    }

    public void setIndividualNumber_4(Integer individualNumber_4) {
        this.individualNumber_4 = individualNumber_4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountNumber that = (BankAccountNumber) o;
        return Objects.equals(controlSum, that.controlSum) && Objects.equals(sortCode, that.sortCode) && Objects.equals(individualNumber_1, that.individualNumber_1) && Objects.equals(individualNumber_2, that.individualNumber_2) && Objects.equals(individualNumber_3, that.individualNumber_3) && Objects.equals(individualNumber_4, that.individualNumber_4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controlSum, sortCode, individualNumber_1, individualNumber_2, individualNumber_3, individualNumber_4);
    }

    @Override
    public String toString() {
        return String.join(" ", controlSum.toString(),
                sortCode.toString(),
                individualNumber_1.toString(),
                individualNumber_2.toString(),
                individualNumber_3.toString(),
                individualNumber_4.toString());
    }
}
