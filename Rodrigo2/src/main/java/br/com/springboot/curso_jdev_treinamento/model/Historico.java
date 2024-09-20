package br.com.springboot.curso_jdev_treinamento.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dataTrans = LocalDateTime.now(); // Data sempre atualizada com a atual, não precisa de setter
	private BigDecimal valorTrans;
	private String motivoTrans;
	private BigDecimal valorAtualizado;

	public Historico() {

	}

	public Historico(BigDecimal valorTrans, String motivoTrans, BigDecimal valorAtualizado) {
		this.valorTrans = valorTrans;
		this.motivoTrans = motivoTrans;
		this.valorAtualizado = valorAtualizado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataTrans() {
		return dataTrans;
	}

	public BigDecimal getValorTrans() {
		return valorTrans;
	}

	public void setValorTrans(BigDecimal valorTrans) {
		this.valorTrans = valorTrans;
	}

	public String getMotivoTrans() {
		return motivoTrans;
	}

	public void setMotivoTrans(String motivoTrans) {
		this.motivoTrans = motivoTrans;
	}

	public BigDecimal getValorAtualizado() {
		return valorAtualizado;
	}

	public void setValorAtualizado(BigDecimal valorAtualizado) {
		if (valorTrans.compareTo(BigDecimal.ZERO) > 0) {
			this.valorAtualizado = valorAtualizado.add(valorTrans);
		} else {
			this.valorAtualizado = valorAtualizado; // Se for negativo, não faz alteração
		}
	}

}
