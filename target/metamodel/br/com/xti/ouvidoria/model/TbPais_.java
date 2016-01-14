package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbPais.class)
public abstract class TbPais_ {

	public static volatile CollectionAttribute<TbPais, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbPais, Integer> idPais;
	public static volatile SingularAttribute<TbPais, String> nmPais;

}

