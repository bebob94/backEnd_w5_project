package com.backEnd_w5_project.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd_w5_project.auth.entity.User;
import com.backEnd_w5_project.auth.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> allUtenti() {
		List<User> listaUtenti = userService.findAllUtente();
		ResponseEntity<List<User>> resp = new ResponseEntity<List<User>>(listaUtenti, HttpStatus.OK);
		return resp;
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> idUtenti(@PathVariable Long id) {
		User user = userService.findUtenteById(id);
		ResponseEntity<User> resp = new ResponseEntity<User>(user, HttpStatus.OK);
		return resp;
	}


	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		return new ResponseEntity<String>(userService.removeUtenteById(id), HttpStatus.OK);
	}
}