package br.com.util;

import java.util.Vector;

public class DicionarioMetaDados {
	
	public String getTipo() {
		return tipo;
	}
	public String getNomeBd() {
		return nomeBd;
	}
	
	public void setNomeBd(String nomeBd) {
		this.nomeBd = nomeBd;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomepk() {
		return nomepk;
	}
	public void setNomepk(String nomepk) {
		this.nomepk = nomepk;
	}
	public String getNomefk() {
		return nomefk;
	}
	public void setNomefk(String nomefk) {
		this.nomefk = nomefk;
	}
	public String getTabelapk() {
		return tabelapk;
	}
	public void setTabelapk(String tabelapk) {
		this.tabelapk = tabelapk;
	}
	public String getTabelafk() {
		return tabelafk;
	}
	public void setTabelafk(String tabelafk) {
		this.tabelafk = tabelafk;
	}
	
	public Vector<String> getNomesColunas () {
		return new Vector<String>();
	}
	
	public String getMnemonico() {
		return mnemonico;
	}
	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	public String getTitulocurto() {
		return titulocurto;
	}
	public void setTitulocurto(String titulocurto) {
		this.titulocurto = titulocurto;
	}
	public String getTitulolongo() {
		return titulolongo;
	}
	public void setTitulolongo(String titulolongo) {
		this.titulolongo = titulolongo;
	}
	public String getTitulomedio() {
		return titulomedio;
	}
	public void setTitulomedio(String titulomedio) {
		this.titulomedio = titulomedio;
	}
	public String getConceito() {
		return conceito;
	}
	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	public String getPolonesa() {
		return polonesa;
	}
	public void setPolonesa(String polonesa) {
		this.polonesa = polonesa;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	private String tipo;      //banco (1), tabela (2), relacionamento(3), chave primária (4), chave estrangeira(5), campo indexador (6), campo comum (7)
	private String nomeBd;    //nome do banco
	private String nomeTabela;      //do tipo: banco tabela relacionamento, etc.
	private String nome;       //nome conforme o tipo 
	private String mnemonico;
	private String titulocurto;
	private String titulolongo;
	private String titulomedio;
	private String conceito;
	private String polonesa;
	private String nomepk;    //nome do campo chave primária
	private String nomefk;    //nome do campo chave estrageira
	private String tabelapk;  //nome da tabela da chave primária
	private String tabelafk;  //nome da tabela da chave estrageira

}