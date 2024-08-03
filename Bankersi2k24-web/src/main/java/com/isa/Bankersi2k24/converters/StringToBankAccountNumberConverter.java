package com.isa.Bankersi2k24.converters;

import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBankAccountNumberConverter implements Converter<String, BankAccountNumber> {

    @Override
    public BankAccountNumber convert(String source) {
        return BankAccountNumberService.accountNumberStringToBan(source);
    }
}
