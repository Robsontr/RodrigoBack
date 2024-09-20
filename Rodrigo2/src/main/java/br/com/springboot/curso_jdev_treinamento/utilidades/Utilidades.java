package br.com.springboot.curso_jdev_treinamento.utilidades;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.springboot.curso_jdev_treinamento.model.MembrosDTO;

@Component
public class Utilidades {

	// Formatação de data
	public String dataFormater(LocalDate dataNasc) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataNasc.format(dtf);
	}

	// Ordenação Alfabética
	public List<MembrosDTO> ordenacao(List<MembrosDTO> membros) {
		// Usando lambda para ordenar a lista com base no nome
		membros.sort((p1, p2) -> {
			String nome1 = p1.getNome();
			String nome2 = p2.getNome();
			return nome1.compareTo(nome2);
		});
		return membros;
	}

	// Aniversariantes do mês
	public List niverMes(List<MembrosDTO> lista) {

		Month mesAtual = LocalDate.now().getMonth();

		List<MembrosDTO> aniversariantes = lista.stream().filter(usuario -> {
			LocalDate dataNasc = LocalDate.parse(usuario.getDataNasc(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return dataNasc.getMonth() == mesAtual;
		}).collect(Collectors.toList());

		return aniversariantes;
	}

	// Aniversariantes do mês posterior
	public List niverMesPos(List<MembrosDTO> lista) {

		Month mesAtual = LocalDate.now().getMonth();

		List<MembrosDTO> aniversariantes = lista.stream().filter(usuario -> {
			LocalDate dataNasc = LocalDate.parse(usuario.getDataNasc(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return dataNasc.getMonth() == mesAtual.plus(1);
		}).collect(Collectors.toList());

		return aniversariantes;
	}

	// Mês atual
	public String mesAtual() {
		LocalDate hoje = LocalDate.now();
		Month mes = hoje.getMonth();
		String mesMaiusculo = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
		return mesMaiusculo.substring(0, 1).toUpperCase() + mesMaiusculo.substring(1);
	}

	// Mês a seguinte
	public String mesAtualPos() {
		LocalDate hoje = LocalDate.now();
		Month mes = hoje.getMonth().plus(1);
		String mesMaiusculo = mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
		return mesMaiusculo.substring(0, 1).toUpperCase() + mesMaiusculo.substring(1);
	}
}
