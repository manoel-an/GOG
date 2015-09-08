package br.com.xti.ouvidoria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TbRespostaQuestionario.class)
public abstract class TbRespostaQuestionario_ {

	public static volatile SingularAttribute<TbRespostaQuestionario, Integer> idResposta;
	public static volatile SingularAttribute<TbRespostaQuestionario, TbPerguntaQuestionario> pergunta;
	public static volatile SingularAttribute<TbRespostaQuestionario, TbManifestacao> manifestacao;
	public static volatile SingularAttribute<TbRespostaQuestionario, Integer> dsResposta;

}

