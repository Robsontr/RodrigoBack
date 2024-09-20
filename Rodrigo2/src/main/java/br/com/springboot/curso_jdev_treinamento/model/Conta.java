package br.com.springboot.curso_jdev_treinamento.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal saldo;

	public Conta() {
		this.saldo = BigDecimal.ZERO; // Inicializando com 0
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void addSaldo(BigDecimal valor) {
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			System.out.println("O valor não pode ser negativo ou igual a 0.");
		} else {
			this.saldo = this.saldo.add(valor); // Adiciona o valor ao saldo
		}
	}

	public void retiraSaldo(BigDecimal valor) {
		
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			System.out.println("O valor não pode ser negativo ou igual a 0.");
		} 
		else if (valor.compareTo(saldo) > 0) {
			System.out.println("Saldo insuficiente para retirada.");
		} 
		else {
			this.saldo = this.saldo.subtract(valor); // Subtrai o valor do saldo
		}
	}
}
