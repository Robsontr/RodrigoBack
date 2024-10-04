package br.com.springboot.curso_jdev_treinamento.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.springboot.curso_jdev_treinamento.users.Users;

@Service
public class TokenService {

    // Método para gerar o token JWT para um usuário
    public String gerarToken(Users user) {
        // Definindo o algoritmo HMAC256 com a chave secreta
        Algorithm algorithm = Algorithm.HMAC256("secreta");

        // Gerando o token JWT
        return JWT.create()
                .withIssuer("Users") // Define quem é o emissor do token
                .withSubject(user.getUsername()) // Define o usuário (assunto)
                .withClaim("id", user.getId()) // Inclui o ID do usuário como uma claim
                .withExpiresAt(Instant.now().plusSeconds(30)) // Define a expiração para 10 minutos
                .sign(algorithm); // Assina o token com o algoritmo HMAC256
    }

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("secreta"))
		     .withIssuer("Users")
		     .build()
		     .verify(token)
		     .getSubject();
		
	}
}














