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
			photographerRepository.deleteAll();
			albumRepository.deleteAll();
			photoRepository.deleteAll();
		}
		
		private void populateDB() {
			User admin = new User("Jacopo", "mucci.jacopo@gmail.com", null, "ADMIN");
			String adminPassword = new BCryptPasswordEncoder().encode("password");
			admin.setPassword(adminPassword);
			admin = this.userRepository.save(admin);	
			
			Photographer f1 = new Photographer("MarioR", "Mario", "Rossi");
			photographerRepository.save(f1);
			Photographer f2 = new Photographer("GSPV", "Giuseppe", "Verdi");
			photographerRepository.save(f2);
			Album a1 = new Album("Città", f1);
			Album a2 = new Album("Paesaggi", f2);
			albumRepository.save(a1);
			albumRepository.save(a2);
			
			Photo photo1 = new Photo("Tramonto", "Un bel tramonto", f2, a2);
			photoRepository.save(photo1);
			Photo photo2 = new Photo("Roma", "Una bella città", f1, a1);
			photoRepository.save(photo2);
			Photo photo3 = new Photo("Milano", "Una bella città", f1, a1);
			photoRepository.save(photo3);
		}
}
