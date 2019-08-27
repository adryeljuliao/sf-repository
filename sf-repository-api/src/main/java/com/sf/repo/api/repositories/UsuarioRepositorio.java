package com.sf.repo.api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sf.repo.api.models.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{
	Optional<Usuario> findByIdGithub(Long idGithub);
}
