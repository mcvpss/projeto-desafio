package com.br.back.repository;

import com.br.back.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findFirstByNome(String nome);

    Cliente findFirstById(Long id);

}
