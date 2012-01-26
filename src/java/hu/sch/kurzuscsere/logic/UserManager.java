package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.User;

/**
 *
 * @author balo
 */
public class UserManager {

    private UserManager() {
    }

    public synchronized static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    private static class UserManagerHolder {

        private static final UserManager INSTANCE = new UserManager();
    }

    public Long getUserId(String login) {
        return 0L;
    }

    public void updateUserAttributes(User userAttrs) {
        //
    }
}
