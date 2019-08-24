package com.sf.repo.api.controller;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sf.repo.api.model.Usuario;
import com.sf.repo.api.model.dto.UsuarioDto;
import com.sf.repo.api.service.UsuarioServico;

@Controller
@RequestMapping(path = "/login")
public class UsuarioControlador {

	@Autowired
	private UsuarioServico usuarioService;

	@GetMapping
	public @ResponseBody String messagem() {
		return "rest deu certeeo";
	}

	@PostMapping
	public ResponseEntity<Void> logar(@RequestBody UsuarioDto usuarioDto) {

		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlGithub = new StringBuilder("https://api.github.com/users");

		urlGithub.append("/");
		urlGithub.append(usuarioDto.getNomeUsuario());

		String dadosUsuario = restTemplate.getForObject(urlGithub.toString(), String.class);
		JSONObject jsonObj = new JSONObject(dadosUsuario);

		Usuario usuario = usuarioService.buscarPorIdGithub(jsonObj.getLong("id"));
		if (usuario == null) {
			usuario = usuarioDto.transformarObjeto(jsonObj);
			usuarioService.salvar(usuario);
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
}
