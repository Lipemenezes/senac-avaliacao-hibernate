/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import entity.Card;
import entity.NaturalPerson;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author Felipe Menezes
 */
public class CardDaoImpl 
        extends BaseDaoImpl<Card, Long>
        implements CardDao {
    
    @Override
    public Card searchById(Long id, Session session) throws HibernateException {
        Card card = (Card) 
                session.get(Card.class, id);
        return card;
    }

    @Override
    public List<Card> listAll(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Card");
        return consulta.list();
    }
}
