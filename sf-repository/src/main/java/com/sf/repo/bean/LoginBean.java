package com.sf.repo.bean;

import java.io.Serializable;

import org.primefaces.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sf.repo.model.dto.UsuarioDto;

@Component
@Scope("session")
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 8946009153982006623L;
//	private StringBuilder url;
	private UsuarioDto usuario;

	public LoginBean() {
		usuario = new UsuarioDto();
	}

	public String redirecionarHome() {
		return "home.xhtml?faces-redirect=true";
	}

	public String getMessage() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.github.com/repos/adryeljuliao/game-websocket";
		String repositoryGithub = restTemplate.getForObject(url, String.class);

		JSONObject jsonObj = new JSONObject(repositoryGithub.toString());

		String fullName = jsonObj.getString("full_name");

		return fullName;
	}

	public String entrar() {
		StringBuilder url = new StringBuilder("http://localhost:3000/login");
		if (!usuario.getNomeUsuario().trim().isEmpty()) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(url.toString(), usuario, UsuarioDto.class);
			limparCampo();
			return redirecionarHome();
		}
		return "";
	}
 
	public void limparCampo() {
		usuario = new UsuarioDto();
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
}
