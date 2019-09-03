package com.sf.repo.api.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.sf.repo.api.models.Repositorio;
import com.sf.repo.api.models.Usuario;
import com.sf.repo.api.models.dto.RepositorioDto;
import com.sf.repo.api.services.RepoGitService;
import com.sf.repo.api.services.UsuarioServico;

@Controller
@RequestMapping(path = "/usuarios/repositorios")
public class RepositorioControlador {

	@Autowired
	private UsuarioServico usuarioServico;

	@Autowired
	private RepoGitService repoGitService;

	@PutMapping(value = "{login}")
	public ResponseEntity<?> cadastrarRepositorio(@RequestBody String nomeRepositorioJson, @PathVariable String login) {
		String nomeRepositorio = new JSONObject(nomeRepositorioJson).optString("nomeRepositorio").trim();
		Usuario usuario = usuarioServico.buscarPorLogin(login);

		for (Repositorio repositorio : usuario.getListaRepositorios()) {
			if (repositorio.getNomeRepositorio().toLowerCase().equals(nomeRepositorio)) {
				return ResponseEntity.badRequest()
						.body("Repositório já cadastrado " + repositorio.getNomeRepositorio());
			}
		}

		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlGithub = new StringBuilder("https://api.github.com/repos");
		urlGithub.append("/");
		urlGithub.append(login);
		urlGithub.append("/");
		urlGithub.append(nomeRepositorio);

		try {
			String dadosUsuario = restTemplate.getForObject(urlGithub.toString(), String.class);
			JSONObject jsonObj = new JSONObject(dadosUsuario);
			Repositorio repositorio = criarObjRepositorio(jsonObj);
			usuario.adicionarRepositorio(repositorio);
			usuarioServico.atualizar(usuario);
			return ResponseEntity.ok().body("Repositório adicionado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("error" + e.getMessage());
		}

	}

	@DeleteMapping(value = "{login}/{nameRepository}")
	public ResponseEntity<?> removerRepositorio(@PathVariable String nameRepository, @PathVariable String login) {
		Usuario usuario = usuarioServico.buscarPorLogin(login);

		for (Repositorio repositorio : usuario.getListaRepositorios()) {
			if (repositorio.getNomeRepositorio().toLowerCase().equals(nameRepository.toLowerCase())) {
				usuario.removerRepositorio(repositorio);
				usuarioServico.atualizar(usuario);
				repoGitService.deletar(repositorio.getId());
				return ResponseEntity.ok().body("Repositório removido com sucesso" + repositorio.getNomeRepositorio());
			}
		}

		return ResponseEntity.badRequest().body("erro ao deletar repositorio");
	}

	@PutMapping(value = "{login}/{nameRepository}")
	public ResponseEntity<?> atualizarRepositorio(@PathVariable String login, @PathVariable String nameRepository,
			@RequestBody String descricao) {
		Usuario usuario = usuarioServico.buscarPorLogin(login);

		for (Repositorio repositorio : usuario.getListaRepositorios()) {
			if (repositorio.getNomeRepositorio().toLowerCase().equals(nameRepository.toLowerCase())) {
				repositorio.setDescricao(new JSONObject(descricao).optString("descricao").trim());
				usuarioServico.atualizar(usuario);
				return ResponseEntity.ok().body("Repositório removido com sucesso" + repositorio.getNomeRepositorio());
			}
		}

		return ResponseEntity.badRequest().body("erro ao deletar repositorio");
	}

	private Repositorio criarObjRepositorio(JSONObject jsonObj) {
		Repositorio repositorio = new Repositorio();
		repositorio.setNomeRepositorio(jsonObj.optString("name"));
		repositorio.setUrlRepositorio(jsonObj.optString("html_url"));
		repositorio.setDescricao(jsonObj.optString("description"));
		repositorio.setLinguagem(jsonObj.optString("language"));
		return repositorio;

	}

}
