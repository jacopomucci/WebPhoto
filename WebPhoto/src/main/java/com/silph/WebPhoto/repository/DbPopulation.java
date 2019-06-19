package com.silph.WebPhoto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.model.User;

@Component
public class DbPopulation implements ApplicationRunner {

		@Autowired
		private UserRepository userRepository;
		@Autowired
		private PhotographerRepository photographerRepository;
		@Autowired
		private AlbumRepository albumRepository;
		@Autowired
		private PhotoRepository photoRepository;
		
		@Override
		public void run(ApplicationArguments agrs) throws Exception {
			this.deleteAll();
			this.populateDB();
		}
		
		private void deleteAll() {
			userRepository.deleteAll();
		}
		
		private void populateDB() {
			User admin = new User("admin", "admin@silph.com", null, "ADMIN");
			String adminPassword = new BCryptPasswordEncoder().encode("password");
			admin.setPassword(adminPassword);
			admin = this.userRepository.save(admin);	
		}
}
