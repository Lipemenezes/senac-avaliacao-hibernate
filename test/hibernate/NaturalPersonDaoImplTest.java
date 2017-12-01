/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import dao.HibernateUtil;
import dao.NaturalPersonDaoImpl;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Generator;
import entity.NaturalPerson;
import dao.NaturalPersonDao;
import entity.Address;

/**
 *
 * @author Aluno
 */
public class NaturalPersonDaoImplTest {
    private NaturalPerson naturalPerson;
    private NaturalPersonDao naturalPersonDao;
    private Session session;
    private Address address= new Address(null, "endereco_natural", "bairro_natural", 
            "cidade_natural", "estado_natural");

    public NaturalPersonDaoImplTest() {
        naturalPersonDao = new NaturalPersonDaoImpl();
    }

    @Test
    public void testSave() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        naturalPerson
                = new NaturalPerson(null, (Generator.randomString() + "_save"), 
                Generator.randomString(), Generator.randomString(), 
                        Generator.randomString(), Generator.randomString(), 
                        address);
        
        naturalPersonDao.saveOrUpdate(naturalPerson, session);
        session.close();

        assertNotNull(naturalPerson.getId());
    }

//    @Test
    public void testUpdate() {
        createNaturalPersonIfNotExists();
        session = HibernateUtil.openSession();
        naturalPerson.setName(Generator.randomString() + "_update");
        naturalPersonDao.saveOrUpdate(naturalPerson, session);
        
        NaturalPerson updatedNaturalPerson = naturalPersonDao.
                searchById(naturalPerson.getId(), session);
        
        assertEquals(naturalPerson.getName(), updatedNaturalPerson.getName());
        
    }

//    @Test
    public void testListAll() {
        createNaturalPersonIfNotExists();
        session = HibernateUtil.openSession();
        List<NaturalPerson> list = naturalPersonDao.listAll(session);
        
        assertFalse(list.isEmpty());
    }

    public NaturalPerson createNaturalPersonIfNotExists() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from NaturalPerson");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testSave();
        } else {
            session = HibernateUtil.openSession();
            naturalPerson = 
                    naturalPersonDao.searchById(id, session);
            session.close();
        }
        
        return naturalPerson;
    }
}
