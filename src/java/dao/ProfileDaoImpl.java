/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Profile;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class ProfileDaoImpl
        extends BaseDaoImpl<Profile, Long>
        implements ProfileDao {

    @Override
    public Profile searchById(Long id, Session session) throws HibernateException {
        Profile fornecedor = (Profile) 
                session.get(Profile.class, id);
        return fornecedor;
    }

    @Override
    public List<Profile> listAll(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Profile");
        return consulta.list();
    }

}
