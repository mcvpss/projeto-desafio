package com.br.back.controller.cliente;


import com.br.back.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/cliente")
public class ClientRestController {

    @Autowired
    private ClienteService service;


    // Cadastrar cliente
    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResource> cadastrar(@RequestBody ClienteResource clienteResource) throws URISyntaxException {
        return ResponseEntity.created(new URI("rest/cliente/")).body(service.salvar(clienteResource));
    }

    //Consultar cliente pelo nome
    @GetMapping("/{nome}/consultarPorNome")
    public ResponseEntity<ClienteResource> consultarPorNome(@PathVariable("nome") String nome){
        return ResponseEntity.ok(service.consultarPeloNome(nome));
    }

    //Consultar cliente pelo id
    @GetMapping("/{id}/consultarPorId")
    public ResponseEntity<ClienteResource> consultarPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.consultarPeloId(id));
    }

    // Excluir
    @GetMapping("/{id}/excluir")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/alterarNome")
    public ResponseEntity<ClienteResource> alterarNome(@RequestBody ClienteResource clienteResource) throws URISyntaxException {
        return ResponseEntity.created(new URI("rest/cliente/")).body(service.alterarNome(clienteResource));
    }
}
