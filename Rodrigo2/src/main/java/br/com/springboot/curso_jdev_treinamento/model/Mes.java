package br.com.springboot.curso_jdev_treinamento.model;

public class Mes {
	private String mesAtual;
	private String mesAtualPos;

	public Mes(String mesAtual, String mesAtualPos) {
		this.mesAtual = mesAtual;
		this.mesAtualPos = mesAtualPos;
	}

	public String getMesAtual() {
		return mesAtual;
	}

	public void setMesAtual(String mesAtual) {
		this.mesAtual = mesAtual;
	}

	public String getMesAtualPos() {
		return mesAtualPos;
	}

	public void setMesAtualPos(String mesAtualPos) {
		this.mesAtualPos = mesAtualPos;
	}

}
