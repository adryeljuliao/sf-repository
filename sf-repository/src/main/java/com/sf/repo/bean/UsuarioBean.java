package com.sf.repo.bean;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.web.client.RestTemplate;

import com.sf.repo.model.dto.UsuarioDto;
import com.sf.repo.model.usuario.Usuario;
import com.sf.repo.util.SessionContext;

@RequestScoped
@ManagedBean
public class UsuarioBean {

	private Usuario usuario;

	@PostConstruct
	public void iniciar() {
		UsuarioDto objDto = (UsuarioDto) SessionContext.getValueObjOnSession("usuarioLogado");
		StringBuilder url = new StringBuilder("http://localhost:3000/usuarios");
		url.append("/");
		url.append(objDto.getNomeUsuario());
		RestTemplate restTemplate = new RestTemplate();

		usuario = restTemplate.getForObject(url.toString(), Usuario.class);
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
