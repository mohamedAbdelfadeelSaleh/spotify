package com.example.Spotify;

import com.example.Spotify.enums.Role;
//import com.example.Spotify.model.User;
import com.example.Spotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpotifyApplication  {
	public static void main(String[] args) {
		SpringApplication.run(SpotifyApplication.class, args);
	}
}


//
//@SpringBootApplication
//public class SpotifyApplication implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpotifyApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) {
//		if (userRepository.findByRole(Role.ADMIN) == null) {
//			User admin = new User();
//			admin.setEmail("admin@gmail.com");
//			admin.setFirstName("admin");
//			admin.setLastName("admin");
//			admin.setRole(Role.ADMIN);
//			admin.setPassword(passwordEncoder.encode("admin"));
//			userRepository.save(admin);
//		}
//	}
//}