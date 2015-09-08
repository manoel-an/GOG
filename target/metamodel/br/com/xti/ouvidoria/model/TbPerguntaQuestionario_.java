package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbPerguntaQuestionario.class)
public abstract class TbPerguntaQuestionario_ {

	public static volatile SingularAttribute<TbPerguntaQuestionario, Integer> idPergunta;
	public static volatile SingularAttribute<TbPerguntaQuestionario, String> dsPergunta;
	public static volatile SingularAttribute<TbPerguntaQuestionario, Integer> posicaoPergunta;
	public static volatile SingularAttribute<TbPerguntaQuestionario, TbQuestionario> questionario;

}

