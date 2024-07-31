package com.isa.Bankersi2k24.models;

import java.util.Objects;

public class BankAccountNumber {

    /**
     * CCAAAAAAAABBBBBBBBBBBBBBBB
     * CC - control sum
     * AAAA AAAA - sort code
     * BBBB BBBB BBBB BBBB - individual bank account number (in 2 parts)
     */
    private Integer controlSum =0;
    private Integer sortCode = 0 ;
    private Integer sortCode_2 = 0;
    private Integer individualNumber_1 = 0;
    private Integer individualNumber_2 = 0;
    private Integer individualNumber_3 = 0;
    private Integer individualNumber_4 = 0;

    public BankAccountNumber() {
    }

    public void setBankAccountNumber(
            Integer controlSum,
            Integer sortCode,
            Integer individualNumber_1,
            Integer individualNumber_2,
            Integer individualNumber_3,
            Integer individualNumber_4,
            Integer sortCode_2) {
        this.controlSum = controlSum;
        this.sortCode = sortCode;
        this.individualNumber_1 = individualNumber_1;
        this.individualNumber_2 = individualNumber_2;
        this.individualNumber_3 = individualNumber_3;
        this.individualNumber_4 = individualNumber_4;
        this.sortCode_2 = sortCode_2;
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

    public Integer getSortCode_2() {
        return sortCode_2;
    }

    public void setSortCode_2(Integer sortCode_2) {
        this.sortCode_2 = sortCode_2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountNumber that = (BankAccountNumber) o;
        return Objects.equals(getControlSum(), that.getControlSum()) && Objects.equals(getSortCode(), that.getSortCode()) && Objects.equals(getIndividualNumber_1(), that.getIndividualNumber_1()) && Objects.equals(getIndividualNumber_2(), that.getIndividualNumber_2()) && Objects.equals(getIndividualNumber_3(), that.getIndividualNumber_3()) && Objects.equals(getIndividualNumber_4(), that.getIndividualNumber_4()) && Objects.equals(getSortCode_2(), that.getSortCode_2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getControlSum(), getSortCode(), getIndividualNumber_1(), getIndividualNumber_2(), getIndividualNumber_3(), getIndividualNumber_4(), getSortCode_2());
    }

    @Override
    public String toString() {
        return String.join(" ", String.format("%02d", controlSum),
                String.format("%04d", sortCode),
                String.format("%04d", sortCode_2),
                String.format("%04d", individualNumber_1),
                String.format("%04d", individualNumber_2),
                String.format("%04d", individualNumber_3),
                String.format("%04d", individualNumber_4));
    }
}
