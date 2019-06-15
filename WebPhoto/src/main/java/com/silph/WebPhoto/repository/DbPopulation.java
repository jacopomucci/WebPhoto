package com.silph.WebPhoto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Photo;
import com.silph.WebPhoto.model.Photographer;
import com.silph.WebPhoto.model.Utente;

@Component
public class DbPopulation implements ApplicationRunner {

		@Autowired
		private UserRepository userRepository;
		@Autowired
		private PhotographerRepository fotografoRepository;
		@Autowired
		private AlbumRepository albumRepository;
		@Autowired
		private PhotoRepository fotoRepository;
		
		@Override
		public void run(ApplicationArguments agrs) throws Exception {
			this.deleteAll();
			this.addAll();
		}
		
		private void deleteAll() {
			userRepository.deleteAll();
			fotografoRepository.deleteAll();
			albumRepository.deleteAll();
		}
		
		private void addAll() {
			Utente us1 = new Utente("Mario", "mario@rossi.it", "password", "ADMIN");
			Utente us2 = new Utente("Giuseppe", "giuseppe@verdi.it", "password", "GUEST");
			userRepository.save(us1);
			userRepository.save(us2);
			
			Photographer f1 = new Photographer("MarioR", "Mario", "Rossi");
			fotografoRepository.save(f1);
			Photographer f2 = new Photographer("GSPV", "Giuseppe", "Verdi");
			fotografoRepository.save(f2);
			Album a1 = new Album("Città", f1);
			Album a2 = new Album("Paesaggi", f2);
			albumRepository.save(a1);
			albumRepository.save(a2);
			
			Photo photo1 = new Photo("Tramonto", "Un bel tramonto", f2, a2);
			fotoRepository.save(photo1);
			Photo photo2 = new Photo("Roma", "Una bella città", f1, a1);
			fotoRepository.save(photo2);
			Photo photo3 = new Photo("Milano", "Una bella città", f1, a1);
			fotoRepository.save(photo3);
		}
}
