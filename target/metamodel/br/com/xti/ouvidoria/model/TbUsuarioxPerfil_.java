package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbUsuarioxPerfil.class)
public abstract class TbUsuarioxPerfil_ {

	public static volatile SingularAttribute<TbUsuarioxPerfil, TbPerfil> idPerfil;
	public static volatile SingularAttribute<TbUsuarioxPerfil, Date> dtAtivacao;
	public static volatile SingularAttribute<TbUsuarioxPerfil, TbUsuario> idUsuario;
	public static volatile SingularAttribute<TbUsuarioxPerfil, Date> dtDesativacao;
	public static volatile SingularAttribute<TbUsuarioxPerfil, Integer> idUsuarioPerfil;

}

