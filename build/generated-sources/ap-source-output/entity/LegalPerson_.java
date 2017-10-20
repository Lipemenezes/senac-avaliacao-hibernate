package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LegalPerson.class)
public abstract class LegalPerson_ extends entity.Person_ {

	public static volatile SingularAttribute<LegalPerson, String> stateRegistration;
	public static volatile SingularAttribute<LegalPerson, String> cnpj;

}

