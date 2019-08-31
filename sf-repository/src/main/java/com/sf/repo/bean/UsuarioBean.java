package com.sf.repo.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.web.client.RestTemplate;

import com.sf.repo.model.dto.RepositorioDto;
import com.sf.repo.model.dto.UsuarioDto;
import com.sf.repo.model.usuario.Repositorio;
import com.sf.repo.model.usuario.Usuario;
import com.sf.repo.util.SessionContext;

@ViewScoped
@ManagedBean
public class UsuarioBean {

	private Usuario usuario;
	private Repositorio repositorioEscolhido;

	private RepositorioDto repositorioDto;

	@PostConstruct
	public void iniciar() {
		UsuarioDto objDto = (UsuarioDto) SessionContext.getValueObjOnSession("usuarioLogado");
		StringBuilder url = new StringBuilder("http://localhost:3000/usuarios");
		url.append("/");
		url.append(objDto.getNomeUsuario());
		RestTemplate restTemplate = new RestTemplate();

		usuario = restTemplate.getForObject(url.toString(), Usuario.class);
		repositorioEscolhido = new Repositorio();
		repositorioDto = new RepositorioDto();
	}

	public void cadastrarRepositorio() {
		String url = "http://localhost:3000/usuarios/repositorios/{login}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", usuario.getLogin());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url.toString(), repositorioDto, params);
		limparCampos();
	}

	public void limparCampos() {
		repositorioDto = new RepositorioDto();
	}

	public String getMessage() {

		UsuarioDto usuario = (UsuarioDto) SessionContext.getValueObjOnSession("usuarioLogado");
		return usuario.getNomeUsuario();
	}

	public void escolher() {
		System.out.println("escolheu " + repositorioEscolhido.getNomeRepositorio());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setRepositorioEscolhido(Repositorio repositorioEscolhido) {
		this.repositorioEscolhido = repositorioEscolhido;
	}

	public RepositorioDto getRepositorioDto() {
		return repositorioDto;
	}

	public void setRepositorioDto(RepositorioDto repositorioDto) {
		this.repositorioDto = repositorioDto;
	}

}
