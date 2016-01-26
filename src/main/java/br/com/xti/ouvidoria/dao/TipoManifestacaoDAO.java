/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xti.ouvidoria.dao;

import javax.ejb.Stateless;

import br.com.xti.ouvidoria.model.TbTipoManifestacao;

/**
 *
 * @author renato
 */
@Stateless
public class TipoManifestacaoDAO extends AbstractDAO<TbTipoManifestacao> {
	
    public TipoManifestacaoDAO() {
        super(TbTipoManifestacao.class);
    }
    
    public TbTipoManifestacao getPorNome(String nome){
    	try{
    		return this.getEntityManager().createQuery("SELECT tm FROM TbTipoManifestacao tm WHERE tm.nmTipoManifestacao = :nome", TbTipoManifestacao.class)
    				.setParameter("nome", nome)
    				.getSingleResult();
    	}catch(Exception e){
    		return null;
    	}
    	
    }
    
    
    @Override
    public String getNomeEntidade() {
        return "Tipo de Manifestação";
    }
}
