package br.com.util;

public class Estrutura {
	
	private String nomeCampo;
	private String tipoCampo;
	private String tamanho;
	
	public Estrutura(){
		this.nomeCampo = "";
		this.tipoCampo = "";
		this.tamanho = "";
	}
	public Estrutura(String nome, String tipo, String ptam){
		this.nomeCampo = nome;
		this.tipoCampo = tipo;
		this.tamanho   = ptam;
	}
	public String getNomeCampo() {
		return nomeCampo;
	}
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public String getTipoCampo() {
		return tipoCampo;
	}
	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}	
}

class Parestrutura {
	Estrutura origem;
	Estrutura destino;
	public Parestrutura(Estrutura o, Estrutura d) {
		origem = o;
		destino = d;
	}
}
