package br.com.springboot.curso_jdev_treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.curso_jdev_treinamento.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
