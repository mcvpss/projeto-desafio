package com.br.back.controller.cidade;

import com.br.back.entity.Cidade;
import lombok.extern.java.Log;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;


@Component
@Log
public class CidadeResourceMapper extends CustomMapper<Cidade, CidadeResource> {

    @Override
    public void mapAtoB(Cidade cidade, CidadeResource cidadeResource, MappingContext context) {
        cidadeResource.setId(cidade.getId());
        cidadeResource.setNome(cidade.getNome());
        cidadeResource.setEstado(cidade.getEstado());
    }

    @Override
    public void mapBtoA(CidadeResource cidadeResource, Cidade cidade, MappingContext context) {
        cidade.setNome(cidadeResource.getNome());
        cidade.setEstado(cidadeResource.getEstado());
    }
}
