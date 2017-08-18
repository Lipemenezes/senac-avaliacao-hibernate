/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID> {
    
    void saveOrUpdate(T entidade, Session session) throws HibernateException;
    
    void delete(T entidade, Session session)throws HibernateException;

    T searchById(ID id, Session session)throws HibernateException;
    
    List<T> listAll(Session session)throws HibernateException;
}
