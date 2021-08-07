package com.backdesafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backdesafio.domain.Entidade;
import com.backdesafio.repositorys.EntidadeRepository;

@Service
public class EntidadeService {

	@Autowired
	private EntidadeRepository repository;
	
	public Entidade buscarPorId(Long id){
		return repository.findById(id).orElseThrow(()->new IllegalArgumentException("A entidade de id" + id + " Não foi encontrado no sistema" ));
	}
	
	
}
