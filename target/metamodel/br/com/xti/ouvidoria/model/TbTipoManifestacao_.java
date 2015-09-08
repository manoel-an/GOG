package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbTipoManifestacao.class)
public abstract class TbTipoManifestacao_ {

	public static volatile CollectionAttribute<TbTipoManifestacao, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbTipoManifestacao, Integer> prazoAreaSolucionadora;
	public static volatile SingularAttribute<TbTipoManifestacao, String> nmTipoManifestacao;
	public static volatile SingularAttribute<TbTipoManifestacao, Integer> prazoRespostaCidadao;
	public static volatile SingularAttribute<TbTipoManifestacao, Integer> idTipoManifestacao;
	public static volatile SingularAttribute<TbTipoManifestacao, String> dsTipoManifestacao;
	public static volatile SingularAttribute<TbTipoManifestacao, Integer> prazoEntrada;

}

