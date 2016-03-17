package br.com.xti.ouvidoria.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.com.xti.ouvidoria.dao.ClassificacaoDAO;
import br.com.xti.ouvidoria.helper.CdiHelper;
import br.com.xti.ouvidoria.model.TbClassificacao;

/**
 *
 * @author samuel.guimaraes
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@FacesConverter(value="classificacaoConverter")
public class ConversorClassificacao extends ConversorBase {
	
	private ClassificacaoDAO dao;

	public ConversorClassificacao() {
		try {
			dao = CdiHelper.getFacadeWithJNDI(ClassificacaoDAO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TbClassificacao classificacao = null;
        if(value == null || value.isEmpty()) {
            return classificacao;
        }
        
        try {
            classificacao = dao.find(Integer.parseInt(value));
        } catch (NumberFormatException e) { }
        return classificacao;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String r = null;
        try {
            if (value instanceof TbClassificacao) {
                r = ((TbClassificacao) value).getIdClassificacao().toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }
}
