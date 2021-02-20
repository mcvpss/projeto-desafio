package com.br.back.controller.cidade;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CidadeResource {

    private Long id;
    private String nome;
    private String estado;
}
