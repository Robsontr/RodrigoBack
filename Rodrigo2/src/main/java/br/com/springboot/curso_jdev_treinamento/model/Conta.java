package br.com.springboot.curso_jdev_treinamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double saldo;

	public Conta() {
		this.saldo = 0.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void addSaldo(double valor) {
		if (valor <= 0) {
			System.out.println("O valor não pode ser negativo ou igual a 0.");
		} else {
			this.saldo += valor;
		}

	}

	public void retiraSaldo(double valor) {
		if (valor <= 0) {
			System.out.println("O valor não pode ser negatrivo ou igual a 0.");
		}
		if (valor > saldo) {
			System.out.println("Saldo insuficiente para retirada.");
		} else {
			this.saldo -= valor;
		}

	}

}
