package com.backdesafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backdesafio.domain.Entidade;
import com.backdesafio.repositorys.EntidadeRepository;
import com.backdesafio.service.EntidadeService;

@RestController
@RequestMapping("/entidades")
public class EntidadeController {
	@Autowired
	private EntidadeService service;
	@Autowired
	private EntidadeRepository repository;

	@GetMapping
	public List<Entidade> BuscarTodos(String nome, Pageable pageable) {
		if (StringUtils.hasLength(nome)) {
			Page<Entidade> page = repository.buscarPorNome(nome, pageable);
			return page.getContent();
		}

		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Entidade Buscar(@PathVariable Long id) {
		return repository.findById(id).get();
	}

	@DeleteMapping("/{id}")
	public void Deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PostMapping
	public Entidade Salvar(@RequestBody Entidade entidade) {

		return repository.save(entidade);
	}

	@PutMapping("/{id}")
	public Entidade Update(@RequestBody Entidade entidade, @PathVariable Long id) {
		Entidade entUp = repository.findById(id).get();
		entUp.setNome(entidade.getNome());

		return repository.save(entUp);
	}

	@Transactional
	@PutMapping("/{id}/feita")
	public void fazerAtividade(@PathVariable Long id) {
		Entidade entid = service.buscarPorId(id);
		entid.setIsFeita(true);
	}

	@Transactional
	@DeleteMapping("/{id}/feita")
	public void desfazerAtividade(@PathVariable Long id) {
		Entidade entid = service.buscarPorId(id);
		entid.setIsFeita(false);
	}

}
