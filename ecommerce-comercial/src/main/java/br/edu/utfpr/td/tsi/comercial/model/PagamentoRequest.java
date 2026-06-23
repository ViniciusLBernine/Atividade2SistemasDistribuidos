package br.edu.utfpr.td.tsi.comercial.model;

import java.math.BigDecimal;

public class PagamentoRequest {
	private String numeroCartao;
	private BigDecimal valorTotal;

	public PagamentoRequest numeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
		return this;
	}

	public PagamentoRequest valorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
