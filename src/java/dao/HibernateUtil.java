/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Aluno
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
             Configuration cfg = new Configuration();
             cfg.addAnnotatedClass(Profile.class);
             
             cfg.configure("/dao/hibernate.cfg.xml");
             StandardServiceRegistryBuilder build = 
                 new StandardServiceRegistryBuilder().
                               applySettings(cfg.getProperties());
             sessionFactory = cfg.buildSessionFactory(build.build());
             
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Erro ao criar fábrica coneção" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
