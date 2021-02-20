package com.br.back.controller.cliente;

import com.br.back.entity.Cidade;
import com.br.back.entity.Cliente;
import com.br.back.repository.ICidadeRepository;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class ClienteResourceMapper  extends CustomMapper<Cliente, ClienteResource> {
    
    private ICidadeRepository cidadeRepository;

    public ClienteResourceMapper(ICidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public void mapAtoB(Cliente cliente, ClienteResource clienteResource, MappingContext context) {
        clienteResource.setId(cliente.getId());
        clienteResource.setNome(cliente.getNome());
        clienteResource.setSexo(cliente.getSexo());
        clienteResource.setDataNascimento(cliente.getDataNascimento() == null ? "" : cliente.getDataNascimento().toString("dd/MM/YYYY"));
        clienteResource.setIdade(cliente.getIdade());
        if(Objects.nonNull(cliente.getCidade())){
            clienteResource.setCidade(cliente.getCidade().getNome());
            clienteResource.setCidadeId(cliente.getCidade().getId());
        }
    }

    /**
     * Tela para objeto
     *
     * @param clienteResource
     * @param cliente
     * @param context
     */
    @Override
    public void mapBtoA(ClienteResource clienteResource, Cliente cliente, MappingContext context) {
        cliente.setId(clienteResource.getId());
        cliente.setNome(clienteResource.getNome());
        cliente.setSexo(clienteResource.getSexo());

        if(Objects.nonNull(clienteResource.getDataNascimento())){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date data = df.parse(clienteResource.getDataNascimento());
                LocalDate dataNascimento = LocalDate.fromDateFields(data);
                cliente.setDataNascimento(dataNascimento);

                LocalDate hoje = LocalDate.now();
                Years idade = Years.yearsBetween(dataNascimento, hoje);

                cliente.setIdade(idade.getYears());
            } catch (ParseException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        if(Objects.nonNull(clienteResource.getCidadeId())){
            Optional<Cidade> cidade = cidadeRepository.findById(clienteResource.getCidadeId());
            cliente.setCidade(cidade.get());
        }
    }
}
