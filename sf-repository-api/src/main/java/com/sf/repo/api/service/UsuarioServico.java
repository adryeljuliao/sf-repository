package com.sf.repo.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.repo.api.model.Usuario;
import com.sf.repo.api.repository.UsuarioRepositorio;

@Service
public class UsuarioServico {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServico.class);

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public void salvar(Usuario usuario) {
		usuarioRepositorio.save(usuario);
		log.info("Usuario salvo com sucesso");
	}
	
	public Usuario buscarPorIdGithub(Long id) {
		return usuarioRepositorio.findByIdGithub(id).orElse(null);
	}
}
