package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbPrioridade.class)
public abstract class TbPrioridade_ {

	public static volatile CollectionAttribute<TbPrioridade, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbPrioridade, Integer> idPrioridade;
	public static volatile SingularAttribute<TbPrioridade, String> nmPrioridade;

}

