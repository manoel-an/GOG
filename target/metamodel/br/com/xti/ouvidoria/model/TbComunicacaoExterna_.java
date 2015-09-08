package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbComunicacaoExterna.class)
public abstract class TbComunicacaoExterna_ {

	public static volatile SingularAttribute<TbComunicacaoExterna, String> stRespostaFinal;
	public static volatile SingularAttribute<TbComunicacaoExterna, Integer> idComunicacaoExterna;
	public static volatile SingularAttribute<TbComunicacaoExterna, String> dsComunicacao;
	public static volatile SingularAttribute<TbComunicacaoExterna, TbUsuario> idUsuario;
	public static volatile SingularAttribute<TbComunicacaoExterna, String> stComunicacaoPublica;
	public static volatile SingularAttribute<TbComunicacaoExterna, Date> dtComunicacao;
	public static volatile SingularAttribute<TbComunicacaoExterna, TbManifestacao> idManifestacao;
	public static volatile CollectionAttribute<TbComunicacaoExterna, TbComunicacaoExternaxAnexo> tbComunicacaoExternaxAnexoCollection;

}

