package com.br.back.controller.cidade;

import com.br.back.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cidade")
public class CidadeRestController {

    public static final String REST_CIDADE = "rest/cidade/";
    @Autowired
    private CidadeService cidadeService;

    // Cadastrar cidade
    @PostMapping("/cadastrar")
    public ResponseEntity<CidadeResource> cadastrar(@RequestBody CidadeResource cidadeResource) throws URISyntaxException {
        return ResponseEntity.created(new URI(REST_CIDADE)).body(cidadeService.salvar(cidadeResource));
    }

    //Consultar cidade pelo nome
    @GetMapping("/{nome}/consultarPorNome")
    public ResponseEntity<CidadeResource> consultarPorNome(@PathVariable("nome") String nome){
        return ResponseEntity.ok(cidadeService.consultarCidadePeloNome(nome));
    }

    //Consultar cidade pelo estado
    @GetMapping("/{estado}/consultarPeloEstado")
    public ResponseEntity<List<CidadeResource>> consultarPorEstado(@PathVariable("estado") String estado){
        return ResponseEntity.ok(cidadeService.consultarCidadePeloEstado(estado));
    }

    @GetMapping("/consultarTodas")
    public ResponseEntity<List<CidadeResource>> consultarTodas(){
        return ResponseEntity.ok(cidadeService.consultarTodas());
    }
}
