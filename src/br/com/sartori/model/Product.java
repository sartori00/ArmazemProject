package br.com.sartori.model;

import java.math.BigDecimal;

public class Product {
	
	private int codigo;
	
	private String nome;
	
	private BigDecimal valor;
	
	private int quantidade;
	
	private String dataVencimento;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public String toString() {
		return " - Código -> " + codigo + "\n - Nome -> " + nome + "\n - Valor -> R$ " + valor + "\n - Quantidade em estoque -> " + quantidade
				+ "\n - Data de Vencimento -> " + dataVencimento;
	}
	
	public String toStringRetornedOfDb() {
		return "\nProduto: Código - " + codigo + ", Nome - " + nome + ", Valor - R$ " + valor + ", Quantidade em estoque - " + quantidade
				+ ", Data de Vencimento - " + dataVencimento;
	}

}
