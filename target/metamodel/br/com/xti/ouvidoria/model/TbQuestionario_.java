package br.com.xti.ouvidoria.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbQuestionario.class)
public abstract class TbQuestionario_ {

	public static volatile SingularAttribute<TbQuestionario, Date> dtInicio;
	public static volatile CollectionAttribute<TbQuestionario, TbPerguntaQuestionario> tbPerguntaCollection;
	public static volatile SingularAttribute<TbQuestionario, String> stQuestionario;
	public static volatile CollectionAttribute<TbQuestionario, TbManifestacao> manifestacoes;
	public static volatile CollectionAttribute<TbQuestionario, TbPerguntaQuestionario> perguntas;
	public static volatile SingularAttribute<TbQuestionario, Date> dtFinal;
	public static volatile SingularAttribute<TbQuestionario, String> nmQuestionario;
	public static volatile SingularAttribute<TbQuestionario, Integer> idQuestionario;

}

