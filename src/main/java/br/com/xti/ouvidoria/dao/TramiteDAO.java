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

import br.com.xti.ouvidoria.model.TbEncaminhamento;
import br.com.xti.ouvidoria.model.TbTramite;
import br.com.xti.ouvidoria.model.TbTramitexAnexo;

/**
 *
 * @author renato
 */
@Stateless
public class TramiteDAO extends AbstractDAO<TbTramite> {

    public TramiteDAO() {
        super(TbTramite.class);
    }

    @Override
    public String getNomeEntidade() {
        return "Trâmite";
    }
    
    public Collection<TbTramite> getPorEncaminhamento(TbEncaminhamento idEncaminhamento) {
        Collection<TbTramite> retorno = null;
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idEncaminhamento", idEncaminhamento);
            retorno = selectList("SELECT t FROM TbTramite t WHERE t.idEncaminhamento = :idEncaminhamento ORDER BY t.idTramite ASC", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
    
    @SuppressWarnings("unchecked")
    public List<TbTramitexAnexo> getTramitexAnexoPorTramite(Integer idTramite) {
        String select = "select ta from TbTramite t inner join t.tbTramitexAnexoCollection ta where t.idTramite = :idTramite";
        List<TbTramitexAnexo> list = null;
        try {
            list = ((List<TbTramitexAnexo>) getEntityManager().createQuery(select)
                    .setParameter("idTramite", idTramite).getResultList());
        } catch (Exception e) {
            return new ArrayList<TbTramitexAnexo>(0);
        }
        return list;
    }    
}
