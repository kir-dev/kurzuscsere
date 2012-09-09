package hu.sch.kurzuscsere.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kresshy
 * @author balo
 */
@Entity
@Table(name = "ccrequests")
public class CCRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Possible statuses of the request
     */
    public enum Status {

        New, Notified, Deleted
    };
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ccrequest_seq")
    @SequenceGenerator(name = "ccrequest_seq", sequenceName = "ccrequest_seq")
    private Long id;
    /**
     * User, who like to change his course
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    /**
     * Lesson, which has courses
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lesson lesson;
    /**
     * Course, what user want to change
     */
    private String fromCourse;
    /**
     * Courses, what the user want instead of the current course
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ccrequests_to_courses")
    private List<String> toCourses;
    /**
     * The status of the request
     */
    @Enumerated(EnumType.STRING)
    private Status status;
    /**
     * When the request issued
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date issued;

    public CCRequest() {
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
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the usr
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the usr to set
     */
    public void setUser(final User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public List<String> getToCourses() {
        return toCourses;
    }

    public void setToCourses(final List<String> to) {
        this.toCourses = to;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(final Lesson lesson) {
        this.lesson = lesson;
    }

    public String getFromCourse() {
        return fromCourse;
    }

    public void setFromCourse(final String fromCourse) {
        this.fromCourse = fromCourse;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(final Date issued) {
        this.issued = issued;
    }

    @Override
    public String toString() {
        final StringBuilder stb = new StringBuilder();
        stb.append("id=").append(id).append("|");
        stb.append("status=").append(status).append("|");
        if (user != null) {
            stb.append("user=").append(user.getNick()).append(",").append(user.getId()).append("|");
        }
        stb.append("fromCourse=").append(fromCourse).append("|");
        if (lesson != null) {
            stb.append("lesson=").append(lesson.getName()).append(",").append("lesson_id=").append(lesson.getId()).append("|");
        }
        stb.append("toCourse=");
        for (String toCourse : toCourses) {
            stb.append(toCourse).append(",");
        }
        stb.append("|");

        return stb.toString();
    }
}
