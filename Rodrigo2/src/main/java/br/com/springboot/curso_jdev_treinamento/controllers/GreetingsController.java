package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Conta;
import br.com.springboot.curso_jdev_treinamento.model.Historico;
import br.com.springboot.curso_jdev_treinamento.model.Membros;
import br.com.springboot.curso_jdev_treinamento.model.MembrosDTO;
import br.com.springboot.curso_jdev_treinamento.model.Mes;
import br.com.springboot.curso_jdev_treinamento.repository.ContaRepository;
import br.com.springboot.curso_jdev_treinamento.repository.HistoricoRepository;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;
import br.com.springboot.curso_jdev_treinamento.utilidades.Utilidades;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController { // Classe responsável por interceptação dos dados

	@Autowired // Injeção de dependência
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	private HistoricoRepository historicoRepository;

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

		Membros membros = new Membros();
		membros.setNome(nome); // Nome Digitado na URL

		usuarioRepository.save(membros); // Gravar no banco de dados

		return "Olá mundo " + nome;

	}

	// -------------------------------------------------------------------------------------------------

	// Método Salvar
	@PostMapping(value = "/salvar") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<Membros> salvar(@RequestBody Membros usuario) { // Recebe os dados para salvar

		Membros user = usuarioRepository.save(usuario);

		return new ResponseEntity<Membros>(user, HttpStatus.CREATED);

	}

	// -------------------------------------------------------------------------------------------------

	// Método Salvar
	@PostMapping(value = "/historico") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<Historico> salvarHistorico(@RequestBody Historico hist) { // Recebe os dados para salvar

		Historico historico = historicoRepository.save(hist);

		return new ResponseEntity<Historico>(historico, HttpStatus.CREATED);

	}

	// --------------------------------------------------------------------------------------------------

	// Lista todos os dados do Banco em ordem alfabética
	@GetMapping(value = "/listatodos") // Primeiro método de API (método buscar todos)
	@ResponseBody // Retorna os dados para o corpo da resposta (vai retornar um JSON)
	public ResponseEntity<List<MembrosDTO>> listaUsuarios() {

		List<Membros> listaM = usuarioRepository.findAll(); // Executa a consulta no banco de dados

		List<MembrosDTO> listaDTO = listaM.stream().map(membros -> new MembrosDTO(membros.getId(), membros.getNome(),
				utilidades.dataFormater(membros.getDataNasc()))).collect(Collectors.toList());

		listaDTO = utilidades.ordenacao(listaDTO);

		return new ResponseEntity<List<MembrosDTO>>(listaDTO, HttpStatus.OK);

	}

	// --------------------------------------------------------------------------------------------------

	// Lista Aniversarios do mês
	@GetMapping(value = "/nivermes") // Primeiro método de API (método buscar todos)
	@ResponseBody // Retorna os dados para o corpo da resposta (vai retornar um JSON)
	public ResponseEntity<List<MembrosDTO>> listaNiiver() {

		List<Membros> listaM = usuarioRepository.findAll(); // Executa a consulta no banco de dados

		List<MembrosDTO> listaDTO = listaM.stream().map(membros -> new MembrosDTO(membros.getId(), membros.getNome(),
				utilidades.dataFormater(membros.getDataNasc()))).collect(Collectors.toList());

		listaDTO = utilidades.ordenacao(listaDTO);

		listaDTO = utilidades.niverMes(listaDTO);

		return new ResponseEntity<List<MembrosDTO>>(listaDTO, HttpStatus.OK);

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------

	// Lista Aniversarios do mês após
	@GetMapping(value = "/nivermespos") // Primeiro método de API (método buscar todos)
	@ResponseBody // Retorna os dados para o corpo da resposta (vai retornar um JSON)
	public ResponseEntity<List<MembrosDTO>> listaNiverPos() {

		List<Membros> listaM = usuarioRepository.findAll(); // Executa a consulta no banco de dados

		List<MembrosDTO> listaDTO = listaM.stream().map(membros -> new MembrosDTO(membros.getId(), membros.getNome(),
				utilidades.dataFormater(membros.getDataNasc()))).collect(Collectors.toList());

		listaDTO = utilidades.ordenacao(listaDTO);

		listaDTO = utilidades.niverMesPos(listaDTO);

		return new ResponseEntity<List<MembrosDTO>>(listaDTO, HttpStatus.OK);

	}

	// ----------------------------------------------------------------------------------------------

	// Pega o mes atual e o mes seguinte
	@GetMapping(value = "/mes")
	@ResponseBody
	public ResponseEntity<Mes> mesAtualPos() {

		// Obtem o mês atual
		String mesA = utilidades.mesAtual();
		String mesB = utilidades.mesAtualPos();

		// Armazena em String o mês atual
		Mes mesResponse = new Mes(mesA, mesB);

		// retorna a String do mês atual
		return new ResponseEntity<>(mesResponse, HttpStatus.OK);
	}

	// -------------------------------------------------------------------------------------------------

	// Método Delete
	@DeleteMapping(value = "/delete") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<String> delete(@RequestParam Long iduser) { // Recebe os dados para salvar

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);

	}

	// -------------------------------------------------------------------------------------------------

	// Método Buscar por id
	@GetMapping(value = "/buscaruserid") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<Membros> buscaruserid(@RequestParam(name = "iduser") Long iduser) { // Recebe os dados para
																								// salvar

		Membros membros = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Membros>(membros, HttpStatus.OK);

	}

	// -------------------------------------------------------------------------------------------------

	// Método Atualizar
	@PutMapping(value = "/atualizar") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<?> atualizar(@RequestBody Membros membros) { // Recebe os dados para salvar

		if (membros.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
		} else {

			Membros membrosIns = usuarioRepository.saveAndFlush(membros);

			return new ResponseEntity<Membros>(membrosIns, HttpStatus.OK);
		}

	}

	// -------------------------------------------------------------------------------------------------

	// Método busca personalizada
	@GetMapping(value = "/buscarPorNome") // Mapeia a URL
	@ResponseBody // Faz a descrição da resposta
	public ResponseEntity<List<Membros>> buscarPorNome(@RequestParam(name = "name") String name) { // Recebe os dados
																									// para salvar
		List<Membros> membros = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); // trim e upperCase na
																							// interface tbm

		return new ResponseEntity<List<Membros>>(membros, HttpStatus.OK);

	}

	// -------------------------------------------------------------------------------------------------------

	// Busca Saldo
	@GetMapping(value = "/saldo")
	@ResponseBody
	public ResponseEntity<Conta> buscaSaldo() {

		Optional<Conta> contaE = contaRepository.findAll().stream().findFirst();
		return new ResponseEntity<>(contaE.get(), HttpStatus.OK);
	}

	// -----------------------------------------------------------------------------------------------------------

	// Adicionar valor
	@PutMapping(value = "/adicionar")
	@ResponseBody
	public ResponseEntity<?> adicinar(@RequestBody Conta contaF) {

		// LOCALIZA O ID NO BANCO
		Optional<Conta> contaId = contaRepository.findById(contaF.getId());

		// ATRIBUI OS CAMPOS A contaB
		Conta contaB = contaId.get();
		contaB.addSaldo(contaF.getSaldo());

		// FINALIZA A TRANSAÇÃO
		contaRepository.saveAndFlush(contaB);

		// RETORNA A TRANSAÇÃO
		return new ResponseEntity<Conta>(contaB, HttpStatus.OK);

	}

	// -------------------------------------------------------------------------------------------------------------

	// Retirar valor
	@PutMapping(value = "/retirar")
	@ResponseBody
	public ResponseEntity<?> retirar(@RequestBody Conta contaF) {

		Conta contaB = contaRepository.findById(contaF.getId()).get();

		contaB.retiraSaldo(contaF.getSaldo());
		contaRepository.saveAndFlush(contaB);

		return new ResponseEntity<Conta>(contaB, HttpStatus.OK);
	}

}
