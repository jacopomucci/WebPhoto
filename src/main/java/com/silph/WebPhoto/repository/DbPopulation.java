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
			
			
			Photographer ph1 = new Photographer("MarioR", "Mario", "Rossi", "Appassionato di viaggi");
			ph1 = this.photographerRepository.save(ph1);
			Photographer ph2 = new Photographer("LuigiB", "Luigi", "Bianchi", "Appassionato della natura");
			ph2 = this.photographerRepository.save(ph2);
			
			Album a1 = new Album("Città", ph1);
			a1 = this.albumRepository.save(a1);
			Album a2 = new Album("Natura", ph2);
			a2 = this.albumRepository.save(a2);
			Album a3 = new Album("Animali", ph2);
			a3 = this.albumRepository.save(a3);
			
			Photo photo1 = new Photo("New York",
					"Manhattan, il suo cuore pulsante, è considerato uno dei poli commerciali, finanziari e culturali più importanti al mondo.",
					ph1, a1, 20);
			photo1.setFileName("new_york.jpg");
			photo1 = this.photoRepository.save(photo1);
			
			Photo photo2 = new Photo("Roma",
					"Roma, capitale dell’Italia, è una grande città cosmopolita con una storia artistica, architettonica e culturale che ha influenzato tutto il mondo e che risale a quasi 3000 anni fa.",
					ph1, a1, 51);
			photo2.setFileName("roma.jpg");
			photo2 = this.photoRepository.save(photo2);
			
			Photo photo3 = new Photo("Tokyo", "La grande capitale orientale", ph1, a1, 11);
			photo3.setFileName("tokyo.jpg");
			photo3 = this.photoRepository.save(photo3);
			
			Photo photo5 = new Photo("Gran Canyon", "La grande gola", ph2, a2, 30);
			photo5.setFileName("canyon.jpg");
			photo5 = this.photoRepository.save(photo5);
			
			Photo photo6 = new Photo("Aurora Boreale", "Il grande fenomeno ottico dell'atmosfera terrestre", ph2, a2, 45);
			photo6.setFileName("aurora_boreale.jpg");
			photo6 = this.photoRepository.save(photo6);
			
			Photo photo7 = new Photo("La Crystal Cave", "Il magico cristallo blu!", ph2, a2, 51);
			photo7.setFileName("crystal_cave.jpg");
			photo7 = this.photoRepository.save(photo7);
			
			Photo photo8 = new Photo("Pinguini", "La marcia dei pinguini", ph2, a3, 20);
			photo8.setFileName("pinguini.jpg");
			photo8 = this.photoRepository.save(photo8);
			
			Photo photo9 = new Photo("Il leone", "Il re della Savana", ph2, a3, 25);
			photo9.setFileName("leone.jpg");
			photo9 = this.photoRepository.save(photo9);
			
			Photo photo10 = new Photo("Il panda", "Un animale quasi in esistinzione...", ph2, a3, 28);
			photo10.setFileName("panda.jpg");
			photo10 = this.photoRepository.save(photo10);
			
		}
}
