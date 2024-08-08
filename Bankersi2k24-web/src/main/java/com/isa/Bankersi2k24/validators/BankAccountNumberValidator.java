package com.isa.Bankersi2k24.validators;

import com.isa.Bankersi2k24.interfaces.BankAccountNumberConstraint;
import com.isa.Bankersi2k24.services.BankAccountNumberService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

import static com.isa.Bankersi2k24.services.BankAccountNumberService.bankAccountRegexp;

public class BankAccountNumberValidator implements ConstraintValidator <BankAccountNumberConstraint, String> {
    @Override
    public void initialize(BankAccountNumberConstraint bankAccountNumber) {
    }

    @Override
    public boolean isValid(String banField, ConstraintValidatorContext constraintValidatorContext) {
        return banField != null &&
                banField.length() >= 26 &&
                banField.matches( bankAccountRegexp);
    }
}
