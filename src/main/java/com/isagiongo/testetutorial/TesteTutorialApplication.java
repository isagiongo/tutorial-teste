package com.isagiongo.testetutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteTutorialApplication.class, args);
	}
	
//	@Bean
//    CommandLineRunner init(ContactService contactService) {
//        return args -> {
//        	contactService.deleteAll();
//            LongStream.range(1, 11)
//                    .mapToObj(i -> {
//                        Contact c = new Contact();
//                        c.setName("Contact " + i);
//                        c.setEmail("contact" + i + "@email.com");
//                        c.setPhone("(111) 111-1111");
//                        return c;
//                    })
//                    .map(v -> contactService.save(v))
//                    .forEach(System.out::println);
//        };

}
