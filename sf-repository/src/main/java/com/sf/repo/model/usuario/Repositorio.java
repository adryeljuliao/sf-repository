package com.sf.repo.model.usuario;

import java.io.Serializable;

public class Repositorio implements Serializable {

	private static final long serialVersionUID = -5258403046600913777L;

	private Long id;
	private String nomeRepositorio;
	private String urlRepositorio;
	private String linguagem;
	private String descricao;

	public Repositorio() {
	}

	public String getNomeRepositorio() {
		return nomeRepositorio;
	}

	public void setNomeRepositorio(String nomeRepositorio) {
		this.nomeRepositorio = nomeRepositorio;
	}

	public String getUrlRepositorio() {
		return urlRepositorio;
	}

	public void setUrlRepositorio(String urlRepositorio) {
		this.urlRepositorio = urlRepositorio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		if(descricao.equals("null")) {
			return "Sem descrição";
		}
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

}
