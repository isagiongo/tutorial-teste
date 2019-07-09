package com.isagiongo.tutorialdevsjava;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.isagiongo.tutorialdevsjava.models.Contact;
import com.isagiongo.tutorialdevsjava.services.ContactService;

@SpringBootApplication
public class TesteTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteTutorialApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(ContactService contactService) {
		return args -> {
			Stream.of("Ana Carolina", "Graziele", "Karine Cardoso", "Denise", "Tais", "Sindy", "Savanna", "Sabrina", "Marcelly", "Isabel", "Luana", "Tamara").forEach(name -> {
				Contact contact = new Contact ();
				contact.setName(name);
				contact.setEmail(name + "@gmail.com");
				contact.setPhone("9988-4455");
				contactService.save(contact);
			});
		};
	}
}