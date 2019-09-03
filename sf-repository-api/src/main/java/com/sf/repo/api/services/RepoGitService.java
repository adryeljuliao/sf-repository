package com.sf.repo.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sf.repo.api.repositories.RepoGitRepositorio;

@Service
public class RepoGitService {

	@Autowired
	private RepoGitRepositorio repoGitRepositorio;
	
	public void deletar(Long id) {
		repoGitRepositorio.deleteById(id);
	}
}
