package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.models.BankAccount;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, BigInteger> {
}
