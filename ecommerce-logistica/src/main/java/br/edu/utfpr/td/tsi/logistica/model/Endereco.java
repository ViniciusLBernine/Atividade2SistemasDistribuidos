package br.edu.utfpr.td.tsi.logistica.model;

public class Endereco {
	private String cep;
	private String logradouro;
	private String bairro;
	private Integer numero;
	private String cidade;
	private String estado;

	public Endereco cep(String cep) {
		this.cep = cep;
		return this;
	}

	public Endereco logradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public Endereco bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public Endereco cidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public Endereco estado(String estado) {
		this.estado = estado;
		return this;
	}

	public Endereco numero(Integer numero) {
		this.numero = numero;
		return this;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}