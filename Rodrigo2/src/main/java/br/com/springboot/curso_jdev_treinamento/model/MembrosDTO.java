package br.com.springboot.curso_jdev_treinamento.model;

public class MembrosDTO {

	private Long id;
	private String nome;
	private String dataNasc;

	public MembrosDTO(Long id, String nome, String dataNasc) {
		this.id = id;
	    this.nome = nome;
	    this.dataNasc = dataNasc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

}
