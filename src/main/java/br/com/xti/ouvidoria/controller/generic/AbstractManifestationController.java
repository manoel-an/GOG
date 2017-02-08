package br.com.xti.ouvidoria.controller.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jsoup.Jsoup;

import br.com.agr.ouvidoria.report.GeradorRelatorio;
import br.com.xti.ouvidoria.controller.MensagemFaceUtil;
import br.com.xti.ouvidoria.dao.AnexoDAO;
import br.com.xti.ouvidoria.dao.ComunicacaoExternaDAO;
import br.com.xti.ouvidoria.dao.EncaminhamentoDAO;
import br.com.xti.ouvidoria.dao.ManifestacaoDAO;
import br.com.xti.ouvidoria.dao.PrioridadeDAO;
import br.com.xti.ouvidoria.dao.TramiteDAO;
import br.com.xti.ouvidoria.dao.UnidadeDAO;
import br.com.xti.ouvidoria.exception.InfrastructureException;
import br.com.xti.ouvidoria.filtropersonalizado.FiltroPersonalizado;
import br.com.xti.ouvidoria.helper.EnumHelper;
import br.com.xti.ouvidoria.helper.FiltroHelper;
import br.com.xti.ouvidoria.helper.ValidacaoHelper;
import br.com.xti.ouvidoria.manifestacao.view.ManifestacaoTabView;
import br.com.xti.ouvidoria.model.TbClassificacao;
import br.com.xti.ouvidoria.model.TbComunicacaoExterna;
import br.com.xti.ouvidoria.model.TbComunicacaoExternaxAnexo;
import br.com.xti.ouvidoria.model.TbEncaminhamento;
import br.com.xti.ouvidoria.model.TbManifestacao;
import br.com.xti.ouvidoria.model.TbSubClassificacao;
import br.com.xti.ouvidoria.model.TbTramite;
import br.com.xti.ouvidoria.model.TbTramitexAnexo;
import br.com.xti.ouvidoria.model.TbUnidade;
import br.com.xti.ouvidoria.model.TbUsuario;
import br.com.xti.ouvidoria.model.enums.FuncaoUsuarioEnum;
import br.com.xti.ouvidoria.model.enums.StatusEncaminhamentoEnum;
import br.com.xti.ouvidoria.model.enums.UnidadeEnum;
import br.com.xti.ouvidoria.security.SecurityService;
import br.com.xti.ouvidoria.util.JSFUtils;

public abstract class AbstractManifestationController extends GeradorRelatorio {

	@Inject
	protected SecurityService securityService;
	
	@EJB
	protected ManifestacaoDAO dao;
	
	@EJB
	protected AnexoDAO anexoDAO;

	@EJB
	protected ComunicacaoExternaDAO comunicacaoExternaDAO;

	@EJB
	protected EncaminhamentoDAO encaminhamentoDAO;

	@EJB
	protected TramiteDAO tramiteDAO;

	@EJB
	protected UnidadeDAO unidadeDAO;
	
	@EJB
	protected PrioridadeDAO prioridadeDAO;
	
	/** Manifestação que está sendo gerenciada */
	protected TbManifestacao manifestacao;
	protected List<ManifestacaoTabView> tabs;
	/** Lista de Unidades que o usuário pode realizar um trâmite/encaminhamento */
	protected List<TbUnidade> listaUnidadesTramite;
	/** Unidades que podem ser área solucionadora (Já tem encaminhamento) */
	protected List<TbUnidade> unidades;

	protected Integer idUnidadeTramite;
	protected Integer idUsuarioTramite;
	
	// Carregar Informações da Manifestação (GET Request)
	// Suporte para multi abas
	protected Integer manifestationId;
	protected Integer manifestationNumber;
	protected String manifestationPass;
	
	// Abas Trâmites
	protected ManifestacaoTabView tabViewSelecionada;
	protected Integer tabViewAtiva = 0;
	
