package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbLogOperacao.class)
public abstract class TbLogOperacao_ {

	public static volatile SingularAttribute<TbLogOperacao, String> tpOperacao;
	public static volatile SingularAttribute<TbLogOperacao, TbUsuario> idUsuario;
	public static volatile SingularAttribute<TbLogOperacao, Integer> idLogOperacoes;
	public static volatile SingularAttribute<TbLogOperacao, Date> dtDataLog;
	public static volatile SingularAttribute<TbLogOperacao, String> dsOperacao;

}

