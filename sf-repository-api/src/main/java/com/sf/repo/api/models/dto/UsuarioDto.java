package com.sf.repo.api.models.dto;

import org.json.JSONObject;

import com.sf.repo.api.models.Usuario;

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
		usuario.setLogin(tentaRecuperarObjeto(jsonObj, "login").toString());
		usuario.setNome(tentaRecuperarObjeto(jsonObj, "name").toString());
		usuario.setBio(tentaRecuperarObjeto(jsonObj, "bio").toString());
		usuario.setAvatar(tentaRecuperarObjeto(jsonObj, "avatar_url").toString());

		return usuario;
	}

	private Object tentaRecuperarObjeto(JSONObject jsonObj, String key) {
		return jsonObj.has(key) ? jsonObj.opt(key) : null;
	}

}
