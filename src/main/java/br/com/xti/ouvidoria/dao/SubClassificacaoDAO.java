/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xti.ouvidoria.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import br.com.xti.ouvidoria.model.TbSubClassificacao;

/**
 *
 * @author renato
 */
@Stateless
public class SubClassificacaoDAO extends AbstractDAO<TbSubClassificacao> {
    public SubClassificacaoDAO() {
        super(TbSubClassificacao.class);
    }

    @Override
    public String getNomeEntidade() {
        return "Subclassificação";
    }

    public List<TbSubClassificacao> getSubClassificacoesPorManifestacao(Integer idManifestacao) {
        List<TbSubClassificacao> res = null;
        try {
            String select = "select s from TbManifestacao m INNER JOIN m.tbSubClassificacaoCollection s where m.idManifestacao = m.idManifestacao = :idManifestacao";
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idManifestacao", idManifestacao);
            res = selectList(select, map);
        } catch (Exception e) {
            return new ArrayList<TbSubClassificacao>(0);
        }
        return res;
    }
    
    public Collection<TbSubClassificacao> getPorClassificacao(Integer idClassificacao) {
        Collection<TbSubClassificacao> retorno = null;
        try {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("idClassificacao", idClassificacao);
            retorno = selectList("SELECT s FROM TbClassificacao c INNER JOIN c.tbSubClassificacaoCollection s where c.idClassificacao = :idClassificacao", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }      
}
