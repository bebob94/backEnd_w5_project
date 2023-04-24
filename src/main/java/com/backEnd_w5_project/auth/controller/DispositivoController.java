package com.backEnd_w5_project.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd_w5_project.auth.entity.Dispositivo;
import com.backEnd_w5_project.auth.service.DispositivoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
	
	@Autowired
	DispositivoService dispositivoService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Dispositivo>> alldispositivo( ){
		List<Dispositivo> listaDispositivi =  dispositivoService.findAllDispositivi();
		ResponseEntity<List<Dispositivo>> resp = new ResponseEntity<List<Dispositivo>>(listaDispositivi,HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Dispositivo> idDispositivo(@PathVariable Long id){
		Dispositivo dispositivo =  dispositivoService.findDispositivoById(id);
		ResponseEntity<Dispositivo> resp = new ResponseEntity<Dispositivo>(dispositivo,HttpStatus.OK);
		return resp;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<Dispositivo> createDispositivo(@RequestBody Dispositivo u){
		return new ResponseEntity<Dispositivo>( dispositivoService.saveDispositivo(u),HttpStatus.CREATED);
	}
	
	@PostMapping("/{id_d}/{id_u}")	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> collegaDispositivoAdUtente(@PathVariable Long id_d,@PathVariable Long id_u){
		return new ResponseEntity<String>( dispositivoService.collegaDispositivoAdUser(id_d, id_u),HttpStatus.CREATED);
	}
}