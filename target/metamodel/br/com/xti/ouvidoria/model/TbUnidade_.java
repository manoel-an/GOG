package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbUnidade.class)
public abstract class TbUnidade_ {

	public static volatile SingularAttribute<TbUnidade, String> stVinculada;
	public static volatile SingularAttribute<TbUnidade, String> stEncaminharOutrasAreas;
	public static volatile SingularAttribute<TbUnidade, String> nmUnidade;
	public static volatile SingularAttribute<TbUnidade, String> cnpj;
	public static volatile SingularAttribute<TbUnidade, String> sgUnidade;
	public static volatile CollectionAttribute<TbUnidade, TbTramite> tbTramiteCollection;
	public static volatile CollectionAttribute<TbUnidade, TbUsuario> tbUsuarioCollection;
	public static volatile CollectionAttribute<TbUnidade, TbEncaminhamento> tbEncaminhamentoCollection;
	public static volatile CollectionAttribute<TbUnidade, TbUnidadexManifestacao> tbUnidadexManifestacaoCollection;
	public static volatile SingularAttribute<TbUnidade, String> eeEmail;
	public static volatile CollectionAttribute<TbUnidade, TbManifestacao> tbManifestacaoCollection;
	public static volatile CollectionAttribute<TbUnidade, TbClassificacao> tbClassificacaoCollection;
	public static volatile SingularAttribute<TbUnidade, Integer> idUnidade;
	public static volatile SingularAttribute<TbUnidade, String> stRetornoOuvidoria;

}

