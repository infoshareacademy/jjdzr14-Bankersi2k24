package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.models.BankAccount;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, BigInteger> {
    @Query("select ba from BankAccount ba where ba.bankAccountNumber = ?1")
    List<BankAccount> findBankAccountByBankAccountNumber(String bankAccountNumber);
}
