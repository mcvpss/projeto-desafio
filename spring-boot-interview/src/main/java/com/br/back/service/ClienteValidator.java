package com.br.back.service;

import com.br.back.controller.cliente.ClienteResource;
import com.br.back.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidator {
    public void validarInclusao(Cliente entidade) {
        // Não faz nada por enquanto
    }

    public void validarConsultaPeloNome(String nome) {
        // Não faz nada por enquanto
    }

    public void validarConsultaPeloId(Long id) {
        // Não faz nada por enquanto
    }

    public void validarAlteracaoNomeCliente(ClienteResource clienteResource) {
        // Não faz nada por enquanto
    }

    public void validarExclusao(Long id) {
        // Não faz nada por enquanto
    }
}
