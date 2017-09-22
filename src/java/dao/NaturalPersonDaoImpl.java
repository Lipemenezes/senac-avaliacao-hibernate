/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import entity.NaturalPerson;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Felipe Menezes
 */
public class NaturalPersonDaoImpl 
        extends BaseDaoImpl<NaturalPerson, Long>
        implements NaturalPersonDao {

    @Override
    public NaturalPerson searchById(Long id, Session session) throws HibernateException {
        NaturalPerson naturalPerson = (NaturalPerson) 
                session.get(NaturalPerson.class, id);
        return naturalPerson;
    }

    @Override
    public List<NaturalPerson> listAll(Session session) throws HibernateException {
        Query consulta = session.createQuery("from NaturalPerson");
        return consulta.list();
    }
    
}
