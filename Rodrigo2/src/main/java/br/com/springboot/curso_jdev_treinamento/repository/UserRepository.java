package br.com.springboot.curso_jdev_treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.springboot.curso_jdev_treinamento.users.Users; // Importação Correta
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByLogin(String login);

}
