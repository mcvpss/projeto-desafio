package com.br.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCidade is a Querydsl query type for Cidade
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCidade extends EntityPathBase<Cidade> {

    private static final long serialVersionUID = -1748167527L;

    public static final QCidade cidade = new QCidade("cidade");

    public final StringPath estado = createString("estado");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public QCidade(String variable) {
        super(Cidade.class, forVariable(variable));
    }

    public QCidade(Path<? extends Cidade> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCidade(PathMetadata metadata) {
        super(Cidade.class, metadata);
    }

}

