/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xti.ouvidoria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author samuel.guimaraes
 */
@Entity
@Table(name = "tbAnexo")
@NamedQueries({
    @NamedQuery(name = "TbAnexo.findAll", query = "SELECT t FROM TbAnexo t"),
    @NamedQuery(name = "TbAnexo.findByIdAnexo", query = "SELECT t FROM TbAnexo t WHERE t.idAnexo = :idAnexo"),
    @NamedQuery(name = "TbAnexo.findByDsCaminhoAnexo", query = "SELECT t FROM TbAnexo t WHERE t.dsCaminhoAnexo = :dsCaminhoAnexo"),
    @NamedQuery(name = "TbAnexo.findByNmAnexo", query = "SELECT t FROM TbAnexo t WHERE t.nmAnexo = :nmAnexo")})
public class TbAnexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnexo")
    private Integer idAnexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "dsCaminhoAnexo")
    private String dsCaminhoAnexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmAnexo")
    private String nmAnexo;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnexo")*/
    @OneToMany(mappedBy = "idAnexo")
    private Collection<TbManifestacaoxAnexo> tbManifestacaoxAnexoCollection = new ArrayList<>();
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnexo")*/
    @OneToMany(mappedBy = "idAnexo")
    private Collection<TbComunicacaoExternaxAnexo> tbComunicacaoExternaxAnexoCollection = new ArrayList<>();
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnexo")*/
    @OneToMany(mappedBy = "idAnexo")
    private Collection<TbTramitexAnexo> tbTramitexAnexoCollection = new ArrayList<>();
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnexo")*/
    @OneToMany(mappedBy = "idAnexo")
    private Collection<TbEncaminhamentoxAnexo> tbEncaminhamentoxAnexoCollection = new ArrayList<>();

    public TbAnexo() {
    }

    public TbAnexo(Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

    public TbAnexo(Integer idAnexo, String dsCaminhoAnexo, String nmAnexo) {
        this.idAnexo = idAnexo;
        this.dsCaminhoAnexo = dsCaminhoAnexo;
        this.nmAnexo = nmAnexo;
    }

    public Integer getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

    public String getDsCaminhoAnexo() {
        return dsCaminhoAnexo;
    }

    public void setDsCaminhoAnexo(String dsCaminhoAnexo) {
        this.dsCaminhoAnexo = dsCaminhoAnexo;
    }

    public String getNmAnexo() {
        return nmAnexo;
    }

    public void setNmAnexo(String nmAnexo) {
        this.nmAnexo = nmAnexo;
    }

    public Collection<TbManifestacaoxAnexo> getTbManifestacaoxAnexoCollection() {
        return tbManifestacaoxAnexoCollection;
    }

    public void setTbManifestacaoxAnexoCollection(Collection<TbManifestacaoxAnexo> tbManifestacaoxAnexoCollection) {
        this.tbManifestacaoxAnexoCollection = tbManifestacaoxAnexoCollection;
    }

    public Collection<TbComunicacaoExternaxAnexo> getTbComunicacaoExternaxAnexoCollection() {
        return tbComunicacaoExternaxAnexoCollection;
    }

    public void setTbComunicacaoExternaxAnexoCollection(Collection<TbComunicacaoExternaxAnexo> tbComunicacaoExternaxAnexoCollection) {
        this.tbComunicacaoExternaxAnexoCollection = tbComunicacaoExternaxAnexoCollection;
    }

    public Collection<TbTramitexAnexo> getTbTramitexAnexoCollection() {
        return tbTramitexAnexoCollection;
    }

    public void setTbTramitexAnexoCollection(Collection<TbTramitexAnexo> tbTramitexAnexoCollection) {
        this.tbTramitexAnexoCollection = tbTramitexAnexoCollection;
    }

    public Collection<TbEncaminhamentoxAnexo> getTbEncaminhamentoxAnexoCollection() {
        return tbEncaminhamentoxAnexoCollection;
    }

    public void setTbEncaminhamentoxAnexoCollection(Collection<TbEncaminhamentoxAnexo> tbEncaminhamentoxAnexoCollection) {
        this.tbEncaminhamentoxAnexoCollection = tbEncaminhamentoxAnexoCollection;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsCaminhoAnexo == null) ? 0 : dsCaminhoAnexo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbAnexo other = (TbAnexo) obj;
		if (dsCaminhoAnexo == null) {
			if (other.dsCaminhoAnexo != null)
				return false;
		} else if (!dsCaminhoAnexo.equals(other.dsCaminhoAnexo))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return String.format("%s > %s",getEntidade(),getDescricao());
    }
    
    private String getEntidade(){
        return "Anexo";
    }
    private String getDescricao(){
        return dsCaminhoAnexo;
    }    
}
