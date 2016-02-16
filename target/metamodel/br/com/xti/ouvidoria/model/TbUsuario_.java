package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbUsuario.class)
public abstract class TbUsuario_ {

	public static volatile SingularAttribute<TbUsuario, String> tpUsuario;
	public static volatile CollectionAttribute<TbUsuario, TbManifestacao> manifestacoesCriadas;
	public static volatile CollectionAttribute<TbUsuario, TbManifestacao> tbManifestacaoCollection1;
	public static volatile SingularAttribute<TbUsuario, Integer> idUsuario;
	public static volatile CollectionAttribute<TbUsuario, TbTramite> tbTramiteCollection1;
	public static volatile CollectionAttribute<TbUsuario, TbLogOperacao> tbLogOperacaoCollection;
	public static volatile SingularAttribute<TbUsuario, String> numTelefone;
	public static volatile CollectionAttribute<TbUsuario, TbUsuarioxPerfil> tbUsuarioxPerfilCollection;
	public static volatile CollectionAttribute<TbUsuario, TbTramite> tbTramiteCollection;
	public static volatile CollectionAttribute<TbUsuario, TbEncaminhamento> tbEncaminhamentoCollection;
	public static volatile SingularAttribute<TbUsuario, String> eeEmail;
	public static volatile CollectionAttribute<TbUsuario, TbManifestacao> tbManifestacaoCollection;
	public static volatile SingularAttribute<TbUsuario, String> tpFuncao;
	public static volatile SingularAttribute<TbUsuario, TbUnidade> idUnidade;
	public static volatile SingularAttribute<TbUsuario, String> nmSenha;
	public static volatile CollectionAttribute<TbUsuario, TbFiltroPersonalizado> tbFiltroPersonalizadoCollection;
	public static volatile SingularAttribute<TbUsuario, String> nmUsuario;
	public static volatile SingularAttribute<TbUsuario, String> nmLogin;
	public static volatile CollectionAttribute<TbUsuario, TbComunicacaoExterna> tbComunicacaoExternaCollection;
	public static volatile CollectionAttribute<TbUsuario, TbAviso> tbAvisoCollection;
	public static volatile SingularAttribute<TbUsuario, Integer> stStatus;

}

