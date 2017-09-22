/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.LegalPerson;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author Aluno
 */
public class LegalPersonDaoImpl 
        extends BaseDaoImpl<LegalPerson, Long>
        implements LegalPersonDao {
    
    @Override
    public LegalPerson searchById(Long id, Session session) throws HibernateException {
        LegalPerson legalPerson = (LegalPerson) 
                session.get(LegalPerson.class, id);
        return legalPerson;
    }

    @Override
    public List<LegalPerson> listAll(Session session) throws HibernateException {
        Query consulta = session.createQuery("from LegalPerson");
        return consulta.list();
    }
    
}
