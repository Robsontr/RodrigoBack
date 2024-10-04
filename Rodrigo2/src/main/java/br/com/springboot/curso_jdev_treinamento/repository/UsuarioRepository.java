package br.com.springboot.curso_jdev_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev_treinamento.model.Membros;

@Repository
public interface UsuarioRepository extends JpaRepository<Membros, Long> {
	
	@Query(value = "select m from Membros m where upper(trim(m.nome)) like %?1%") // trim() tira espaço e upper() ignora maiúsculas e minúsculas
	List<Membros> buscarPorNome(String nome);


}
