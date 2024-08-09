package com.isa.Bankersi2k24.interfaces;


import com.isa.Bankersi2k24.validators.BankAccountNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BankAccountNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BankAccountNumberConstraint {
    String message() default "Invalid account number format, required: CC AAAA AAAA BBBB BBBB BBBB BBBB";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
