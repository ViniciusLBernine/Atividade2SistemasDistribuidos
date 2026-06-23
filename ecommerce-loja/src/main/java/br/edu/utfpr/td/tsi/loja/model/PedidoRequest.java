package br.edu.utfpr.td.tsi.loja.model;

import java.math.BigDecimal;
import java.util.List;

public class PedidoRequest {
	private String emailUsuario;
	private List<String> produtosIds;
	private String cep;
	private PagamentoRequest pagamento;
	private String formaPagamento;
	private boolean ePremium;
	private BigDecimal valorBase;

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public List<String> getProdutosIds() {
		return produtosIds;
	}

	public void setProdutosIds(List<String> produtosIds) {
		this.produtosIds = produtosIds;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public PagamentoRequest getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoRequest pagamento) {
		this.pagamento = pagamento;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public boolean isePremium() {
		return ePremium;
	}

	public void setePremium(boolean ePremium) {
		this.ePremium = ePremium;
	}

	public BigDecimal getValorBase() {
		return valorBase;
	}

	public void setValorBase(BigDecimal valorBase) {
		this.valorBase = valorBase;
	}
}
