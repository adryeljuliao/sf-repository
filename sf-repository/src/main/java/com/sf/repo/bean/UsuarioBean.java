package com.sf.repo.bean;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UsuarioBean {

	
	@PostConstruct
	public void iniciar() {
		
	}
}
