package com.sf.repo.api.controllers;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sf.repo.api.models.Usuario;
import com.sf.repo.api.models.dto.UsuarioDto;
import com.sf.repo.api.services.UsuarioServico;
import com.sf.repo.api.services.execptions.ObjectNotFoundException;

@Controller
@RequestMapping(path = "/")
public class UsuarioControlador {

	@Autowired
	private UsuarioServico usuarioService;

	@GetMapping
	public @ResponseBody String messagem() {
		return "rest deu certeeo";
	}

	@PostMapping(path = "/login")
	public ResponseEntity<Void> logar(@RequestBody UsuarioDto usuarioDto) throws ObjectNotFoundException {

		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlGithub = new StringBuilder("https://api.github.com/users");

		urlGithub.append("/");
		urlGithub.append(usuarioDto.getNomeUsuario());

		String dadosUsuario = restTemplate.getForObject(urlGithub.toString(), String.class);
		JSONObject jsonObj = new JSONObject(dadosUsuario);

		Usuario usuario = usuarioService.buscarPorLogin(jsonObj.getString("login"));
		if (usuario == null) {
			usuario = usuarioDto.transformarObjeto(jsonObj);
			usuarioService.salvar(usuario);
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{login}").buildAndExpand(usuario.getLogin())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/usuarios/{username}")
	public ResponseEntity<?> buscarUsuario(@PathVariable String username) {
		Usuario usuario = usuarioService.buscarPorLogin(username);
		if(usuario == null) {
			return ResponseEntity.badRequest().body("Usuário " + username + " não encontrado");
		}
		return ResponseEntity.ok().body(usuario);
	}
}
