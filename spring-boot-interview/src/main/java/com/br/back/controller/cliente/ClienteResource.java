package com.br.back.controller.cliente;

import com.br.back.controller.cidade.CidadeResource;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResource {

    private Long id;
    private String nome;
    private String sexo;
    private String dataNascimento;
    private Integer idade;
    private String cidade;
    private Long cidadeId;

}
