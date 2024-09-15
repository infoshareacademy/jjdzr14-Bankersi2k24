package com.isa.Bankersi2k24;

import com.isa.Bankersi2k24.services.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Bankersi2k24Application {

	public static void main(String[] args) {
		SpringApplication.run(Bankersi2k24Application.class, args);
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.init();
	}

}
