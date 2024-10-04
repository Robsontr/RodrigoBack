package br.com.springboot.curso_jdev_treinamento.dto;

public class LoginDTO {

	private String login;
	private String password;

	public LoginDTO() {

	}

	// Construtor
	public LoginDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	// Getters
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	// Setters
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
