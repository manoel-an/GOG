package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbManifestacao.class)
public abstract class TbManifestacao_ {

	public static volatile SingularAttribute<TbManifestacao, String> tipoPessoa;
	public static volatile SingularAttribute<TbManifestacao, String> horario;
	public static volatile SingularAttribute<TbManifestacao, String> nrTelefone;
	public static volatile SingularAttribute<TbManifestacao, TbUsuario> idUsuarioCriador;
	public static volatile SingularAttribute<TbManifestacao, String> dsComplemento;
	public static volatile CollectionAttribute<TbManifestacao, TbSubClassificacao> tbSubClassificacaoCollection;
	public static volatile SingularAttribute<TbManifestacao, Integer> idManifestacao;
	public static volatile SingularAttribute<TbManifestacao, String> stSpam;
	public static volatile SingularAttribute<TbManifestacao, TbQuestionario> idQuestionario;
	public static volatile CollectionAttribute<TbManifestacao, TbUnidade> tbUnidadeAreaSolucionadoraCollection;
	public static volatile CollectionAttribute<TbManifestacao, TbClassificacao> tbClassificacaoCollection;
	public static volatile SingularAttribute<TbManifestacao, TbMeioResposta> idMeioResposta;
	public static volatile SingularAttribute<TbManifestacao, String> numeroConta;
	public static volatile SingularAttribute<TbManifestacao, String> eeEmailUsuario;
	public static volatile SingularAttribute<TbManifestacao, String> siSigilo;
	public static volatile CollectionAttribute<TbManifestacao, TbManifestacaoxAnexo> tbManifestacaoxAnexoCollection;
	public static volatile SingularAttribute<TbManifestacao, String> unidadeConsumidora;
	public static volatile SingularAttribute<TbManifestacao, String> nrCelular;
	public static volatile SingularAttribute<TbManifestacao, Integer> nrEndereco;
	public static volatile SingularAttribute<TbManifestacao, String> tpSexo;
	public static volatile SingularAttribute<TbManifestacao, String> dsLocalidade;
	public static volatile SingularAttribute<TbManifestacao, String> dsTextoManifestacao;
	public static volatile SingularAttribute<TbManifestacao, String> dsTextoEncerramentoScript;
	public static volatile SingularAttribute<TbManifestacao, String> nrCEP;
	public static volatile SingularAttribute<TbManifestacao, String> tpRaca;
	public static volatile SingularAttribute<TbManifestacao, String> dsMotivoOcultacao;
	public static volatile SingularAttribute<TbManifestacao, TbUsuario> idUsuarioReativou;
	public static volatile SingularAttribute<TbManifestacao, TbUsuario> idUsuarioBloqueou;
	public static volatile SingularAttribute<TbManifestacao, String> ra;
	public static volatile SingularAttribute<TbManifestacao, String> eeEmailSecundario;
	public static volatile SingularAttribute<TbManifestacao, String> nrCpfCnpj;
	public static volatile CollectionAttribute<TbManifestacao, TbUnidadexManifestacao> tbUnidadexManifestacaoCollection;
	public static volatile SingularAttribute<TbManifestacao, String> stResposta;
	public static volatile SingularAttribute<TbManifestacao, String> prestadoraServico;
	public static volatile SingularAttribute<TbManifestacao, String> nrTelefone2;
	public static volatile CollectionAttribute<TbManifestacao, TbComunicacaoExterna> tbComunicacaoExternaCollection;
	public static volatile SingularAttribute<TbManifestacao, String> nrProcesso;
	public static volatile SingularAttribute<TbManifestacao, String> stStatusOcultacao;
	public static volatile SingularAttribute<TbManifestacao, TbGrauInstrucao> idGrauInstrucao;
	public static volatile SingularAttribute<TbManifestacao, TbUsuario> idUsuarioManifestante;
	public static volatile SingularAttribute<TbManifestacao, TbPais> idPais;
	public static volatile SingularAttribute<TbManifestacao, Date> dtFechamento;
	public static volatile SingularAttribute<TbManifestacao, String> numeroVeiculo;
	public static volatile SingularAttribute<TbManifestacao, String> tpManifestante;
	public static volatile SingularAttribute<TbManifestacao, TbUsuario> idUsuarioAnalisador;
	public static volatile SingularAttribute<TbManifestacao, Integer> nrManifestacao;
	public static volatile SingularAttribute<TbManifestacao, TbPrioridade> idPrioridade;
	public static volatile SingularAttribute<TbManifestacao, String> nmPessoa;
	public static volatile SingularAttribute<TbManifestacao, TbFaixaEtaria> idFaixaEtaria;
	public static volatile SingularAttribute<TbManifestacao, TbUF> idUF;
	public static volatile SingularAttribute<TbManifestacao, String> dsBairro;
	public static volatile SingularAttribute<TbManifestacao, String> nrPronac;
	public static volatile SingularAttribute<TbManifestacao, Date> dtUltimaAtualizacao;
	public static volatile SingularAttribute<TbManifestacao, Date> dtCadastro;
	public static volatile SingularAttribute<TbManifestacao, String> tipoSolicitacao;
	public static volatile SingularAttribute<TbManifestacao, TbMeioEntrada> idMeioEntrada;
	public static volatile SingularAttribute<TbManifestacao, String> dsSenha;
	public static volatile CollectionAttribute<TbManifestacao, TbEncaminhamento> tbEncaminhamentoCollection;
	public static volatile SingularAttribute<TbManifestacao, String> titularidade;
	public static volatile SingularAttribute<TbManifestacao, String> placaVeiculo;
	public static volatile SingularAttribute<TbManifestacao, String> motorista;
	public static volatile SingularAttribute<TbManifestacao, String> stStatusManifestacao;
	public static volatile SingularAttribute<TbManifestacao, String> enEndereco;
	public static volatile SingularAttribute<TbManifestacao, String> nrFAX;
	public static volatile SingularAttribute<TbManifestacao, TbTipoManifestacao> idTipoManifestacao;
	public static volatile SingularAttribute<TbManifestacao, TbAreaEntrada> idAreaEntrada;

}

