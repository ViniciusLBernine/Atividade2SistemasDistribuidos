package br.edu.utfpr.td.tsi.comercial.model;

import java.math.BigDecimal;

public class Produto {
	private String id;
	private String nome;
	private String urlImagem;
	private BigDecimal preco;
	private int quantidadeEmEstoque;

	public Produto id(String id) {
		this.id = id;
		return this;
	}

	public Produto nome(String nome) {
		this.nome = nome;
		return this;
	}

	public Produto urlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
		return this;
	}

	public Produto preco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}

	public Produto quantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
}
