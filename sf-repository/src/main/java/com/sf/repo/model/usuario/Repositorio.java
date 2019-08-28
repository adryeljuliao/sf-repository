package com.sf.repo.model.usuario;

import java.io.Serializable;

public class Repositorio implements Serializable {

	private static final long serialVersionUID = -5258403046600913777L;

	private Long id;
	private String nomeRepositorio;
	private String urlRepositorio;
	

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

}
