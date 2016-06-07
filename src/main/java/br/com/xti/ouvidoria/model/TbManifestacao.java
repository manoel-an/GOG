package br.com.xti.ouvidoria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jsoup.Jsoup;

import br.com.xti.ouvidoria.helper.ValidacaoHelper;
import br.com.xti.ouvidoria.model.enums.BooleanEnum;

/**
 * @author samuel.guimaraes
 */
@Entity
@Table(name = "tbManifestacao")
@NamedQueries({
    @NamedQuery(name = "TbManifestacao.findAll", query = "SELECT t FROM TbManifestacao t"),
    @NamedQuery(name = "TbManifestacao.findByIdManifestacao", query = "SELECT t FROM TbManifestacao t WHERE t.idManifestacao = :idManifestacao"),
    @NamedQuery(name = "TbManifestacao.findByNrManifestacao", query = "SELECT t FROM TbManifestacao t WHERE t.nrManifestacao = :nrManifestacao"),
    @NamedQuery(name = "TbManifestacao.findByDsSenha", query = "SELECT t FROM TbManifestacao t WHERE t.dsSenha = :dsSenha"),
    @NamedQuery(name = "TbManifestacao.findBySiSigilo", query = "SELECT t FROM TbManifestacao t WHERE t.siSigilo = :siSigilo"),
    @NamedQuery(name = "TbManifestacao.findByNmPessoa", query = "SELECT t FROM TbManifestacao t WHERE t.nmPessoa = :nmPessoa"),
    @NamedQuery(name = "TbManifestacao.findByEeEmailSecundario", query = "SELECT t FROM TbManifestacao t WHERE t.eeEmailSecundario = :eeEmailSecundario"),
    @NamedQuery(name = "TbManifestacao.findByTpSexo", query = "SELECT t FROM TbManifestacao t WHERE t.tpSexo = :tpSexo"),
    @NamedQuery(name = "TbManifestacao.findByStResposta", query = "SELECT t FROM TbManifestacao t WHERE t.stResposta = :stResposta"),
    @NamedQuery(name = "TbManifestacao.findByEnEndereco", query = "SELECT t FROM TbManifestacao t WHERE t.enEndereco = :enEndereco"),
    @NamedQuery(name = "TbManifestacao.findByNrEndereco", query = "SELECT t FROM TbManifestacao t WHERE t.nrEndereco = :nrEndereco"),
    @NamedQuery(name = "TbManifestacao.findByDsComplemento", query = "SELECT t FROM TbManifestacao t WHERE t.dsComplemento = :dsComplemento"),
    @NamedQuery(name = "TbManifestacao.findByDsBairro", query = "SELECT t FROM TbManifestacao t WHERE t.dsBairro = :dsBairro"),
    @NamedQuery(name = "TbManifestacao.findByDsTextoManifestacao", query = "SELECT t FROM TbManifestacao t WHERE t.dsTextoManifestacao = :dsTextoManifestacao"),
    @NamedQuery(name = "TbManifestacao.findByStStatusManifestacao", query = "SELECT t FROM TbManifestacao t WHERE t.stStatusManifestacao = :stStatusManifestacao"),
    @NamedQuery(name = "TbManifestacao.findByDtCadastro", query = "SELECT t FROM TbManifestacao t WHERE t.dtCadastro = :dtCadastro"),
    @NamedQuery(name = "TbManifestacao.findByDtUltimaAtualizacao", query = "SELECT t FROM TbManifestacao t WHERE t.dtUltimaAtualizacao = :dtUltimaAtualizacao"),
    @NamedQuery(name = "TbManifestacao.findByTpRaca", query = "SELECT t FROM TbManifestacao t WHERE t.tpRaca = :tpRaca"),
    @NamedQuery(name = "TbManifestacao.findByNrTelefone", query = "SELECT t FROM TbManifestacao t WHERE t.nrTelefone = :nrTelefone"),
    @NamedQuery(name = "TbManifestacao.findByNrCEP", query = "SELECT t FROM TbManifestacao t WHERE t.nrCEP = :nrCEP"),
    @NamedQuery(name = "TbManifestacao.findByNrFAX", query = "SELECT t FROM TbManifestacao t WHERE t.nrFAX = :nrFAX"),
    @NamedQuery(name = "TbManifestacao.findByEeEmailUsuario", query = "SELECT t FROM TbManifestacao t WHERE t.eeEmailUsuario = :eeEmailUsuario"),
    @NamedQuery(name = "TbManifestacao.findByDtFechamento", query = "SELECT t FROM TbManifestacao t WHERE t.dtFechamento = :dtFechamento"),
    @NamedQuery(name = "TbManifestacao.findByStSpam", query = "SELECT t FROM TbManifestacao t WHERE t.stSpam = :stSpam")})
