package com.react.example.cardatabase;

import com.react.example.cardatabase.domain.CarRepository;
import com.react.example.cardatabase.domain.OwnerRepository;
import com.react.example.cardatabase.domain.UserRepository;
import com.react.example.cardatabase.domain.entity.Car;
import com.react.example.cardatabase.domain.entity.Owner;
import com.react.example.cardatabase.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CardatabaseApplication {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Hello Spring Boot");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			//save data to db
			Owner owen = new Owner("Owen", "Willson");
			Owner john = new Owner("John", "Martin");
			ownerRepository.save(owen);
			ownerRepository.save(john);

			carRepository.save(
					new Car("Ford", "Mustang", "Red",
							"ADF-1121", 2017, 59000, owen)
			);
			carRepository.save(
					new Car("Nissan", "Leaf", "White",
							"SSJ-3002", 2014, 29000, owen)
			);
			carRepository.save(
					new Car("Toyota", "Prius", "Silver",
							"KKO-0212", 2018, 39000, john)
			);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("password");
			System.out.println(pass);

			userRepository.save(
					new User("user", pass, "USER")
			);
			userRepository.save(
					new User("admin", "admin", "ADMIN")
			);

		};
	}
}
