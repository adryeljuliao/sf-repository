package com.sf.repo.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;

import com.sf.repo.model.dto.UsuarioDto;
import com.sf.repo.util.SessionContext;

@RequestScoped
@ManagedBean
public class UsuarioBean {

	@PostConstruct
	public void iniciar() {

	}

	public String getMessage() {

		UsuarioDto usuario = (UsuarioDto) SessionContext.getValueObjOnSession("usuarioLogado");
		return usuario.getNomeUsuario();
	}

}
