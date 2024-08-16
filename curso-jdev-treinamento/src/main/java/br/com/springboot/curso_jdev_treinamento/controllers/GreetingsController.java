package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController { // Classe responsável por interceptação dos dados

	@Autowired // Injeção de dependência
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API:  " + name + "!";
	}

	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) { // Pega o nome digitado na URL

		Usuario usuario = new Usuario();
		usuario.setNome(nome); // Nome Digitado na URL

		usuarioRepository.save(usuario); // Gravar no banco de dados

		return "Olá mundo " + nome;

	}

	@GetMapping(value = "listatodos") // Primeiro método de API (método buscar todos)
	@ResponseBody // Retorna os dados para o corpo da resposta (vai retornar um JSON)
	public ResponseEntity<List<Usuario>> listaUsuarios() {

		List<Usuario> usuarios = usuarioRepository.findAll(); // Executa a consulta no banco de dados

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);

	}

	@PostMapping(value = "/salvar") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // Recebe os dados para salvar

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

}
