package com.sf.repo.api.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.sf.repo.api.models.Repositorio;
import com.sf.repo.api.models.Usuario;
import com.sf.repo.api.models.dto.RepositorioDto;
import com.sf.repo.api.services.UsuarioServico;
import com.sf.repo.api.utils.ObjetoJson;

@Controller
@RequestMapping(path = "/usuarios/repositorios")
public class RepositorioControlador {

	@Autowired
	private UsuarioServico usuarioServico;

	@PutMapping(value = "{login}")
	public ResponseEntity<?> cadastrarRepositorio(@RequestBody RepositorioDto repositorioDto,
			@PathVariable String login) {

		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlGithub = new StringBuilder("https://api.github.com/repos");
		urlGithub.append("/");
		urlGithub.append(login);
		urlGithub.append("/");
		urlGithub.append(
				ObjetoJson.tentaRecuperarObjeto(new JSONObject(repositorioDto), "nomeRepositorio").toString().trim());

		String dadosUsuario = restTemplate.getForObject(urlGithub.toString(), String.class);

		JSONObject jsonObj = new JSONObject(dadosUsuario);

		Usuario usuario = usuarioServico.buscarPorLogin(login);

		for (Repositorio repositorio : usuario.getListaRepositorios()) {
			if (repositorio.getNomeRepositorio().toLowerCase()
					.equals(ObjetoJson.tentaRecuperarObjeto(jsonObj, "name").toString().toLowerCase())) {
				return ResponseEntity.badRequest()
						.body("Repositório já cadastrado " + repositorio.getNomeRepositorio());
			}
		}

		Repositorio repositorio = criarObjRepositorio(jsonObj);
		usuario.adicionarRepositorio(repositorio);
		usuario = usuarioServico.atualizar(usuario);
		return ResponseEntity.ok().body("Repositório adicionado com sucesso");

	}

	private Repositorio criarObjRepositorio(JSONObject jsonObj) {
		Repositorio repositorio = new Repositorio();
		repositorio.setNomeRepositorio(ObjetoJson.tentaRecuperarObjeto(jsonObj, "name").toString());
		repositorio.setUrlRepositorio(ObjetoJson.tentaRecuperarObjeto(jsonObj, "html_url").toString());
		repositorio.setDescricao(ObjetoJson.tentaRecuperarObjeto(jsonObj, "description").toString());
		repositorio.setLinguagem(ObjetoJson.tentaRecuperarObjeto(jsonObj, "language").toString());
		return repositorio;

	}

}
