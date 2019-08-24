package com.sf.repo.api.model.dto;

import org.json.JSONObject;

import com.sf.repo.api.model.Usuario;

public class UsuarioDto {

	private String nomeUsuario;
	
	public UsuarioDto() {
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Usuario transformarObjeto(JSONObject jsonObj) {
		Usuario usuario = new Usuario();
		usuario.setNomeUsuario(jsonObj.getString("login"));
		usuario.setIdGithub(jsonObj.getLong("id"));
		return usuario;
	}
	
}
