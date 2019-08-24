package com.sf.repo.model.dto;

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

	@Override
	public String toString() {
		return "UsuarioDto [nomeUsuario=" + nomeUsuario + "]";
	}

	
}