	@SuppressWarnings("all")
	public void loadManifestation() {
		boolean redirectToHomePage = false;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(!context.isValidationFailed()) {
			if(ValidacaoHelper.isEmpty(manifestationNumber)) {
				redirectToHomePage = true;
			} else {
				FiltroPersonalizado filtroPadrao = new FiltroPersonalizado();
	    		FuncaoUsuarioEnum funcao = securityService.getUserProfile();
	    		if(!(funcao == FuncaoUsuarioEnum.ADMINISTRADOR || funcao == FuncaoUsuarioEnum.OUVIDOR)) {
	    			filtroPadrao = FiltroHelper.getFiltrosPadrao(securityService.getUser());
	    		}
				
	    		FiltroPersonalizado filtroNumeroManifestacao = new FiltroPersonalizado();
	    		filtroNumeroManifestacao.setManIdRangeDe(manifestationNumber);
	    		filtroNumeroManifestacao.setManIdRangeAte(manifestationNumber);
	    		
	    		TbManifestacao oldManifestation = getOldManifestation();
	    		List<TbManifestacao> lista = dao.getManifestacoes(filtroNumeroManifestacao);
	    		if(ValidacaoHelper.isNotEmpty(lista)) {
	    			manifestacao = lista.get(0);
	    			
	    			//Verifica ultimo trâmite da atualização e atualiza data e visulização para exibir ou não icone de nova mensagem na manifestação
	    			if(securityService.loggedIn() && !securityService.isManifestante() && manifestacao != null){
	    				TbTramite ultimoTramite = getUltimoTramiteManifestacao(manifestacao);
	    				if(ultimoTramite == null){
	    					ultimoTramite = getUltimaComunicaxaoExterna(manifestacao);
	    				}
	    				if(ultimoTramite != null){
	    					try {
	    						if(ultimoTramite.getIdUsuarioEmissor() == null){
	    							manifestacao.setDtUltimaVisualizacao(new Date());
	    							dao.edit(manifestacao);
	    						}else{
	    							if(ultimoTramite.getIdUsuarioEmissor().getIdUnidade() == null){
	    								manifestacao.setDtUltimaVisualizacao(new Date());
		    							dao.edit(manifestacao);
	    							}else{
	    								if(ultimoTramite.getIdUsuarioEmissor().getIdUnidade().getIdUnidade() != securityService.getUser().getIdUnidade().getIdUnidade()){
	    									manifestacao.setDtUltimaVisualizacao(new Date());
			    							dao.edit(manifestacao);
	    								}
	    							}
	    						}
	    					} catch (InfrastructureException e) {
	    							e.printStackTrace();
	    					}
	    				}
	    			}

	    			if(ValidacaoHelper.isEmpty(oldManifestation)) {
	    				JSFUtils.setSessionAttribute("manifestation", manifestacao);
	    			}
	    		}
	    		
				if(manifestacao == null) {
					redirectToHomePage = true;
				} else {
					// Se for usuário não logado verifica se informou o id da manifestação
					if(securityService.getUser() == null && !manifestacao.getDsSenha().equals(manifestationPass)) {
						redirectToHomePage = true;
					}
				}
				
			}
		}
		
		// Seta última visualização da manifestação
		
		if(redirectToHomePage) {
			redirectToHomePage();
		}
	}

	private TbManifestacao getOldManifestation() {
		TbManifestacao oldManifestation = null;
		Object manifestation = JSFUtils.getSessionAttribute("manifestation");
		if(ValidacaoHelper.isNotEmpty(manifestation) && manifestation instanceof TbManifestacao) {
			oldManifestation = (TbManifestacao) manifestation;
		}
		return oldManifestation;
	}
	
