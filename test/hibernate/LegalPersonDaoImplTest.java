/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import dao.HibernateUtil;
import dao.LegalPersonDaoImpl;
import entity.Profile;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Generator;
import entity.LegalPerson;
import dao.LegalPersonDao;

/**
 *
 * @author Aluno
 */
public class LegalPersonDaoImplTest {
    private LegalPerson legalPerson;
    private LegalPersonDao legalPersonDao;
    private Session session;

    public LegalPersonDaoImplTest() {
        legalPersonDao = new LegalPersonDaoImpl();
    }

    @Test
    public void testSave() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        legalPerson
                = new Profile(null, Generator.randomString() + "_save", 
                        date, "obs");
        legalPersonDao.saveOrUpdate(profile, session);
        session.close();

        assertNotNull(profile.getId());
    }

    @Test
    public void testUpdate() {
        createPerfilIfNotExists();
        session = HibernateUtil.openSession();
        profile.setName(Generator.randomString() + "_update");
        legalPersonDao.saveOrUpdate(legalPerson, session);
        
        Profile updatedPerfil = profileDao.
                searchById(profile.getId(), session);
        
        assertEquals(profile.getName(), updatedPerfil.getName());
        
    }

    @Test
    public void testListAll() {
        createPerfilIfNotExists();
        session = HibernateUtil.openSession();
        List<LegalPerson> list = legalPersonDao.listAll(session);
        
        assertFalse(list.isEmpty());
    }

    private void createPerfilIfNotExists() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from LegalPerson");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testSave();
        } else {
            session = HibernateUtil.openSession();
            legalPerson = 
                    legalPersonDao.searchById(id, session);
            session.close();
        }

    }
}
