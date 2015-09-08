package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbGrauInstrucao.class)
public abstract class TbGrauInstrucao_ {

	public static volatile SingularAttribute<TbGrauInstrucao, Integer> idGrauInstrucao;
	public static volatile CollectionAttribute<TbGrauInstrucao, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbGrauInstrucao, String> nmGrauInstrucao;

}

