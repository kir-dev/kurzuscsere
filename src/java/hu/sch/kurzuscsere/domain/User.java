package hu.sch.kurzuscsere.domain;

import java.io.Serializable;

/**
 *
 * @author Kresshy
 */
public class User implements Serializable {

    private Long id = 0L;
    private String nick;
    private String name;
    private String email;

    public User() {
        nick = "";
        name = "";
        email = "";
    }

    public User(Long id, String nick, String name, String email) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.email = email;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("id=");
        sb.append(id).append("|nick=").append(nick).append("|");
        sb.append("name=").append(name).append("|");
        sb.append("email=").append(email);
        return sb.toString();
    }
}
