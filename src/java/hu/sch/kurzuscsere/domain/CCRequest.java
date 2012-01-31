package hu.sch.kurzuscsere.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kresshy
 */
public class CCRequest implements Serializable {

    private Long id;
    private User usr;
    private Lesson lesson;
    private String fromCourse;
    private List<String> to;

    public enum Status {

        New, Notified, Deleted
    };
    private Status status;

    public CCRequest() {
        to = new LinkedList<String>();
        usr = null;
        fromCourse = "";
        lesson = null;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getFromCourse() {
        return fromCourse;
    }

    public void setFromCourse(String fromCourse) {
        this.fromCourse = fromCourse;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("id=").append(id).append("|");
        stb.append("status=").append(status).append("|");

        if (usr != null) {
            stb.append("user=").append(usr.getNick()).append("|");
        }
        
        stb.append("fromCourse=").append(fromCourse).append("|");
        
        if (lesson != null) {
            stb.append("lesson=").append(lesson.getName()).append("|");
        }
        stb.append("toCourse=");

        for (String toCourse : to) {
            stb.append(toCourse).append(",");
        }

        stb.append("|");

        return stb.toString();
    }
}
