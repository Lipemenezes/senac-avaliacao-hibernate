/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import dao.HibernateUtil;
import dao.LegalPersonDaoImpl;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Generator;
import entity.LegalPerson;
import dao.LegalPersonDao;
import entity.Address;

/**
 *
 * @author Aluno
 */
public class LegalPersonDaoImplTest {
    private LegalPerson legalPerson;
    private LegalPersonDao legalPersonDao;
    private Session session;
    private Address address = new Address(null, "endereco_natural", "bairro_natural", 
            "cidade_natural", "estado_natural");

    public LegalPersonDaoImplTest() {
        legalPersonDao = new LegalPersonDaoImpl();
    }

    @Test
    public void testSave() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        legalPerson
                = new LegalPerson(null, (Generator.randomString() + "_save"), 
                Generator.randomString(), Generator.randomString(), 
                        Generator.randomString(), Generator.randomString(),
                        address
                        );
        
        legalPersonDao.saveOrUpdate(legalPerson, session);
        session.close();

        assertNotNull(legalPerson.getId());
    }

    @Test
    public void testUpdate() {
        createLegalPersonIfNotExists();
        session = HibernateUtil.openSession();
        legalPerson.setName(Generator.randomString() + "_update");
        legalPersonDao.saveOrUpdate(legalPerson, session);
        
        LegalPerson updatedLegalPerson = legalPersonDao.
                searchById(legalPerson.getId(), session);
        
        assertEquals(legalPerson.getName(), updatedLegalPerson.getName());
        
    }

    @Test
    public void testListAll() {
        createLegalPersonIfNotExists();
        session = HibernateUtil.openSession();
        List<LegalPerson> list = legalPersonDao.listAll(session);
        
        assertFalse(list.isEmpty());
    }

    private void createLegalPersonIfNotExists() {
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
