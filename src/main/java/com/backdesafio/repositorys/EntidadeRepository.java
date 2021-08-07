package com.backdesafio.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backdesafio.domain.Entidade;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

	@Query("select e from Entidade e where e.nome like %?1%")
	Page<Entidade> buscarPorNome(String nome, Pageable pageable);

}
