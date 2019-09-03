package com.sf.repo.api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sf.repo.api.models.Repositorio;

public interface RepoGitRepositorio extends CrudRepository<Repositorio, Long> {

	Optional<Repositorio> findByNomeRepositorio(String nomeRepositorio);
}
