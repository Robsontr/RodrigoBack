
package br.com.springboot.curso_jdev_treinamento.configurations;
/*
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.springboot.curso_jdev_treinamento.services.TokenService;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService; // Serviço que lida com a lógica do token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String authorizationHeader = request.getHeader("Authorization");
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.replace("Bearer ", "");
            String subject = this.tokenService.getSubject(token);

            // Aqui você pode usar o sujeito para autenticar o usuário, por exemplo:
            if (subject != null) {
                // Você pode definir o usuário autenticado no contexto de segurança, se necessário
                // Exemplo: SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua com a cadeia de filtros
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Aqui você pode definir quais URLs não devem ser filtradas
        // Por exemplo, não filtrar requisições para o login
        return request.getRequestURI().equals("/app/login");
    }
}

*/

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.springboot.curso_jdev_treinamento.services.TokenService;

@Component
public class FilterToken extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService; // Serviço que lida com a lógica do token

	@Autowired
	private UserDetailsService userDetailsService; // Serviço para carregar dados do usuário

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = null;
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.replace("Bearer ", "");
			String subject = this.tokenService.getSubject(token);

			// Autentica o usuário
			if (subject != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

				// Cria o objeto de autenticação
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				// Define a autenticação no contexto de segurança
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		// Continua com a cadeia de filtros
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		// Aqui você pode definir quais URLs não devem ser filtradas
		return request.getRequestURI().equals("/app/login");
	}
}