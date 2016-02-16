package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbEncaminhamento.class)
public abstract class TbEncaminhamento_ {

	public static volatile SingularAttribute<TbEncaminhamento, TbUsuario> idUsuarioEnviou;
	public static volatile SingularAttribute<TbEncaminhamento, String> dsDescricao;
	public static volatile SingularAttribute<TbEncaminhamento, TbUnidade> idUnidadeEnviou;
	public static volatile SingularAttribute<TbEncaminhamento, Integer> idEncaminhamento;
	public static volatile SingularAttribute<TbEncaminhamento, Date> dtRespostaTramite;
	public static volatile CollectionAttribute<TbEncaminhamento, TbEncaminhamentoxAnexo> tbEncaminhamentoxAnexoCollection;
	public static volatile SingularAttribute<TbEncaminhamento, TbUnidade> idUnidadeRecebeu;
	public static volatile SingularAttribute<TbEncaminhamento, Date> dtEnvioTramite;
	public static volatile SingularAttribute<TbEncaminhamento, String> stEncaminhamento;
	public static volatile SingularAttribute<TbEncaminhamento, TbManifestacao> idManifestacao;
	public static volatile SingularAttribute<TbEncaminhamento, Date> dtCriacaoEncaminhamento;
	public static volatile CollectionAttribute<TbEncaminhamento, TbTramite> tbTramiteCollection;

}

