package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbFuncionalidade.class)
public abstract class TbFuncionalidade_ {

	public static volatile SingularAttribute<TbFuncionalidade, Integer> idFuncionalidade;
	public static volatile SingularAttribute<TbFuncionalidade, String> dsFuncionalidade;
	public static volatile CollectionAttribute<TbFuncionalidade, TbPerfilxFuncionalidade> tbPerfilxFuncionalidadeCollection;

}

