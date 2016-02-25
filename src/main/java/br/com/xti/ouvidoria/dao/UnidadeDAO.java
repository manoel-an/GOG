package br.com.xti.ouvidoria.dao;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.Stateless;

import br.com.xti.ouvidoria.model.TbManifestacao;
import br.com.xti.ouvidoria.model.TbUnidade;

/**
 * @author renato
 */
@Stateless
public class UnidadeDAO extends AbstractDAO<TbUnidade> {
    public UnidadeDAO() {
        super(TbUnidade.class);
    }
    
      @Override
    public String getNomeEntidade() {
        return "Unidade";
    }
      
       public Collection<TbUnidade> getPorEncaminhamentoManifestacao(TbManifestacao idManifestacao) {
        Collection<TbUnidade> retorno = null;
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idManifestacao", idManifestacao);
            retorno = selectList("SELECT t.idUnidadeRecebeu FROM TbEncaminhamento t WHERE t.idManifestacao = :idManifestacao", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
       public Collection<TbUnidade> getPorClassificacao(Integer idClassificacao) {
           Collection<TbUnidade> retorno = null;
           try {
               HashMap<String, Integer> map = new HashMap<String, Integer>();
               map.put("idClassificacao", idClassificacao);
               retorno = selectList("SELECT u FROM TbClassificacao c INNER JOIN c.tbUnidadeCollection u where c.idClassificacao = :idClassificacao", map);
           } catch (Exception e) {
               e.printStackTrace();
           }
           return retorno;
       }

	public String buscarCNPJPorRegistroAGR(String sgUnidadeComoRegistroAGR) {
		try{
			return (String) this.getEntityManager()
            .createNativeQuery("select cnpj from tbcnpj_registroagr where reg_agr = :reg_agr")
            .setParameter("reg_agr", sgUnidadeComoRegistroAGR).getSingleResult();
		}catch (Exception e){
			return null;
		}
	}
}
