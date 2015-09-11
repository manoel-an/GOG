package br.com.xti.ouvidoria.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import br.com.xti.ouvidoria.model.TbEncaminhamento;
import br.com.xti.ouvidoria.model.TbManifestacao;
import br.com.xti.ouvidoria.model.TbUnidade;
import br.com.xti.ouvidoria.model.enums.StatusEncaminhamentoEnum;

/**
 * @author renato
 */
@Stateless
public class EncaminhamentoDAO extends AbstractDAO<TbEncaminhamento> {

    public EncaminhamentoDAO() {
        super(TbEncaminhamento.class);
    }

    @Override
    public String getNomeEntidade() {
        return "Encaminhameno";
    }

    public Collection<TbEncaminhamento> getPorManifestacao(TbManifestacao idManifestacao) {
        Collection<TbEncaminhamento> retorno = null;
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idManifestacao", idManifestacao);
            retorno = selectList("SELECT t FROM TbEncaminhamento t WHERE t.idManifestacao = :idManifestacao", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public Collection<TbEncaminhamento> getPorManifestacaoUnidade(TbManifestacao idManifestacao, TbUnidade idUnidade) {
        Collection<TbEncaminhamento> retorno = null;
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idManifestacao", idManifestacao);
            map.put("idUnidade", idUnidade);
            retorno = selectList(
                    "SELECT t FROM TbEncaminhamento t WHERE t.idManifestacao = :idManifestacao AND t.idUnidadeEnvio = :idUnidade",
                    map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public Collection<TbEncaminhamento> getEncaminhamentoAberto(TbManifestacao idManifestacao) {
        Collection<TbEncaminhamento> retorno = null;
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idManifestacao", idManifestacao);
            map.put("stEncaminhamento", StatusEncaminhamentoEnum.ENCAMINHADA.getId());
            retorno = selectList(
                    "SELECT t FROM TbEncaminhamento t WHERE t.idManifestacao = :idManifestacao AND t.stEncaminhamento = :stEncaminhamento",
                    map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public List<TbEncaminhamento> getEncaminhamentosPorUnidade(Integer idUnidade) {
        List<TbEncaminhamento> res = null;
        try {
            String select = "select e from TbUnidade u INNER JOIN u.tbEncaminhamentoCollection e where u.idUnidade = :idUnidade";
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idUnidade", idUnidade);
            res = selectList(select, map);
        } catch (Exception e) {
            return new ArrayList<TbEncaminhamento>(0);
        }
        return res;
    }

    public TbEncaminhamento getEncaminhamentoPorTramite(int idTramite) {
        TbEncaminhamento enc = null;
        try {
            String select = "SELECT t.idEncaminhamento FROM TbTramite t WHERE t.idTramite = :idTramite";
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("idTramite", idTramite);
            enc = selectObject(select, map);
        } catch (Exception e) {
            return enc;
        }
        return enc;
    }
}
