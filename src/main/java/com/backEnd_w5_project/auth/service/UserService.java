package com.backEnd_w5_project.auth.service;


import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.backEnd_w5_project.auth.entity.User;
import com.backEnd_w5_project.auth.repository.UserRepository;



import jakarta.persistence.EntityNotFoundException;
@Service
public class UserService {
	@Autowired
	UserRepository uRepo;
	@Autowired
	@Qualifier("FakeUser")
	private ObjectProvider<User> fakeUtenteProvider;
	
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CREA E SALVA UTENTE FAKE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void createAndSaveFakeUtente(int n) {
		for (int i = 0; i < n; i++) {
			
			saveUser(fakeUtenteProvider.getObject());
		}
	}
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SALVA UTENTE FAKE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void saveUser(User u) {
		uRepo.save(u);
	}

//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICA UTENTE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void updateUtente(User u) {
		if (!uRepo.existsById(u.getId())) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.save(u);
		}
	}
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CERCA UTENTE PER ID>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public User findUtenteById(Long id) {
		if (!uRepo.existsById(id)) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			return uRepo.findById(id).get();
		}
	}

//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CERCA TUTTI GLI UTENTI>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public List<User> findAllUtente() {
		return (List<User>) uRepo.findAll();
	}

//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< RIMUOVI UTENTE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void removeUtente(User u) {
		if (!uRepo.existsById(u.getId())) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.delete(u);
		}
	}

//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< RIMUOVI UTENTE PER ID >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String removeUtenteById(Long id) {
		if (!uRepo.existsById(id)) {
			throw new EntityNotFoundException("User not exists!!!");
		} else {
			uRepo.deleteById(id);
			return "Utente eliminato";
		}
	}
}