package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author balo
 */
@Stateless
public class UserManager {

    @PersistenceContext
    private EntityManager em;
    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    /**
     * Megkeresi a login név alapján a lokális felhasználót
     *
     * @param loginName login név (remote user)
     * @return
     */
    public User getUserFromLoginName(final String loginName) {
        User user = null;

        try {
            final TypedQuery<User> query = em.createQuery("SELECT u from User u WHERE nick=:loginName",
                    User.class);

            query.setParameter("loginName", loginName);
            user = query.getSingleResult();
        } catch (Exception ex) {
            log.error("Couldn't find user with the given loginName =" + loginName
                    + "; message=" + ex.getMessage());
        }

        return user;
    }

    /**
     * Megkeresi id alapján a felhasználót az adatbázisban. Ha ilyen id-val
     * nincs felhasználó, akkor
     * <pre>null</pre> értékkel tér vissza
     *
     * @param uid user id
     * @return
     */
    public User find(final Long uid) {
        User user = null;

        if (uid != null) {
            try {
                user = em.find(User.class, uid);
            } catch (IllegalArgumentException ex) {
                log.error("Illegal uid in find=" + uid + "; message=" + ex.getMessage());
            }
        }

        return user;
    }

    /**
     * Frissíti az átadott felhasználót az adatbázisban. Ha a lokális
     * felhasználó még nem létezik, akkor létrehozza.
     *
     * @param user
     */
    public User merge(final User user) {
        User u = null;

        try {
            u = em.merge(user);
        } catch (IllegalArgumentException ex) {
            log.error("Illegal user in merge=" + user + "; message=" + ex.getMessage());
        }

        return u;
    }
}
