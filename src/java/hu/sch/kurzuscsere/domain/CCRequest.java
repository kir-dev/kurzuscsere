package hu.sch.kurzuscsere.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kresshy
 */
public class CCRequest {

    private Long id;
    private User usr;
    private Course from;
    private List<Course> to;

    public enum Status {

        New, Notified, Deleted
    };
    private Status status;

    public CCRequest() {
        to = new ArrayList<Course>();
        usr = new User();
        from = null;
        status = Status.New;
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
     * @return the usr
     */
    public User getUser() {
        return usr;
    }

    /**
     * @param usr the usr to set
     */
    public void setUser(User usr) {
        this.usr = usr;
    }

    /**
     * @return the from
     */
    public Course getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Course from) {
        this.from = from;
    }
}
