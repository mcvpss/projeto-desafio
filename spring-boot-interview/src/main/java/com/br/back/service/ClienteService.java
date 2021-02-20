package com.br.back.service;

import com.br.back.controller.cliente.ClienteResource;
import com.br.back.controller.cliente.ClienteResourceMapper;
import com.br.back.entity.Cliente;
import com.br.back.repository.ICidadeRepository;
import com.br.back.repository.IClienteRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;

    @Autowired
    private ICidadeRepository cidadeRepository;

    @Autowired
    private ClienteValidator validator;


    @Transactional
    public ClienteResource salvar(ClienteResource clienteResource){
        MapperFacade mapperFacade = getMapperFacade();
        Cliente entidade = mapperFacade.map(clienteResource, Cliente.class);
        validator.validarInclusao(entidade);
        repository.save(entidade);
        return entidade == null ? null :  mapperFacade.map(entidade, ClienteResource.class);
    }

    private MapperFacade getMapperFacade() {
        ClienteResourceMapper customMapper = new ClienteResourceMapper(cidadeRepository);
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Cliente.class, ClienteResource.class).customize(customMapper).register();
        return mapperFactory.getMapperFacade();
    }

    @Transactional(readOnly = true)
    public ClienteResource consultarPeloNome(String nome){
        List<Cliente> all = repository.findAll();
        validator.validarConsultaPeloNome(nome);
        Cliente entidade = repository.findFirstByNome(nome);
        return entidade == null ? null : getMapperFacade().map(entidade, ClienteResource.class);
    }

    @Transactional(readOnly = true)
    public ClienteResource consultarPeloId(Long id){
        List<Cliente> all = repository.findAll();

        validator.validarConsultaPeloId(id);
        Cliente entidade = repository.findFirstById(id);
        return entidade == null ? null : getMapperFacade().map(entidade, ClienteResource.class);
    }

    @Transactional
    public void excluir(Long id){
        validator.validarExclusao(id);
        Cliente entidade = repository.findFirstById(id);
        repository.delete(entidade);
      }

    @Transactional
    public ClienteResource alterarNome(ClienteResource clienteResource){
        validator.validarAlteracaoNomeCliente(clienteResource);
        MapperFacade mapperFacade = getMapperFacade();
        Cliente entidade = mapperFacade.map(clienteResource, Cliente.class);
        repository.save(entidade);
        return mapperFacade.map(entidade, ClienteResource.class);
    }


}
