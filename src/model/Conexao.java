package model;

import java.io.Serializable;


/**
 * The persistent class for the conexao database table.
 * 
 */

public class Conexao implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idconexao;

	private String bancoconexao;

	private String bancodedados;

	private String comentariosbanco;

	private String driverbanco;

	private String senha;

	private String usuario;

	public Conexao() {
	}

	public int getIdconexao() {
		return this.idconexao;
	}

	public void setIdconexao(int idconexao) {
		this.idconexao = idconexao;
	}

	public String getBancoconexao() {
		return this.bancoconexao;
	}

	public void setBancoconexao(String bancoconexao) {
		this.bancoconexao = bancoconexao;
	}

	public String getBancodedados() {
		return this.bancodedados;
	}

	public void setBancodedados(String bancodedados) {
		this.bancodedados = bancodedados;
	}

	public String getComentariosbanco() {
		return this.comentariosbanco;
	}

	public void setComentariosbanco(String comentariosbanco) {
		this.comentariosbanco = comentariosbanco;
	}

	public String getDriverbanco() {
		return this.driverbanco;
	}

	public void setDriverbanco(String driverbanco) {
		this.driverbanco = driverbanco;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}