package br.com.xti.ouvidoria.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Guthierrez Gregório de Souza
 *
 */
@ManagedBean(name = "mBAcompanharManifestacao")
@ViewScoped
public class MBAcompanharManifestacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroManifestacao;
	
	private String senhaManifestacao;
	
	public void abrirManifestacao() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext()
			.redirect("http://10.243.1.29:8088/ouvidoria/pages/manifestacao/administrar.xhtml?num="+numeroManifestacao+"&pass="+senhaManifestacao);
	}
	
	public void setNumeroManifestacao(Integer numeroManifestacao) {
		this.numeroManifestacao = numeroManifestacao;
	}
	
	public Integer getNumeroManifestacao() {
		return numeroManifestacao;
	}
	
	public String getSenhaManifestacao() {
		return senhaManifestacao;
	}
	
	public void setSenhaManifestacao(String senhaManifestacao) {
		this.senhaManifestacao = senhaManifestacao;
	}

}
