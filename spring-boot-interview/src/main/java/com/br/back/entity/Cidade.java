package com.br.back.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cidade")
public class Cidade implements Serializable {

    static final String ID = "id_cid";

    @Id
    @Column(name=ID)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", columnDefinition = "VARCHAR(45)")
    private String nome;

    @Column(name="estado", columnDefinition = "VARCHAR(45)")
    private String estado;
}
