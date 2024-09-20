package br.com.springboot.curso_jdev_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.curso_jdev_treinamento.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
	List<Historico> findAllByOrderByDataTransDesc();
}