public class TbManifestacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idManifestacao")
    private Integer idManifestacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nrManifestacao")
    private int nrManifestacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "dsSenha")
    private String dsSenha;
    @Column(name = "siSigilo")
    private String siSigilo;
    @Size(max = 150)
    @Column(name = "nmPessoa")
    private String nmPessoa;
    
    @Column(length = 20)
    private String tipoSolicitacao;
    
    @Column(length = 60)
    private String prestadoraServico;
    
    @Column(length = 30)
    @Size(max = 30)
    private String ra;
    
    @Column(length = 30)
    @Size(max = 30)
    private String numeroConta;
    
    @Column(length = 60)
    @Size(max = 60)
    private String titularidade;
    
    @Column(length = 10)
    @Size(max = 10)
    private String placaVeiculo;
    
    @Column(length = 10)
    @Size(max = 10)
    private String numeroVeiculo;
    
    @Column(length = 60)
    @Size(max = 60)
    private String horario;
    
    @Column(length = 60)
    @Size(max = 60)
    private String motorista;
    
    @Column(length = 30)
    @Size(max = 30)
    private String unidadeConsumidora;
    
    @Size(max = 200)
    @Column(name = "eeEmailSecundario")
    private String eeEmailSecundario;
    @Size(min = 1, max = 2)
    @Column(name = "tpSexo")
    private String tpSexo;
    @Basic(optional = true)
    @Size(min = 1, max = 1)
    @Column(name = "tpManifestante")
    private String tpManifestante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stResposta")
    private String stResposta;
    @Size(max = 180)
    @Column(name = "enEndereco")
    private String enEndereco;
    @Column(name = "nrEndereco")
    private Integer nrEndereco = 0;
    @Size(max = 180)
    @Column(name = "dsComplemento")
    private String dsComplemento;
    @Size(max = 100)
    @Column(name = "dsBairro")
    private String dsBairro;
    @Size(max = 100)
    @Column(name = "dsLocalidade")
    private String dsLocalidade;
    @Basic(optional = false)
    @NotNull
    @Column(columnDefinition = "text", name = "dsTextoManifestacao")
    private String dsTextoManifestacao;
    @Column(columnDefinition = "text", name = "dsTextoEncerramentoScript")
    private String dsTextoEncerramentoScript;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stStatusManifestacao")
    private String stStatusManifestacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtUltimaAtualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAtualizacao;
    @Column(name = "dtUltimaVisualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaVisualizacao;
    @Column(name = "tpRaca")
    private String tpRaca;
    @Size(max = 13)
    @Column(name = "nrTelefone")
    private String nrTelefone;
    @Size(max = 13)
    @Column(name = "nrTelefone2")
    private String nrTelefone2;
    @Size(max = 13)
    @Column(name = "nrCelular")
    private String nrCelular;
    @Size(max = 9)
    @Column(name = "nrCEP")
    private String nrCEP;
    @Size(max = 13)
    @Column(name = "nrFAX")
    private String nrFAX;
    @Basic(optional = false)
    @Column(name = "eeEmailUsuario")
    private String eeEmailUsuario;
    @Column(name = "dtFechamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFechamento;
    @Column(name = "stSpam")
    private String stSpam;
    @Size(max = 255)
    @Column(name = "dsMotivoOcultacao")
    private String dsMotivoOcultacao;
    @Size(max = 1)
    @Column(name = "stStatusOcultacao")
    private String stStatusOcultacao;
    @Size(max = 20)
    @Column(name = "nrCpfCnpj")
    private String nrCpfCnpj;
    @Size(max = 60)
    @Column(name = "nrPronac")
    private String nrPronac;
    @Size(max = 60)
    @Column(name = "nrProcesso")
    private String nrProcesso;
    @Size(max = 1)
    @Column(name = "tipoPessoa")
    private String tipoPessoa;
    @OneToMany(mappedBy = "idManifestacao", cascade=CascadeType.MERGE)
    private Collection<TbManifestacaoxAnexo> tbManifestacaoxAnexoCollection = new ArrayList<>();
    @OneToMany(mappedBy = "idManifestacao")
    private Collection<TbUnidadexManifestacao> tbUnidadexManifestacaoCollection = new ArrayList<>();
    @JoinColumn(name = "idUsuarioAnalisador", referencedColumnName = "idUsuario")
    @ManyToOne
    private TbUsuario idUsuarioAnalisador;
    @JoinColumn(name = "idUsuarioManifestante", referencedColumnName = "idUsuario")
    @ManyToOne
    private TbUsuario idUsuarioManifestante;
    @JoinColumn(name = "idUsuarioCriador", referencedColumnName = "idUsuario")
    @ManyToOne
    private TbUsuario idUsuarioCriador;
    @JoinColumn(name = "idUsuarioReativou", referencedColumnName = "idUsuario")
    @ManyToOne
    private TbUsuario idUsuarioReativou;
    @JoinColumn(name = "idUsuarioBloqueou", referencedColumnName = "idUsuario")
    @ManyToOne
    private TbUsuario idUsuarioBloqueou;
    @JoinColumn(name = "idTipoManifestacao", referencedColumnName = "idTipoManifestacao")
    @ManyToOne(optional = false)
    private TbTipoManifestacao idTipoManifestacao;
    @JoinColumn(name = "idPrioridade", referencedColumnName = "idPrioridade")
    @ManyToOne(optional = false)
    private TbPrioridade idPrioridade;
    @JoinColumn(name = "idPais", referencedColumnName = "idPais")
    @ManyToOne(optional = false)
    private TbPais idPais;
    @JoinColumn(name = "idUF", referencedColumnName = "idUF")
    @ManyToOne(optional = true)
    private TbUF idUF;
    @JoinColumn(name = "idMeioResposta", referencedColumnName = "idMeioResposta")
    @ManyToOne
    private TbMeioResposta idMeioResposta;
    @JoinColumn(name = "idMeioEntrada", referencedColumnName = "idMeioEntrada")
    @ManyToOne(optional = false)
    private TbMeioEntrada idMeioEntrada;
    @JoinColumn(name = "idFaixaEtaria", referencedColumnName = "idFaixaEtaria")
    @ManyToOne(optional = true)
    private TbFaixaEtaria idFaixaEtaria;
    @JoinColumn(name = "idGrauInstrucao", referencedColumnName = "idGrauInstrucao")
    @ManyToOne(optional = true)
    private TbGrauInstrucao idGrauInstrucao;
    @JoinColumn(name = "idAreaEntrada", referencedColumnName = "idAreaEntrada")
    @ManyToOne(optional = false)
    private TbAreaEntrada idAreaEntrada;
    @JoinColumn(name = "idQuestionario", referencedColumnName = "idQuestionario")
    @ManyToOne
    private TbQuestionario idQuestionario;
    @OneToMany(mappedBy = "idManifestacao")
    @OrderBy(value = "idComunicacaoExterna DESC")
    private Collection<TbComunicacaoExterna> tbComunicacaoExternaCollection = new ArrayList<>();
    @OneToMany(mappedBy = "idManifestacao")
    private Collection<TbEncaminhamento> tbEncaminhamentoCollection = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tbManifestacao_tbClassificacao", joinColumns = @JoinColumn(name = "idManifestacao"), inverseJoinColumns = @JoinColumn(name = "idClassificacao"))  
    private Collection<TbClassificacao> tbClassificacaoCollection = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tbManifestacao_tbSubClassificacao", joinColumns = @JoinColumn(name = "idManifestacao"), inverseJoinColumns = @JoinColumn(name = "idSubClassificacao"))
    private Collection<TbSubClassificacao> tbSubClassificacaoCollection = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tbManifestacao_UnidadeAreaSolucionadora", joinColumns = @JoinColumn(name = "idManifestacao"), inverseJoinColumns = @JoinColumn(name = "idUnidade"))  
    private Collection<TbUnidade> tbUnidadeAreaSolucionadoraCollection = new ArrayList<>();
    

    public TbManifestacao() {
        stStatusOcultacao = BooleanEnum.NAO.getId();
    }

    public TbManifestacao(Integer idManifestacao) {
        this.idManifestacao = idManifestacao;
    }

    public TbManifestacao(Integer idManifestacao, int nrManifestacao, String dsSenha, String tpSexo, String stResposta, String dsTextoManifestacao, String stStatusManifestacao, Date dtCadastro, Date dtUltimaAtualizacao, String tpRaca, String eeEmailUsuario) {
        this.idManifestacao = idManifestacao;
        this.nrManifestacao = nrManifestacao;
        this.dsSenha = dsSenha;
        this.tpSexo = tpSexo;
        this.stResposta = stResposta;
        this.dsTextoManifestacao = dsTextoManifestacao;
        this.stStatusManifestacao = stStatusManifestacao;
        this.dtCadastro = dtCadastro;
        this.dtUltimaAtualizacao = dtUltimaAtualizacao;
        this.tpRaca = tpRaca;
        this.eeEmailUsuario = eeEmailUsuario;
    }

    public String getNrCpfCnpj() {
        return nrCpfCnpj;
    }

    public void setNrCpfCnpj(String nrCpfCnpj) {
        this.nrCpfCnpj = nrCpfCnpj;
    }

    public Integer getIdManifestacao() {
        return idManifestacao;
    }

    public void setIdManifestacao(Integer idManifestacao) {
        this.idManifestacao = idManifestacao;
    }

    public int getNrManifestacao() {
        return nrManifestacao;
    }

    public void setNrManifestacao(int nrManifestacao) {
        this.nrManifestacao = nrManifestacao;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public String getSiSigilo() {
        return siSigilo;
    }

    public void setSiSigilo(String siSigilo) {
        this.siSigilo = siSigilo;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getPrestadoraServico() {
		return prestadoraServico;
	}

	public void setPrestadoraServico(String prestadoraServico) {
		this.prestadoraServico = prestadoraServico;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTitularidade() {
		return titularidade;
	}

	public void setTitularidade(String titularidade) {
		this.titularidade = titularidade;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getNumeroVeiculo() {
		return numeroVeiculo;
	}

	public void setNumeroVeiculo(String numeroVeiculo) {
		this.numeroVeiculo = numeroVeiculo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getMotorista() {
		return motorista;
	}

	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}

	public String getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(String unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

	public String getEeEmailSecundario() {
        return eeEmailSecundario;
    }

    public void setEeEmailSecundario(String eeEmailSecundario) {
        this.eeEmailSecundario = eeEmailSecundario;
    }

    public String getTpSexo() {
        return tpSexo;
    }

    public void setTpSexo(String tpSexo) {
        this.tpSexo = tpSexo;
    }
    
    public String getTpManifestante() {
		return tpManifestante;
	}

	public void setTpManifestante(String tpManifestante) {
		this.tpManifestante = tpManifestante;
	}

	public String getStResposta() {
        return stResposta;
    }

    public void setStResposta(String stResposta) {
        this.stResposta = stResposta;
    }

    public String getEnEndereco() {
        return enEndereco;
    }
    
    public String getEnderecoCompleto() {
    	try {
	    	StringBuilder sb = new StringBuilder();
	    	
	    	if(ValidacaoHelper.isNotEmpty(enEndereco))
	    		sb.append(enEndereco).append(" ");
	    	
	    	if(ValidacaoHelper.isNotEmpty(nrEndereco))
	    		sb.append("Nº "+ nrEndereco);
	    	
	    	if(ValidacaoHelper.isNotEmpty(dsComplemento))
	    		sb.append(", ").append(dsComplemento);
	    	
	    	if(ValidacaoHelper.isNotEmpty(dsBairro))
	    		sb.append(" - ").append(dsBairro);
	    	
	    	if(ValidacaoHelper.isNotEmpty(dsLocalidade))
	    		sb.append(" - ").append(dsLocalidade);
	    	
	    	if(ValidacaoHelper.isNotEmpty(idUF))
	    		sb.append("-").append(idUF.getSgUF());
	    	
	    	if(ValidacaoHelper.isNotEmpty(nrCEP))
	    		sb.append("  ").append(nrCEP);
	    	
	    	return sb.toString();
    	} catch(Exception e) {
    		return "";
    	}
    }
    
    public String getOutrosTelefones(){
    	StringBuilder sb = new StringBuilder();
    	
    	if(ValidacaoHelper.isNotEmpty(nrTelefone2))
    		sb.append(nrTelefone2).append(" ");
    	
    	if(ValidacaoHelper.isNotEmpty(nrCelular))
    		sb.append(nrCelular).append(" ");
    	
    	if(ValidacaoHelper.isNotEmpty(nrFAX))
    		sb.append(nrFAX).append(" ");
    	
    	return sb.toString();
    }

    public void setEnEndereco(String enEndereco) {
        this.enEndereco = enEndereco;
    }

    public Integer getNrEndereco() {
        return nrEndereco;
    }

    public void setNrEndereco(Integer nrEndereco) {
        this.nrEndereco = nrEndereco;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }

    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsTextoManifestacao() {
        return dsTextoManifestacao;
    }
    
    public String getDsTextoManifestacaoFormatado() {
    	// Remove os caracteres problemáticos (travam a tela do sistema)
    	return Jsoup.parse(dsTextoManifestacao).html();
    }

    public void setDsTextoManifestacao(String dsTextoManifestacao) {
        this.dsTextoManifestacao = dsTextoManifestacao;
    }

    public String getDsTextoEncerramentoScript() {
		return dsTextoEncerramentoScript;
	}

	public void setDsTextoEncerramentoScript(String dsTextoEncerramentoScript) {
		this.dsTextoEncerramentoScript = dsTextoEncerramentoScript;
	}

	public String getStStatusManifestacao() {
        return stStatusManifestacao;
    }

    public void setStStatusManifestacao(String stStatusManifestacao) {
        this.stStatusManifestacao = stStatusManifestacao;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtUltimaAtualizacao() {
        return dtUltimaAtualizacao;
    }

    public void setDtUltimaAtualizacao(Date dtUltimaAtualizacao) {
        this.dtUltimaAtualizacao = dtUltimaAtualizacao;
    }

    public Date getDtUltimaVisualizacao() {
		return dtUltimaVisualizacao;
	}

	public void setDtUltimaVisualizacao(Date dtUltimaVisualizacao) {
		this.dtUltimaVisualizacao = dtUltimaVisualizacao;
	}

	public String getTpRaca() {
        return tpRaca;
    }

    public void setTpRaca(String tpRaca) {
        this.tpRaca = tpRaca;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getNrCEP() {
        return nrCEP;
    }

    public void setNrCEP(String nrCEP) {
        this.nrCEP = nrCEP;
    }

    public String getNrFAX() {
        return nrFAX;
    }

    public void setNrFAX(String nrFAX) {
        this.nrFAX = nrFAX;
    }

    public String getEeEmailUsuario() {
        return eeEmailUsuario;
    }

    public void setEeEmailUsuario(String eeEmailUsuario) {
        this.eeEmailUsuario = eeEmailUsuario;
    }

    public Date getDtFechamento() {
        return dtFechamento;
    }

    public void setDtFechamento(Date dtFechamento) {
        this.dtFechamento = dtFechamento;
    }

    public String getStSpam() {
        return stSpam;
    }

    public void setStSpam(String stSpam) {
        this.stSpam = stSpam;
    }

    public Collection<TbManifestacaoxAnexo> getTbManifestacaoxAnexoCollection() {
        return tbManifestacaoxAnexoCollection;
    }

    public void setTbManifestacaoxAnexoCollection(Collection<TbManifestacaoxAnexo> tbManifestacaoxAnexoCollection) {
        this.tbManifestacaoxAnexoCollection = tbManifestacaoxAnexoCollection;
    }
    
    public Collection<TbUnidadexManifestacao> getTbUnidadexManifestacaoCollection() {
        return tbUnidadexManifestacaoCollection;
    }

    public void setTbUnidadexManifestacaoCollection(Collection<TbUnidadexManifestacao> tbUnidadexManifestacaoCollection) {
        this.tbUnidadexManifestacaoCollection = tbUnidadexManifestacaoCollection;
    }
    
    public Collection<TbUnidade> getTbUnidadeAreaSolucionadoraCollection() {
		return tbUnidadeAreaSolucionadoraCollection;
	}

	public void setTbUnidadeAreaSolucionadoraCollection(
			Collection<TbUnidade> tbUnidadeAreaSolucionadoraCollection) {
		this.tbUnidadeAreaSolucionadoraCollection = tbUnidadeAreaSolucionadoraCollection;
	}

	public TbUsuario getIdUsuarioAnalisador() {
        return idUsuarioAnalisador;
    }

    public void setIdUsuarioAnalisador(TbUsuario idUsuarioAnalisador) {
        this.idUsuarioAnalisador = idUsuarioAnalisador;
    }

    public TbUsuario getIdUsuarioManifestante() {
        return idUsuarioManifestante;
    }

    public void setIdUsuarioManifestante(TbUsuario idUsuarioManifestante) {
        this.idUsuarioManifestante = idUsuarioManifestante;
    }
    
    public TbUsuario getIdUsuarioCriador() {
		return idUsuarioCriador;
	}

	public void setIdUsuarioCriador(TbUsuario idUsuarioCriador) {
		this.idUsuarioCriador = idUsuarioCriador;
	}
	
	public TbUsuario getIdUsuarioReativou() {
		return idUsuarioReativou;
	}

	public void setIdUsuarioReativou(TbUsuario idUsuarioReativou) {
		this.idUsuarioReativou = idUsuarioReativou;
	}
	
	public TbUsuario getIdUsuarioBloqueou() {
		return idUsuarioBloqueou;
	}

	public void setIdUsuarioBloqueou(TbUsuario idUsuarioBloqueou) {
		this.idUsuarioBloqueou = idUsuarioBloqueou;
	}

	public TbTipoManifestacao getIdTipoManifestacao() {
        return idTipoManifestacao;
    }

    public void setIdTipoManifestacao(TbTipoManifestacao idTipoManifestacao) {
        this.idTipoManifestacao = idTipoManifestacao;
    }
    
    public Collection<TbClassificacao> getTbClassificacaoCollection() {
		return tbClassificacaoCollection;
	}

	public void setTbClassificacaoCollection(
			Collection<TbClassificacao> tbClassificacaoCollection) {
		this.tbClassificacaoCollection = tbClassificacaoCollection;
	}

	public Collection<TbSubClassificacao> getTbSubClassificacaoCollection() {
		return tbSubClassificacaoCollection;
	}

	public void setTbSubClassificacaoCollection(
			Collection<TbSubClassificacao> tbSubClassificacaoCollection) {
		this.tbSubClassificacaoCollection = tbSubClassificacaoCollection;
	}

	public TbPrioridade getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(TbPrioridade idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    public TbPais getIdPais() {
        return idPais;
    }

    public void setIdPais(TbPais idPais) {
        this.idPais = idPais;
    }

    public TbUF getIdUF() {
        return idUF;
    }

    public void setIdUF(TbUF idUF) {
        this.idUF = idUF;
    }

    public TbMeioResposta getIdMeioResposta() {
        return idMeioResposta;
    }

    public void setIdMeioResposta(TbMeioResposta idMeioResposta) {
        this.idMeioResposta = idMeioResposta;
    }

    public TbMeioEntrada getIdMeioEntrada() {
        return idMeioEntrada;
    }

    public void setIdMeioEntrada(TbMeioEntrada idMeioEntrada) {
        this.idMeioEntrada = idMeioEntrada;
    }

    public TbFaixaEtaria getIdFaixaEtaria() {
        return idFaixaEtaria;
    }

    public void setIdFaixaEtaria(TbFaixaEtaria idFaixaEtaria) {
        this.idFaixaEtaria = idFaixaEtaria;
    }

    public TbGrauInstrucao getIdGrauInstrucao() {
        return idGrauInstrucao;
    }

    public void setIdGrauInstrucao(TbGrauInstrucao idGrauInstrucao) {
        this.idGrauInstrucao = idGrauInstrucao;
    }

    public TbAreaEntrada getIdAreaEntrada() {
        return idAreaEntrada;
    }

    public void setIdAreaEntrada(TbAreaEntrada idAreaEntrada) {
        this.idAreaEntrada = idAreaEntrada;
    }

    public Collection<TbComunicacaoExterna> getTbComunicacaoExternaCollection() {
        return tbComunicacaoExternaCollection;
    }

    public void setTbComunicacaoExternaCollection(Collection<TbComunicacaoExterna> tbComunicacaoExternaCollection) {
        this.tbComunicacaoExternaCollection = tbComunicacaoExternaCollection;
    }

    public Collection<TbEncaminhamento> getTbEncaminhamentoCollection() {
        return tbEncaminhamentoCollection;
    }

    public void setTbEncaminhamentoCollection(Collection<TbEncaminhamento> tbEncaminhamentoCollection) {
        this.tbEncaminhamentoCollection = tbEncaminhamentoCollection;
    }
    
    public String getNrPronac() {
		return nrPronac;
	}

	public void setNrPronac(String nrPronac) {
		this.nrPronac = nrPronac;
	}

	public String getNrProcesso() {
		return nrProcesso;
	}

	public void setNrProcesso(String nrProcesso) {
		this.nrProcesso = nrProcesso;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idManifestacao != null ? idManifestacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TbManifestacao)) {
            return false;
        }
        TbManifestacao other = (TbManifestacao) object;
        if ((this.idManifestacao == null && other.idManifestacao != null) || (this.idManifestacao != null && !this.idManifestacao.equals(other.idManifestacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s > %s", getEntidade(), getDescricao());
    }

    private String getEntidade() {
        return "Manifestação";
    }

    private String getDescricao() {
        return nrManifestacao + "";
    }

    /**
     * @return the nrTelefone2
     */
    public String getNrTelefone2() {
        return nrTelefone2;
    }

    /**
     * @param nrTelefone2 the nrTelefone2 to set
     */
    public void setNrTelefone2(String nrTelefone2) {
        this.nrTelefone2 = nrTelefone2;
    }

    /**
     * @return the nrCelular
     */
    public String getNrCelular() {
        return nrCelular;
    }

    /**
     * @param nrCelular the nrCelular to set
     */
    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    public String getDsLocalidade() {
        return dsLocalidade;
    }

    public void setDsLocalidade(String nmLocalidade) {
        this.dsLocalidade = nmLocalidade;
    }

    /**
     * @return the dsMotivoOcultacao
     */
    public String getDsMotivoOcultacao() {
        return dsMotivoOcultacao;
    }

    /**
     * @param dsMotivoOcultacao the dsMotivoOcultacao to set
     */
    public void setDsMotivoOcultacao(String dsMotivoOcultacao) {
        this.dsMotivoOcultacao = dsMotivoOcultacao;
    }

    public boolean estaOculta(TbManifestacao manifestacao) {
        if (manifestacao.getStStatusOcultacao() == null) {
            return false;
        }
        return BooleanEnum.SIM.getId().equals(manifestacao.getStStatusManifestacao());
    }

    /**
     * @return the stStatusOcultacao
     */
    public String getStStatusOcultacao() {
        return stStatusOcultacao;
    }

    /**
     * @param stStatusOcultacao the stStatusOcultacao to set
     */
    public void setStStatusOcultacao(String stStatusOcultacao) {
        this.stStatusOcultacao = stStatusOcultacao;
    }

	public TbQuestionario getIdQuestionario() {
		return idQuestionario;
	}

	public void setIdQuestionario(TbQuestionario idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
}
