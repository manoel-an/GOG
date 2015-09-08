package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbPerfil.class)
public abstract class TbPerfil_ {

	public static volatile SingularAttribute<TbPerfil, String> tpPerfil;
	public static volatile SingularAttribute<TbPerfil, Integer> idPerfil;
	public static volatile CollectionAttribute<TbPerfil, TbPerfilxFuncionalidade> tbPerfilxFuncionalidadeCollection;
	public static volatile SingularAttribute<TbPerfil, String> nmPerfil;
	public static volatile CollectionAttribute<TbPerfil, TbUsuarioxPerfil> tbUsuarioxPerfilCollection;

}

