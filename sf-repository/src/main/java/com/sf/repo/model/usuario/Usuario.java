package com.sf.repo.model.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "usuario")
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1608589736777340771L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String jsonDados;
	private Long idGithub;
	private String nomeUsuario;
	private String nomeRepositorio;
	private String urlRepositorio;

	public Usuario() {

	}

	public Usuario(Long idGithub, String jsonDados) {
		this.idGithub = idGithub;
		this.jsonDados = jsonDados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdGithub() {
		return idGithub;
	}

	public void setIdGithub(Long idGithub) {
		this.idGithub = idGithub;
	}

	public String getUrlRepositorio() {
		return urlRepositorio;
	}

	public void setUrlRepositorio(String urlRepositorio) {
		this.urlRepositorio = urlRepositorio;
	}

	public String getNomeRepositorio() {
		return nomeRepositorio;
	}

	public void setNomeRepositorio(String nomeRepositorio) {
		this.nomeRepositorio = nomeRepositorio;
	}

	public String getJsonDados() {
		return jsonDados;
	}

	public void setJsonDados(String jsonDados) {
		this.jsonDados = jsonDados;
	}
}
