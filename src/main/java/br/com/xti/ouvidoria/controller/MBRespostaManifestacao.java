package br.com.xti.ouvidoria.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.xti.ouvidoria.dao.RespostaManifestacaoDAO;
import br.com.xti.ouvidoria.model.TbRespostaManifestacao;

/**
 * 
 * @author Guthierrez
 */
@SuppressWarnings("serial")
@ManagedBean(name = "mBRespostaManifestacao")
@ViewScoped
public class MBRespostaManifestacao implements Serializable {
	
	@EJB
	private RespostaManifestacaoDAO dao;
	private TbRespostaManifestacao resposta = new TbRespostaManifestacao();
	private TbRespostaManifestacao respostaNovo = new TbRespostaManifestacao();
	
	private void limpar(){
		resposta = new TbRespostaManifestacao();
		respostaNovo = new TbRespostaManifestacao();
	}
	
	public List<TbRespostaManifestacao> getTodos(){
		List<TbRespostaManifestacao> list = dao.findAll();
		Collections.sort(list);
		return list;
	}
	
	public void cadastrar(ActionEvent actionEvent) {
        try {
            respostaNovo.setDtCadastroRespostaManifestacao(new Date());
            getDao().create(getRespostaNovo());
            limpar();
            MensagemFaceUtil.info("Sucesso", "Inclusão realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            MensagemFaceUtil.erro("Erro", "Ocorreu um erro. Entre em contato com o administrador do sistema.");
        }
        setRespostaNovo(new TbRespostaManifestacao());
    }
	
	public void alterar(ActionEvent actionEvent) {
        try {
            if (getResposta() != null && getResposta().getDtCadastroRespostaManifestacao() != null) {
                getDao().edit(getResposta());
                limpar();
                MensagemFaceUtil.info("Sucesso", "Alteração realizada com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MensagemFaceUtil.erro("Erro", "Ocorreu um erro. Entre em contato com o administrador do sistema.");
        }
    }
	
	public void remover(ActionEvent actionEvent) {
        try {
            if (getResposta() != null && getResposta().getIdRespostaManifestacao() != null) {
                getDao().remove(getResposta());

                MensagemFaceUtil.info("Sucesso", "Exclusão realizada com sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MensagemFaceUtil.erro("Erro", "Ocorreu um erro. Entre em contato com o administrador do sistema.");
        }
    }

	public RespostaManifestacaoDAO getDao() {
		return dao;
	}

	public void setDao(RespostaManifestacaoDAO dao) {
		this.dao = dao;
	}

	public TbRespostaManifestacao getResposta() {
		return resposta;
	}

	public void setResposta(TbRespostaManifestacao resposta) {
		this.resposta = resposta;
	}

	public TbRespostaManifestacao getRespostaNovo() {
		return respostaNovo;
	}

	public void setRespostaNovo(TbRespostaManifestacao respostaNovo) {
		this.respostaNovo = respostaNovo;
	}

}
