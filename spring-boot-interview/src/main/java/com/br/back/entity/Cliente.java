package com.br.back.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_cli")
    private Long id;

    @Column(name = "nome", columnDefinition = "VARCHAR(45)")
    private String nome;

    @Column(name =  "sexo", columnDefinition = "CHAR(1)")
    private String sexo;

    @Column(name =  "dataNascimento")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dataNascimento;

    @Column(name =  "idade")
    private Integer idade;

    @JoinColumn(name = Cidade.ID)
    @ManyToOne(targetEntity = Cidade.class)
    private Cidade cidade;
}
