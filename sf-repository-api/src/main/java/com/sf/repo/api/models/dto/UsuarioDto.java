package com.sf.repo.api.models.dto;

import org.json.JSONObject;

import com.sf.repo.api.models.Usuario;
import com.sf.repo.api.utils.ObjetoJson;

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
		usuario.setLogin(ObjetoJson.tentaRecuperarObjeto(jsonObj, "login").toString());
		usuario.setNome(ObjetoJson.tentaRecuperarObjeto(jsonObj, "name").toString());
		usuario.setBio(ObjetoJson.tentaRecuperarObjeto(jsonObj, "bio").toString());
		usuario.setAvatar(ObjetoJson.tentaRecuperarObjeto(jsonObj, "avatar_url").toString());

		return usuario;
	}

}
