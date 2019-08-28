package com.sf.repo.model.usuario;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1608589736777340771L;

	private Long id;
	private String login;
	private String nome;
	private String bio;
	private String avatar;
	private List<Repositorio> listaRepositorios;

	public Usuario() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Repositorio> getListaRepositorios() {
		return listaRepositorios;
	}

	public void setListaRepositorios(List<Repositorio> listaRepositorios) {
		this.listaRepositorios = listaRepositorios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
