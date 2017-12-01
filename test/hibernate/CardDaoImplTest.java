/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import dao.CardDao;
import dao.CardDaoImpl;
import dao.HibernateUtil;
import java.util.List;
import entity.Card;
import entity.Address;
import entity.NaturalPerson;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Generator;

/**
 *
 * @author helton
 */
public class CardDaoImplTest {
    
    private Card card;
    private CardDao cardDao;
    private NaturalPerson naturalPerson;
    private NaturalPersonDaoImplTest naturalPersonDaoImpl;
    
    public CardDaoImplTest() {
        cardDao = new CardDaoImpl();
    }
   
    private Session session;
     
   @Test
    public void testSave() {
        session = HibernateUtil.openSession();
        naturalPersonDaoImpl = new NaturalPersonDaoImplTest();
        naturalPerson = naturalPersonDaoImpl.createNaturalPersonIfNotExists();
        card = new Card();
        card.setExpirationYear(Generator.randomString());
        card.setFlag(Generator.randomString()+ "_save");
        card.setNumber(Generator.randomString());
        card.setNaturalPerson(naturalPerson);
        
        cardDao.saveOrUpdate(card, session);
        session.close();
        assertNotNull(card.getId());
    }

    @Test
    public void testeAlterar() {
       getCard();
        session = HibernateUtil.openSession();
        card.setFlag(Generator.randomString() + "_update");
        cardDao.saveOrUpdate(card, session);
        
        Card updatedCard = cardDao.
                searchById(card.getId(), session);
        
        assertEquals(card.getFlag(), updatedCard.getFlag());
    }

    @Test
    public void testeExcluir() {
        getCard();
        session = HibernateUtil.openSession();
        cardDao.delete(card, session);

        Card cardToDelete = cardDao.
                searchById(card.getId(), session);
        session.close();
        assertNull(cardToDelete);
    }

    
    @Test
    public void testListarTodos() {
        getCard();
        session = HibernateUtil.openSession();
        CardDaoImpl cardDaoImpl = new CardDaoImpl();
        List<Card> list;
        list = cardDaoImpl.listAll(session);
        
        assertFalse(list.isEmpty());
    }
    public Card getCard() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from Card");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testSave();
        } else {
            session = HibernateUtil.openSession();
            card
                    = cardDao.searchById(id, session);
            session.close();
        }
        return card;
    }
    
}
