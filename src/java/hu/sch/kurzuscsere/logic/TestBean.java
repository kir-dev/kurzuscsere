package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author balo
 */
@Stateless
public class TestBean {

    @PersistenceContext
    private EntityManager em;

    public void save() {
        final User u = new User();
        u.setEmail("teszt@test.cc");
        u.setNick("teszt");
        u.setName("Teszt Tesztecske");
        em.merge(u);
        em.flush();
        em.clear();
    }
}
