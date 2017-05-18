package com.util.br;

public class Estrutura {
	
	private String nomeCampo;
	private String tipoCampo;
	private String tamanho;
	
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
}
