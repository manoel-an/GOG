package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbSubClassificacao.class)
public abstract class TbSubClassificacao_ {

	public static volatile CollectionAttribute<TbSubClassificacao, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbSubClassificacao, Integer> idSubClassificacao;
	public static volatile CollectionAttribute<TbSubClassificacao, TbClassificacao> tbClassificacaoCollection;
	public static volatile SingularAttribute<TbSubClassificacao, String> dsSubClassificacao;

}

