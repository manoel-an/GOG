package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbClassificacao.class)
public abstract class TbClassificacao_ {

	public static volatile CollectionAttribute<TbClassificacao, TbManifestacao> tbManifestacaoCollection;
	public static volatile CollectionAttribute<TbClassificacao, TbSubClassificacao> tbSubClassificacaoCollection;
	public static volatile SingularAttribute<TbClassificacao, String> dsClassificacao;
	public static volatile CollectionAttribute<TbClassificacao, TbUnidade> tbUnidadeCollection;
	public static volatile SingularAttribute<TbClassificacao, Integer> idClassificacao;

}