	private void redirectToHomePage() {
		try {
			JSFUtils.redirect("/pages/erro/erro?type=3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean verificarExistenciaNovoTramite(TbManifestacao manifestacao){
    	if(manifestacao.getDtUltimaVisualizacao() != null && securityService.loggedIn() && !securityService.isManifestante()){
    			TbTramite ultimoTramite = getUltimoTramiteManifestacao(manifestacao);
    			if(ultimoTramite == null)
    				return false;
    			if(ultimoTramite.getIdUsuarioEmissor() != null){
    				if(ultimoTramite.getIdUsuarioEmissor().getIdUnidade() != null){
    					if(ultimoTramite.getDtTramite().getTime() > manifestacao.getDtUltimaVisualizacao().getTime()
    						&& ultimoTramite.getIdUsuarioEmissor().getIdUnidade().getIdUnidade() != securityService.getUser().getIdUnidade().getIdUnidade())
    						return true;
    					else
    						return false;
    				}else{
    					return false;
    				}
    			}else{
    				if(ultimoTramite.getDtTramite().getTime() > manifestacao.getDtUltimaVisualizacao().getTime()){
    					return true;
    				}else{
    					return false;
    				}
    			}
    	}else{
    		return false;
    	}
    }
	
	public Boolean verificaExistenciaNovaComunicacaoExterna(TbManifestacao manifestacao){
		if(manifestacao.getDtUltimaVisualizacao() != null && securityService.loggedIn() && (securityService.isOuvidor() || securityService.isAdministrador())){
			TbTramite ultimoTramite = getUltimaComunicaxaoExterna(manifestacao);
			if(ultimoTramite == null)
				return false;
			if(ultimoTramite.getIdUsuarioEmissor() != null){
				if(ultimoTramite.getIdUsuarioEmissor().getIdUnidade() != null){
					if(ultimoTramite.getDtTramite().getTime() > manifestacao.getDtUltimaVisualizacao().getTime()
						&& ultimoTramite.getIdUsuarioEmissor().getIdUnidade().getIdUnidade() != securityService.getUser().getIdUnidade().getIdUnidade())
						return true;
					else
						return false;
				}else{
					return false;
				}
			}else{
				if(ultimoTramite.getDtTramite().getTime() > manifestacao.getDtUltimaVisualizacao().getTime()){
					return true;
				}else{
					return false;
				}
			}
	}else{
		return false;
	}
	}
	
	public TbTramite getUltimoTramiteManifestacao(TbManifestacao manifestacao){
		List<TbTramite> tramites = new ArrayList<>(0);
		
		for (TbEncaminhamento enc : manifestacao.getTbEncaminhamentoCollection()){
			tramites.addAll(enc.getTbTramiteCollection());
		}
		
		Collections.sort(tramites, new Comparator<TbTramite>() {
			@Override
			public int compare(TbTramite t1, TbTramite t2) {
				return Long.valueOf(t1.getDtTramite().getTime()).compareTo(Long.valueOf(t2.getDtTramite().getTime()));
			}
		});
		if(!tramites.isEmpty()){
			TbTramite ultimoTramite = tramites.get(tramites.size() -1);
			return ultimoTramite;
		}else{
			return null;
		}
		
	}
	
	public TbTramite getUltimaComunicaxaoExterna(TbManifestacao manifestacao){
		List<TbTramite> tramites = new ArrayList<>(0);
		
		if(!securityService.isInterlocutor() && !securityService.isOperador())
			tramites.addAll(converterTramitesExternosParaInternos((List<TbComunicacaoExterna>) manifestacao.getTbComunicacaoExternaCollection()));

		Collections.sort(tramites, new Comparator<TbTramite>() {
			@Override
			public int compare(TbTramite t1, TbTramite t2) {
				return Long.valueOf(t1.getDtTramite().getTime()).compareTo(Long.valueOf(t2.getDtTramite().getTime()));
			}
		});
		if(!tramites.isEmpty()){
			TbTramite ultimoTramite = tramites.get(tramites.size() -1);
			return ultimoTramite;
		}else{
			return null;
		}
	}
	
	public void imprimirTodosDadosManifestacao(){
		List<TbTramite> tramites = new ArrayList<>(0);
		
		if(!securityService.isInterlocutor() && !securityService.isOperador())
			tramites.addAll(converterTramitesExternosParaInternos((List<TbComunicacaoExterna>) manifestacao.getTbComunicacaoExternaCollection()));

		for (TbEncaminhamento enc : manifestacao.getTbEncaminhamentoCollection()){
			tramites.addAll(enc.getTbTramiteCollection());
		}
		
		recuperarStringAnexosTramites(tramites);

		for(TbTramite tramite : tramites){
			tramite.setDsDescricao(Jsoup.parse(tramite.getDsDescricao()).text());
		}
		
		Collections.sort(tramites, new Comparator<TbTramite>() {
			@Override
			public int compare(TbTramite t1, TbTramite t2) {
				return Long.valueOf(t1.getDtTramite().getTime()).compareTo(Long.valueOf(t2.getDtTramite().getTime()));
			}
		});
		
		try {
	    	adicionaParametroRelatorio("numeroManifestacao", String.valueOf(manifestacao.getNrManifestacao()));
	    	adicionaParametroRelatorio("dataManifestacao", manifestacao.getDtCadastro());
	    	adicionaParametroRelatorio("atendente", manifestacao.getIdUsuarioAnalisador().getNmUsuario());
	    	adicionaParametroRelatorio("prestadoraServico", manifestacao.getPrestadoraServico());
	    	adicionaParametroRelatorio("titulo", getClassificacaoNome());
	    	adicionaParametroRelatorio("tipoSolicitacao", manifestacao.getTipoSolicitacao());
	    	adicionaParametroRelatorio("nomeManifestante", manifestacao.getNmPessoa());
	    	adicionaParametroRelatorio("telefoneUm", manifestacao.getNrTelefone());
	    	adicionaParametroRelatorio("endereco", manifestacao.getEnderecoCompleto());
	    	adicionaParametroRelatorio("outrosTelefones", manifestacao.getOutrosTelefones());
	    	adicionaParametroRelatorio("tipoManifestacao", manifestacao.getIdTipoManifestacao().getNmTipoManifestacao());
	    	adicionaParametroRelatorio("classificacao", getClassificaoESubClassificacao());
	    	adicionaParametroRelatorio("texto", Jsoup.parse(manifestacao.getDsTextoManifestacaoFormatado()).text());
	    	
	    	//saneamento
	    	adicionaParametroRelatorio("ra", manifestacao.getRa());
	    	adicionaParametroRelatorio("numeroConta", manifestacao.getNumeroConta());
	    	adicionaParametroRelatorio("titularidade", manifestacao.getTitularidade());
	    	
	    	//transporte
	    	adicionaParametroRelatorio("placaVeiculo", manifestacao.getPlacaVeiculo());
	    	adicionaParametroRelatorio("numeroVeiculo", manifestacao.getNumeroVeiculo());
	    	adicionaParametroRelatorio("horario", manifestacao.getHorario());
	    	adicionaParametroRelatorio("motorista", manifestacao.getMotorista());

	    	//energia
	    	adicionaParametroRelatorio("unidadeConsumidora", manifestacao.getUnidadeConsumidora());
	    	
	    	adicionaParametroRelatorio("logoAGR", "logoagr.jpg");
			baixarPDF("todosdialogosmanifestacao", tramites, "tramites");
		} catch (InfrastructureException e) {
			MensagemFaceUtil.erro("Erro!", e.getMessage());
		}
	}
	
	public List<TbTramite> converterTramitesExternosParaInternos(List<TbComunicacaoExterna> comunicacoes){
		List<TbTramite> tramites = new ArrayList<>(0);
		recuperarStringAnexosExterno(comunicacoes);
		
		TbUnidade unidadeManifestante = new TbUnidade();
		unidadeManifestante.setNmUnidade("Ouvidoria AGR");
		unidadeManifestante.setIdUnidade(0);
		
		for(TbComunicacaoExterna comunicacao : comunicacoes){
			TbTramite tramite = new TbTramite();
			tramite.setIdTramite(comunicacao.getIdComunicacaoExterna());
			tramite.setDsDescricao(comunicacao.getDsComunicacao());
			tramite.setIdUsuarioReceptor(comunicacao.getIdManifestacao().getIdUsuarioManifestante());
			tramite.setDtTramite(comunicacao.getDtComunicacao());
			tramite.setIdUsuarioEmissor(comunicacao.getIdUsuario());
			tramite.setIdUnidadeEnvio(unidadeManifestante);
			tramite.setStrAnexos(comunicacao.getStrAnexos());
			tramites.add(tramite);
		}
		return tramites;
	}
	
	@SuppressWarnings("unchecked")
	public void imprimirDialogoEncaminhamento(ManifestacaoTabView encaminhamento){
		List<TbTramite> tramites = (List<TbTramite>) encaminhamento.getConteudo();
		recuperarStringAnexosTramites(tramites);
		adicionaParametroRelatorio("numeroManifestacao", encaminhamento.getEncaminhamento().getIdManifestacao().getNrManifestacao());
		adicionaParametroRelatorio("logoAGR", "logoagr.jpg");
		adicionaParametroRelatorio("dataInicioDialogo", encaminhamento.getEncaminhamento().getDtCriacaoEncaminhamento());
		adicionaParametroRelatorio("origemTramite", encaminhamento.getEncaminhamento().getIdUnidadeEnviou().getNmUnidade());
		adicionaParametroRelatorio("destinoTramite", encaminhamento.getEncaminhamento().getIdUnidadeRecebeu().getNmUnidade());
		try {
			baixarPDF("dialogomanifestacao", tramites, "tramites");
		} catch (InfrastructureException e) {
			MensagemFaceUtil.erro("Erro!", e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void imprimirDialogoManifestante(ManifestacaoTabView encaminhamento) throws InfrastructureException {
		List<TbComunicacaoExterna> comunicacoes = (List<TbComunicacaoExterna>) encaminhamento.getConteudo();
		recuperarStringAnexosExterno(comunicacoes);
		adicionaParametroRelatorio("numeroManifestacao", manifestacao.getNrManifestacao());
		adicionaParametroRelatorio("logoAGR", "logoagr.jpg");
		adicionaParametroRelatorio("dataInicioDialogo", comunicacoes.get(0).getDtComunicacao());
		adicionaParametroRelatorio("origemTramite", comunicacoes.get(0).getIdUsuario() == null ? "Manifestante" : "Ouvidoria");
		adicionaParametroRelatorio("destinoTramite", comunicacoes.get(0).getIdUsuario() != null ? "Manifestante" : "Ouvidoria");
		adicionaParametroRelatorio("nomeManifestante", manifestacao.getNmPessoa());
		baixarPDF("dialogomanifestante", comunicacoes, "tramites");
	}
	
	

	public void recuperarStringAnexosTramites(List<TbTramite> tramites){
		for(TbTramite t : tramites){
			StringBuilder anexos = new StringBuilder("");
			for(TbTramitexAnexo a : t.getTbTramitexAnexoCollection()){
				anexos.append(a.getIdAnexo().getNmAnexo());
				anexos.append(", ");
			}
			if(anexos.length() > 2)
				anexos.replace(anexos.length()-2, anexos.length(), "");
			t.setStrAnexos(anexos.toString());
		}
		
	}
	
	public void recuperarStringAnexosExterno(List<TbComunicacaoExterna> comunicacoes){
		for(TbComunicacaoExterna c : comunicacoes){
			StringBuilder anexos = new StringBuilder("");
			for(TbComunicacaoExternaxAnexo a : c.getTbComunicacaoExternaxAnexoCollection()){
				anexos.append(a.getIdAnexo().getNmAnexo());
				anexos.append(", ");
			}
			if(anexos.length() > 2)
				anexos.replace(anexos.length()-2, anexos.length(), "");
			c.setStrAnexos(anexos.toString());
		}
		
	}
	
	
	public void mountTabs() {
        int index = 0;
        setTabs(new ArrayList<ManifestacaoTabView>());
        // Monta a aba "Ouvidoria X Manifestante"
        ManifestacaoTabView comExt = new ManifestacaoTabView();
        comExt.setIndex(index);
        comExt.setTitulo("Ouvidoria x Manifestante");
        Collection<TbComunicacaoExterna> comunicacoes = comunicacaoExternaDAO.getPorManifestacao(manifestacao);
        comExt.setConteudo(comunicacoes);
        // Se o usuário for INTERLOCUTOR ou OPERADOR só visualiza as comunicações externas públicas
        if (securityService.isInterlocutor() || securityService.isOperador()) {
        	Iterator<TbComunicacaoExterna> comunicacoesIterator = comunicacoes.iterator();
        	for( ; comunicacoesIterator.hasNext() ; ) {
        		TbComunicacaoExterna comunicacao = comunicacoesIterator.next();
        		if(!comunicacao.isComunicacaoPublica()) {
        			comunicacoesIterator.remove();
        		}
        	}
        	
        	if(comunicacoes.size() > 0) {
        		getTabs().add(comExt);
        	}
        } else {
        	getTabs().add(comExt);
        }
        
        
        // Monta as abas das Unidades
        if(!securityService.isManifestante()) {
        	TbUnidade unidadeUsuarioLogado = securityService.getUser().getIdUnidade();
        	for (TbEncaminhamento enc : encaminhamentoDAO.getPorManifestacao(manifestacao)) {
				// Se o usuário for Interlocutor ou Operador só irá mostrar os
				// trâmites enviados ou recebidos pela sua área. A menos que
				// exista um trâmite de outra área marcado como público
        		if((securityService.isInterlocutor() || securityService.isOperador())	// Interlocutor ou Operador
					&& !(unidadeUsuarioLogado.equals(enc.getIdUnidadeEnviou())			// Unidade do usuário NÃO enviou...
					|| unidadeUsuarioLogado.equals(enc.getIdUnidadeRecebeu()))			// ...ou recebeu o encaminhamento
					&& !enc.temTramitePublico()) {										// Encaminhamento NÃO tem trâmite público
						continue;
        		}
        		
				TbUnidade departmentSent = enc.getIdUnidadeEnviou();
				TbUnidade departmentReceived = enc.getIdUnidadeRecebeu();
				String tabTitle = String.format("%s x %s", departmentSent.getNomeFormatado(), departmentReceived.getNomeFormatado());
				
				ManifestacaoTabView tabView = new ManifestacaoTabView();
				tabView.setIndex(++index);
				tabView.setTitulo(tabTitle);
				tabView.setConteudo(tramiteDAO.getPorEncaminhamento(enc));
				tabView.setEncaminhamento(enc);
				tabView.setUnidadeEnviou(departmentSent);
				tabView.setUnidadeRecebeu(departmentReceived);
				getTabs().add(tabView);
        	}
        }
    }
	
	public boolean mostrarTramite(Object tramiteObj) {
		boolean mostrarTramite = false;
		
		if(tramiteObj instanceof TbTramite) {
			if(securityService.isInterlocutor() || securityService.isOperador()) {
				TbTramite tramite = (TbTramite) tramiteObj;
				TbEncaminhamento enc = tramite.getIdEncaminhamento();
				TbUnidade unidade = securityService.getUser().getIdUnidade();
				
				if(unidade.equals(enc.getIdUnidadeEnviou()) || unidade.equals(enc.getIdUnidadeRecebeu())) {
					mostrarTramite = true;
				} else {
					mostrarTramite = tramite.isTramitePublico();
				}
			} else {
				mostrarTramite = true;
			}
		}
	
		return mostrarTramite;
	}
	
	public String mostrarPessoasEnvolvidasNaComunicacaoExterna(Object comunicacao) {
		StringBuilder sb = new StringBuilder(" - ");
		TbComunicacaoExterna ce = null;
		if (comunicacao instanceof TbComunicacaoExterna) {
			ce = (TbComunicacaoExterna) comunicacao;
			TbUsuario u = ce.getIdUsuario();
			if (u != null) {
				if (u.getIdUnidade() != null && UnidadeEnum.OUVIDORIA.getId().equals(u.getIdUnidade().getIdUnidade())) {
					sb.append("Ouvidoria disse:");
				} else {
					sb.append(u.getNmUsuario()).append(" disse:");
				}
			} else {
				String nomeManifestante = ValidacaoHelper.isNotEmpty(manifestacao.getNmPessoa()) 
						? manifestacao.getNmPessoa() : "Anônimo";
				sb.append(nomeManifestante).append(" disse:");
			}
		}
		return sb.toString();
	}

	public String mostrarAreaDeQuemRealizouComunicacao(Object comunicacao) {
		StringBuilder sb = new StringBuilder();
		TbComunicacaoExterna ce = null;
		if (comunicacao instanceof TbComunicacaoExterna) {
			ce = (TbComunicacaoExterna) comunicacao;
			TbUsuario u = ce.getIdUsuario();

			if (u != null && FuncaoUsuarioEnum.ADMINISTRADOR.getId().equals(u.getTpFuncao())) {
				sb.append("administrador");
			} else if (u != null && FuncaoUsuarioEnum.OUVIDOR.getId().equals(u.getTpFuncao())) {
				sb.append("ouvidoria");
			} else {
				sb.append("manifestante");
			}
		}
		return sb.toString();
	}
	
	public String mostrarPessoasEnvolvidasNoTramite(Object tramite) {
		StringBuilder sb = new StringBuilder(" - ");
		TbTramite t = null;
		if (tramite instanceof TbTramite) {
			t = (TbTramite) tramite;
			TbUnidade unidadeEnvio = t.getIdUnidadeEnvio();
			TbUsuario usuarioReceptor = t.getIdUsuarioReceptor();

			// Se foi um Ouvidor que fez o trâmite aparece o nome Ouvidoria e não o nome do usuário
			if (UnidadeEnum.OUVIDORIA.getId().equals(t.getIdUsuarioEmissor().getIdUnidade().getIdUnidade())) {
				sb.append("Ouvidoria disse para ");
			} else {
				sb.append(t.getIdUsuarioEmissor().getNmUsuario()).append(" disse para ");
			}

			if (unidadeEnvio != null && usuarioReceptor != null) {
				sb.append(usuarioReceptor.getNmUsuario())
				.append(" (").append(unidadeEnvio.getNmUnidade()).append(")");
			} else if (unidadeEnvio != null) {
				sb.append(unidadeEnvio.getNmUnidade());
			} else if (usuarioReceptor != null) {
				sb.append(usuarioReceptor.getNmUsuario());
			}
		}
		return sb.toString();
	}
	
	public String mostrarAreaDeQuemRealizouTramite(Object tramite) {
		try {
			StringBuilder sb = new StringBuilder();
			TbTramite t = null;
			if (tramite instanceof TbTramite) {
				t = (TbTramite) tramite;
				TbUsuario u = t.getIdUsuarioEmissor();

				if (FuncaoUsuarioEnum.ADMINISTRADOR.getId().equals(u.getTpFuncao())) {
					sb.append("administrador");
				} else if (FuncaoUsuarioEnum.OUVIDOR.getId().equals(u.getTpFuncao())) {
					sb.append("ouvidor");
				} else {
					sb.append("unidade");
				}
			}
			return sb.toString();
		} catch (NullPointerException npe) {
			return "";
		}
	}
	
	// Abas Trâmites
	public String getLabelSendMessage() {
		String labelName = "Enviar Mensagem ao ";
		if(isManifestante()) {
			labelName = labelName.concat("Ouvidor");
		} else {
			labelName = labelName.concat("Manifestante");
		}
		return labelName;
	}
	
	/**
	 * <p>Se o usuário for:</p>
	 * <ul>
	 * 	<li><b>ADMINISTRADOR ou OUVIDOR:</b> renderiza</li>
	 * 	<li><b>OPERADOR ou INTERLOCUTOR:</b> renderiza se o encaminhamento for para a área do usuário</li>
	 * 	<li><b>MANIFESTANTE:</b> não renderiza</li>
	 * </ul>
	 * 
	 * @param manifestationTabView Encaminhamento da Manifestação
	 * @return se vai ou não renderizar o botão de enviar mensagem a área do encaminhamento
	 */
	public boolean showSendButton(ManifestacaoTabView manifestationTabView) {
		boolean showSendButton = Boolean.FALSE;
		if(!isManifestante()) {
			if(securityService.isInterlocutor() || securityService.isOperador()) {
				TbUnidade userDepartment = securityService.getUser().getIdUnidade();
				TbUnidade departmentReceived = manifestationTabView.getUnidadeRecebeu();
				TbUnidade departmentSent = manifestationTabView.getUnidadeEnviou();
				
				if (userDepartment.equals(departmentReceived)
						|| userDepartment.equals(departmentSent)) {
					showSendButton = Boolean.TRUE;
				}
			} else {
				showSendButton = Boolean.TRUE;
			}
		}
		return showSendButton;
	}
	
	public String getStatusReferralName(TbEncaminhamento referral) {
		String referralStatusId = referral.getStEncaminhamento();
		StatusEncaminhamentoEnum referralStatus = EnumHelper.getStatusEncaminhamentoEnum(referralStatusId);
		return referralStatus.getDescricao();
	}
	
	public String getStatusReferralClass(TbEncaminhamento referral) {
		String referralStatusId = referral.getStEncaminhamento();
		StatusEncaminhamentoEnum referralStatus = EnumHelper.getStatusEncaminhamentoEnum(referralStatusId);
		String className = "";
		if(referralStatus == StatusEncaminhamentoEnum.ENCAMINHADA) {
			className = "statusTramiteEncaminhado";
		} else {
			className = "statusTramiteRetornado";
		}
		return className;
	}
	
	public String getUserProfileMessageClass(TbUsuario user) {
		String className = "notaAreaTecnica";
		if(user != null) {
			String userProfileId = user.getTpFuncao();
			FuncaoUsuarioEnum userProfile = EnumHelper.getFuncaoUsuarioEnum(userProfileId);
			if(userProfile == FuncaoUsuarioEnum.OUVIDOR || userProfile == FuncaoUsuarioEnum.ADMINISTRADOR) {
				className = "notaOuvidoria";
			}
		}
		return className;
	}
	
	@SuppressWarnings("all")
    public String getClassificacaoNome(){
    	List<TbClassificacao> classificacao = (List<TbClassificacao>) manifestacao.getTbClassificacaoCollection();
		List<TbSubClassificacao> tbSubClassificacoes = (List<TbSubClassificacao>) manifestacao.getTbSubClassificacaoCollection();
		
		StringBuilder sb = new StringBuilder();
		
		if(!classificacao.isEmpty()){
			sb.append(classificacao.get(0).getDsClassificacao());
		}
		
		return sb.toString();
    }
	
    public String getClassificaoESubClassificacao() {
		List<TbClassificacao> classificacao = (List<TbClassificacao>) manifestacao.getTbClassificacaoCollection();
		List<TbSubClassificacao> tbSubClassificacoes = (List<TbSubClassificacao>) manifestacao.getTbSubClassificacaoCollection();
		
		StringBuilder sb = new StringBuilder();
		
		if(!classificacao.isEmpty()){
			sb.append(classificacao.get(0).getDsClassificacao());
		}
		
		if(!tbSubClassificacoes.isEmpty()){
			sb.append(" - ").append(tbSubClassificacoes.get(0).getDsSubClassificacao());
		}
		
		return sb.toString();
	}
	
	public boolean disableSendMessageButton() {
		return false;
	}
	
	public boolean isManifestante() {
		boolean isManifestante = Boolean.FALSE;
		FuncaoUsuarioEnum profile = securityService.getUserProfile();
		if(profile == null || profile == FuncaoUsuarioEnum.MANIFESTANTE) {
			isManifestante = Boolean.TRUE;
		}
		return isManifestante;
	}
	
	// GETTES e SETTERS
	public TbManifestacao getManifestacao() {
		return manifestacao;
	}

	public void setManifestacao(TbManifestacao manifestacao) {
		this.manifestacao = manifestacao;
	}

	public List<ManifestacaoTabView> getTabs() {
		return tabs;
	}

	private void setTabs(List<ManifestacaoTabView> tabs) {
		this.tabs = tabs;
	}

	public Integer getTabViewAtiva() {
		return tabViewAtiva;
	}

	public void setTabViewAtiva(Integer tabViewAtiva) {
		this.tabViewAtiva = tabViewAtiva;
	}

	public ManifestacaoTabView getTabViewSelecionada() {
		return tabViewSelecionada;
	}

	public void setTabViewSelecionada(ManifestacaoTabView tabViewSelecionada) {
		this.tabViewSelecionada = tabViewSelecionada;
	}

	public List<TbUnidade> getListaUnidadesTramite() {
		return listaUnidadesTramite;
	}

	public void setListaUnidadesTramite(List<TbUnidade> listaUnidadesTramite) {
		this.listaUnidadesTramite = listaUnidadesTramite;
	}

	public Integer getIdUnidadeTramite() {
		return idUnidadeTramite;
	}

	public void setIdUnidadeTramite(Integer idUnidadeTramite) {
		this.idUnidadeTramite = idUnidadeTramite;
	}

	public Integer getIdUsuarioTramite() {
		return idUsuarioTramite;
	}

	public void setIdUsuarioTramite(Integer idUsuarioTramite) {
		this.idUsuarioTramite = idUsuarioTramite;
	}

	public Integer getManifestationNumber() {
		return manifestationNumber;
	}

	public void setManifestationNumber(Integer manifestationNumber) {
		this.manifestationNumber = manifestationNumber;
	}
	
	public Integer getManifestationId() {
		return manifestationId;
	}

	public void setManifestationId(Integer manifestationId) {
		this.manifestationId = manifestationId;
	}

	public String getManifestationPass() {
		return manifestationPass;
	}

	public void setManifestationPass(String manifestationPass) {
		this.manifestationPass = manifestationPass;
	}

	public List<TbUnidade> getUnidades() {
		return unidades;
	}
	
}
