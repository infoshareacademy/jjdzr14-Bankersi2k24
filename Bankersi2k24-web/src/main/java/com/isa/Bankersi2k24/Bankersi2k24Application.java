package com.isa.Bankersi2k24;

import com.isa.Bankersi2k24.services.UserService;
import com.isa.Bankersi2k24.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Bankersi2k24Application {

	public static void main(String[] args) {
		SpringApplication.run(Bankersi2k24Application.class, args);
		User u = new User();
		List<User> users = u.fetchAllObjects();
//
//		UserService.createNewUser(
//				"ccc",
//				"ccc",
//				"ccc@lvb.pl",
//				"ccc",
//				"ccc"
//		);
//		UserService.createNewUser(
//				"aaa",
//				"aaa",
//				"aaa@lvb.pl",
//				"aaa",
//				"aaa"
//		);
//		UserService.createNewUser(
//				"bbb",
//				"bbb",
//				"bbb@lvb.pl",
//				"bbb",
//				"bbb"
//		);


	}

}
