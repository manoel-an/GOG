package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbAviso.class)
public abstract class TbAviso_ {

	public static volatile SingularAttribute<TbAviso, Date> dtInicioAviso;
	public static volatile SingularAttribute<TbAviso, TbUsuario> idUsuario;
	public static volatile SingularAttribute<TbAviso, Date> dtFimAviso;
	public static volatile SingularAttribute<TbAviso, Integer> idAvisos;
	public static volatile SingularAttribute<TbAviso, String> dsConteudo;
	public static volatile SingularAttribute<TbAviso, String> dsTitulo;

}

