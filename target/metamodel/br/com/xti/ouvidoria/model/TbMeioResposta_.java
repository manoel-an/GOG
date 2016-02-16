package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbMeioResposta.class)
public abstract class TbMeioResposta_ {

	public static volatile SingularAttribute<TbMeioResposta, String> nmMeioResposta;
	public static volatile CollectionAttribute<TbMeioResposta, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbMeioResposta, Integer> idMeioResposta;

}

