package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.User;
import hu.sch.kurzuscsere.logic.db.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author balo
 */
public class UserManager {

    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    private UserManager() {
    }

    public synchronized static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    private static class UserManagerHolder {

        private static final UserManager INSTANCE = new UserManager();
    }

    /**
     * Megkeresi a login név alapján a lokális felhasználót
     *
     * @param nick login név (remote user)
     * @return
     */
    public Long getUserId(final String nick) {
        Long uid = 0L;

        Connection conn = DbHelper.getConnection();
        if (conn == null) {
            return uid;
        }

        String sql = "SELECT id FROM users WHERE usr_nick = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nick);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                uid = res.getLong("id");
            }
            stmt.close();
            //
            conn.close();
        } catch (SQLException ex) {
            log.error("Can't get user id from login name: " + nick, ex);
        }

        return uid;
    }

    /**
     * Frissíti a felhasználó attribútumait. Ha a lokális felhasználó még nem
     * létezik, akkor létrehozza.
     *
     * @param userAttrs
     */
    public void updateUserAttributes(final User userAttrs) {
        //
        Connection conn = DbHelper.getConnection();
        if (conn == null) {
            return;
        }

        if (getUserId(userAttrs.getNick()).equals(0L)) {
            //nincs még mentve -> insert
            insertUser(conn, userAttrs);
        } else {

            String sql = "UPDATE users SET usr_email = ?,"
                    + "usr_name = ? "
                    + "WHERE usr_nick = ?";

            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, userAttrs.getEmail());
                stmt.setString(2, userAttrs.getName());
                stmt.setString(3, userAttrs.getNick());
                stmt.executeUpdate();
                stmt.close();
                //
                conn.close();
                //meglevo felhasznalo id betoltese
                userAttrs.setId(getUserId(userAttrs.getNick()));
            } catch (SQLException ex) {
                log.error("Can't update user: " + userAttrs, ex);
            }
        }

    }

    /**
     * Beszúrja az átadott felhasználót az adatbázisba
     *
     * @param conn
     * @param u
     */
    private void insertUser(final Connection conn, final User u) {

        String sql = "INSERT INTO users(usr_nick, usr_name, usr_email) VALUES(?, ?, ?);";
        try {
            if (u.getNick() == null || u.getNick().equals("") || u.getEmail() == null
                    || u.getEmail().equals("") || u.getName() == null
                    || u.getName().equals("")) {

                throw new Exception();
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getNick());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getEmail());
            stmt.executeUpdate();
            stmt.close();
            //
            conn.close();
        } catch (Exception ex) {
            log.error("Can't insert user: " + u, ex);
        }
    }
}
