package br.com.springboot.curso_jdev_treinamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.dto.LoginDTO;
import br.com.springboot.curso_jdev_treinamento.services.TokenService;
import br.com.springboot.curso_jdev_treinamento.users.Users;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public String login(@RequestBody LoginDTO login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.getLogin(), login.getPassword());

		
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		Users users = (Users) authenticate.getPrincipal();

		return tokenService.gerarToken(users);
	}

}
