package com.isa.Bankersi2k24.repository;

import com.isa.Bankersi2k24.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.BitSet;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, BigInteger> {
}
