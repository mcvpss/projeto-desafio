package com.br.back.service;

import com.br.back.controller.cidade.CidadeResource;
import com.br.back.controller.cidade.CidadeResourceMapper;
import com.br.back.entity.Cidade;
import com.br.back.repository.ICidadeRepository;
import lombok.Setter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Setter
public class CidadeService {

    @Autowired
    private ICidadeRepository cidadeRepository;

    @Autowired
    private  CidadeValidator cidadeValidator;

    private MapperFacade getMapperFacade() {
        CidadeResourceMapper customMapper = new CidadeResourceMapper();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Cidade.class, CidadeResource.class).customize(customMapper).register();
        return mapperFactory.getMapperFacade();
    }


    @Transactional(readOnly = true)
    public CidadeResource consultarCidadePeloNome(String nome){
        cidadeValidator.validarConsultaNome(nome);
        Cidade entidade = cidadeRepository.findFirstByNome(nome);
        return entidade == null ? null : getMapperFacade().map(entidade, CidadeResource.class);
    }

    @Transactional(readOnly = true)
    public List<CidadeResource> consultarCidadePeloEstado(String estado){
        cidadeValidator.validarConsultaEstado(estado);
        List<Cidade> entidades =  cidadeRepository.findByEstado(estado);
        return entidades == null ? null : getMapperFacade().mapAsList(entidades, CidadeResource.class);
    }

    @Transactional
    public CidadeResource salvar(CidadeResource cidadeResource){
        MapperFacade mapperFacade = getMapperFacade();
        Cidade entidade = mapperFacade.map(cidadeResource, Cidade.class);
        cidadeValidator.validarInclusao(entidade);
        cidadeRepository.save(entidade);
        return entidade == null ? null : mapperFacade.map(entidade, CidadeResource.class);
    }

    @Transactional(readOnly = true)
    public List<CidadeResource> consultarTodas(){
        List<Cidade> entidades =  cidadeRepository.findAll();
        MapperFacade mapperFacade = getMapperFacade();
        return entidades == null ? null : getMapperFacade().mapAsList(entidades, CidadeResource.class);
    }


}
