package com.sf.repo.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sf.repo.model.dto.UsuarioDto;
import com.sf.repo.model.usuario.Usuario;
import com.sf.repo.util.SessionContext;

@RequestScoped
@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario;
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public String getMessage() {

		UsuarioDto usuario = (UsuarioDto) SessionContext.getValueObjOnSession("usuarioLogado");
		return usuario.getNomeUsuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
