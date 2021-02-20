package com.br.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCliente is a Querydsl query type for Cliente
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCliente extends EntityPathBase<Cliente> {

    private static final long serialVersionUID = 1732015909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCliente cliente = new QCliente("cliente");

    public final QCidade cidade;

    public final DatePath<org.joda.time.LocalDate> dataNascimento = createDate("dataNascimento", org.joda.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idade = createNumber("idade", Integer.class);

    public final StringPath nome = createString("nome");

    public final StringPath sexo = createString("sexo");

    public QCliente(String variable) {
        this(Cliente.class, forVariable(variable), INITS);
    }

    public QCliente(Path<? extends Cliente> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCliente(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCliente(PathMetadata metadata, PathInits inits) {
        this(Cliente.class, metadata, inits);
    }

    public QCliente(Class<? extends Cliente> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cidade = inits.isInitialized("cidade") ? new QCidade(forProperty("cidade")) : null;
    }

}

