package br.com.xti.ouvidoria.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import br.com.xti.ouvidoria.model.TbClassificacao;
import br.com.xti.ouvidoria.model.TbUnidade;

/**
 *
 * @author renato
 */
@Stateless
public class ClassificacaoDAO extends AbstractDAO<TbClassificacao> {

    public ClassificacaoDAO() {
        super(TbClassificacao.class);
    }

    @Override
    public String getNomeEntidade() {
        return "Classificação";
    }

    public List<TbClassificacao> findByUnidade(TbUnidade tbUnidade) throws Exception {
        HashMap<String, TbUnidade> map = new HashMap<String, TbUnidade>();
        map.put("idUnidade", tbUnidade);
        List<TbClassificacao> res = getList("findByUnidade", map);
        return res;
    }
    
    public List<TbClassificacao> getClassificacoesPorManifestacao(Integer idManifestacao) {
        List<TbClassificacao> res = null;
        try {
            String select = "select c from TbManifestacao m INNER JOIN m.tbClassificacaoCollection c where m.idManifestacao = m.idManifestacao = :idManifestacao";
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idManifestacao", idManifestacao);
            res = selectList(select, map);
        } catch (Exception e) {
            return new ArrayList<TbClassificacao>(0);
        }
        return res;
    }
    
    public List<TbClassificacao> getClassificacoesPorUnidade(Integer idUnidade) {
        List<TbClassificacao> res = null;
        try {
            String select = "select c from TbUnidade u INNER JOIN u.tbClassificacaoCollection c where u.idUnidade = :idUnidade";
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idUnidade", idUnidade);
            res = selectList(select, map);
        } catch (Exception e) {
            return new ArrayList<TbClassificacao>(0);
        }
        return res;
    }
    
    public List<TbClassificacao> getClassificacoesPorSubClassificacao(Integer idSubClassficacao) {
        List<TbClassificacao> res = null;
        try {
            String select = "select c from TbSubClassificacao s INNER JOIN s.tbClassificacaoCollection c where s.idSubClassificacao = :idSubClassficacao";
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idSubClassficacao", idSubClassficacao);
            res = selectList(select, map);
        } catch (Exception e) {
            return new ArrayList<TbClassificacao>(0);
        }
        return res;
    }     
}
