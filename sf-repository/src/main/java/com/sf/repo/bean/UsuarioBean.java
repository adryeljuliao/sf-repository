package com.sf.repo.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.web.client.RestTemplate;

import com.sf.repo.model.dto.RepositorioDto;
import com.sf.repo.model.dto.UsuarioDto;
import com.sf.repo.model.usuario.Repositorio;
import com.sf.repo.model.usuario.Usuario;
import com.sf.repo.util.SessionContext;

@RequestScoped
@ManagedBean
public class UsuarioBean {

	private Usuario usuario;
	private Repositorio repositorioEscolhido;
	private UsuarioDto objDto;
	private RepositorioDto repositorioDto;
	private static final String URL_FIND_USER = "http://localhost:3000/usuarios/{login}";
	@PostConstruct
	public void iniciar() {
		objDto = (UsuarioDto) SessionContext.getValueObjOnSession("usuarioLogado");
		
		repositorioEscolhido = new Repositorio();
		repositorioDto = new RepositorioDto();
	}

	public void cadastrarRepositorio() {
		String url = "http://localhost:3000/usuarios/repositorios/{login}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", usuario.getLogin());
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.put(url.toString(), repositorioDto, params);
		} catch (Exception e) {
			System.err.println("error " + e.getMessage());
		}
		
		limparCampos();
	}
	
	public void removerRepositorio() {
		String url = "http://localhost:3000/usuarios/repositorios/{login}/{nameRepository}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("login", usuario.getLogin());
		params.put("nameRepository", repositorioEscolhido.getNomeRepositorio());
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(url.toString(), params);
		} catch (Exception e) {
			System.err.println("error");
		}
		
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
		Map<String, String> params = new HashMap<>();
		params.put("login", objDto.getNomeUsuario());
		RestTemplate restTemplate = new RestTemplate();

		usuario = restTemplate.getForObject(URL_FIND_USER, Usuario.class, params);
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
