package com.backEnd_w5_project.auth.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.backEnd_w5_project.auth.entity.Dispositivo;
import com.backEnd_w5_project.auth.entity.User;
import com.backEnd_w5_project.auth.entity.StatoDispositivo;
import com.backEnd_w5_project.auth.repository.DispositivoRepository;
import com.backEnd_w5_project.auth.repository.UserRepository;


import jakarta.persistence.EntityNotFoundException;

@Service
public class DispositivoService {
	@Autowired
	DispositivoRepository dRepo;
	@Autowired
	UserRepository uRepo;
	@Autowired
	@Qualifier("FakeDispositivo")
	private ObjectProvider<Dispositivo> fakeDispositivoProvider;
	@Autowired
	@Qualifier("ParamDispositivo")
	private ObjectProvider<Dispositivo> paramDispositivoProvider;
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CREA E SALVA DISPOSITIVO FAKE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void createAndSaveFakeDispositivi(int n) {
		for (int i = 0; i < n; i++) {
			saveDispositivo(fakeDispositivoProvider.getObject());
		}
	}
	
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< COLLEGA IL DISPOSITIVO ALL'UTENTE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String collegaDispositivoAdUser(Long id_dis,Long id_user) {
		if (!dRepo.existsById(id_dis) && dRepo.findById(id_dis).get().getStatoDispositivo() != StatoDispositivo.DISPONIBILE) {
			throw new EntityNotFoundException("Dispositivo non esistente");
		}else if(!uRepo.existsById(id_user)) {
			throw new EntityNotFoundException("User non esistente");
		}else {
			User u = uRepo.findById(id_user).get();
			Dispositivo d = dRepo.findById(id_dis).get();
			System.out.println("aggiungo dispositivo");
			d.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
			d.setUser(u);
			uRepo.save(u);
			dRepo.save(d);
			return "Dispositivo collegato con sucesso";
		}
	}
	
	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SALVA DISPOSITIVO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Dispositivo saveDispositivo(Dispositivo u) {
		dRepo.save(u);
		return u;
	}

	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MODIFICA DISPOSITIVO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void updateDispositivo(Dispositivo u) {
		if (!dRepo.existsById(u.getId())) {
			throw new EntityNotFoundException("Dispositivo non esistente");
		} else {
			dRepo.save(u);
		}
	}

	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TROVA DISPOSITIVO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Dispositivo findDispositivoById(Long id) {
		if (!dRepo.existsById(id)) {
			throw new EntityNotFoundException("Dispositivo non esistente");
		} else {
			return dRepo.findById(id).get();
		}
	}

	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TROVA TUTTI I DISPOSITIVO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public List<Dispositivo> findAllDispositivi() {
		return (List<Dispositivo>) dRepo.findAll();
	}

	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< RIMUOVI UTENTE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void removeUtente(Dispositivo u) {
		if (!dRepo.existsById(u.getId())) {
			throw new EntityNotFoundException("Dispositivo non esistente");
		} else {
			dRepo.delete(u);
		}
	}

	
//	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< RIMUOVI DISPOSITIVO PER ID >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String removeDispositivoById(Long id) {
		if (!dRepo.existsById(id)) {
			throw new EntityNotFoundException("Dispositivo non esistente");
		} else {
			dRepo.deleteById(id);
			return "Dispositivo eliminato";
		}
	}
}