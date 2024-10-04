package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, BigInteger> {
    public List<Transaction> findTransactionsBySenderBankAccountNumberIs(String bankAccountNumber);
}
