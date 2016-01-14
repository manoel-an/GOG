package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbAnexo.class)
public abstract class TbAnexo_ {

	public static volatile SingularAttribute<TbAnexo, String> nmAnexo;
	public static volatile CollectionAttribute<TbAnexo, TbTramitexAnexo> tbTramitexAnexoCollection;
	public static volatile CollectionAttribute<TbAnexo, TbComunicacaoExternaxAnexo> tbComunicacaoExternaxAnexoCollection;
	public static volatile CollectionAttribute<TbAnexo, TbEncaminhamentoxAnexo> tbEncaminhamentoxAnexoCollection;
	public static volatile SingularAttribute<TbAnexo, Integer> idAnexo;
	public static volatile CollectionAttribute<TbAnexo, TbManifestacaoxAnexo> tbManifestacaoxAnexoCollection;
	public static volatile SingularAttribute<TbAnexo, String> dsCaminhoAnexo;

}

