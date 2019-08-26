package com.sf.repo.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


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
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario")
	private List<Repositorio> listaRepositorios;

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

	public String getJsonDados() {
		return jsonDados;
	}

	public void setJsonDados(String jsonDados) {
		this.jsonDados = jsonDados;
	}

	public List<Repositorio> getListaRepositorios() {
		return listaRepositorios;
	}

	public void setListaRepositorios(List<Repositorio> listaRepositorios) {
		this.listaRepositorios = listaRepositorios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idGithub == null) ? 0 : idGithub.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idGithub == null) {
			if (other.idGithub != null)
				return false;
		} else if (!idGithub.equals(other.idGithub))
			return false;
		return true;
	}
	
	
}
