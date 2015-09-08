package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbTramite.class)
public abstract class TbTramite_ {

	public static volatile SingularAttribute<TbTramite, Integer> idTramite;
	public static volatile SingularAttribute<TbTramite, String> dsDescricao;
	public static volatile SingularAttribute<TbTramite, Date> dtTramite;
	public static volatile SingularAttribute<TbTramite, String> stNotificacao;
	public static volatile SingularAttribute<TbTramite, TbUsuario> idUsuarioEmissor;
	public static volatile SingularAttribute<TbTramite, TbEncaminhamento> idEncaminhamento;
	public static volatile SingularAttribute<TbTramite, TbUnidade> idUnidadeEnvio;
	public static volatile SingularAttribute<TbTramite, String> stTramitePublico;
	public static volatile SingularAttribute<TbTramite, TbUsuario> idUsuarioReceptor;
	public static volatile CollectionAttribute<TbTramite, TbTramitexAnexo> tbTramitexAnexoCollection;
	public static volatile SingularAttribute<TbTramite, String> stRetornada;

}

