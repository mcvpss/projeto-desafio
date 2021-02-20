package com.br.back.repository;

import com.br.back.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cidadeRepository")
public interface ICidadeRepository extends JpaRepository<Cidade, Long> {

    Cidade findFirstByNome(String nome);

    List<Cidade> findByEstado(String estado);
}
