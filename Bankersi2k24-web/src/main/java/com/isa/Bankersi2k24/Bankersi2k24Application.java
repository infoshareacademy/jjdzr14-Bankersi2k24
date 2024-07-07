package com.isa.Bankersi2k24;

import com.isa.Bankersi2k24.models.BankAccount;
import com.isa.Bankersi2k24.models.BankAccountNumber;
import com.isa.Bankersi2k24.services.BankAccountService;
import com.isa.Bankersi2k24.services.UserService;
import com.isa.Bankersi2k24.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Bankersi2k24Application {

	public static void main(String[] args) {
		SpringApplication.run(Bankersi2k24Application.class, args);

		BankAccountService bankAccountService = new BankAccountService();

		BankAccount ba1 = bankAccountService.createNewBankAccount(1);
		BankAccount ba2 = bankAccountService.createNewBankAccount(2);
		String ba1n = ba1.getBankAccountNumber().toString();
		BankAccount tmp;

		bankAccountService.saveBankAccount(ba1);
		bankAccountService.saveBankAccount(ba2);

		bankAccountService = new BankAccountService();
		tmp = bankAccountService.getBankAccount(ba1n);
		System.out.printf("%s \n",tmp);

		tmp = bankAccountService.getBankAccount(ba2.getBankAccountNumber());

		BankAccount ba3 = bankAccountService.getBankAccount(ba1.getBankAccountNumber());
		System.out.printf("--");
	}

}
