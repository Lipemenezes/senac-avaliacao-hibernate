package hibernate;

import dao.HibernateUtil;
import entity.Profile;
import dao.ProfileDaoImpl;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import dao.ProfileDao;
import util.Generator;

/**
 *
 * @author Aluno
 */
public class ProfileDaoImplTest {

    private Profile profile;
    private ProfileDao profileDao;
    private Session session;

    public ProfileDaoImplTest() {
        profileDao = new ProfileDaoImpl();
    }

    @Test
    public void testSave() {
        session = HibernateUtil.openSession();
        Date date = new Date();
        profile
                = new Profile(null, Generator.randomString() + "_save", 
                        date, "obs");
        profileDao.saveOrUpdate(profile, session);
        session.close();

        assertNotNull(profile.getId());
    }

    @Test
    public void testUpdate() {
        createPerfilIfNotExists();
        session = HibernateUtil.openSession();
        profile.setName(Generator.randomString() + "_update");
        profileDao.saveOrUpdate(profile, session);
        
        Profile updatedPerfil = profileDao.
                searchById(profile.getId(), session);
        
        assertEquals(profile.getName(), updatedPerfil.getName());
        
    }

    @Test
    public void testListAll() {
        createPerfilIfNotExists();
        session = HibernateUtil.openSession();
        ProfileDaoImpl profileDaoImpl = new ProfileDaoImpl();
        List<Profile> list = profileDaoImpl.listAll(session);
        
        assertFalse(list.isEmpty());
    }

    private void createPerfilIfNotExists() {
        session = HibernateUtil.openSession();
        Query consulta
                = session.createQuery("select max(id) from Profile");
        Long id = (Long) consulta.uniqueResult();
        session.close();
        if (id == null) {
            testSave();
        } else {
            session = HibernateUtil.openSession();
            profile = 
                    profileDao.searchById(id, session);
            session.close();
        }

    }

}
