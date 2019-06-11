package com.silph.WebPhoto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.silph.WebPhoto.model.Album;
import com.silph.WebPhoto.model.Fotografo;
import com.silph.WebPhoto.model.Utente;

@Component
public class dbPopulation implements ApplicationRunner {

		@Autowired
		private UserRepository userRepository;
		@Autowired
		private FotografoRepository fotografoRepository;
		@Autowired
		private AlbumRepository albumRepository;
		
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
			
			Fotografo f1 = new Fotografo("Jacopo", "Mucci");
			fotografoRepository.save(f1);
			Album a1 = new Album("Città");
			Album a2 = new Album("Paesaggi");
			albumRepository.save(a1);
			albumRepository.save(a2);
		}
}
