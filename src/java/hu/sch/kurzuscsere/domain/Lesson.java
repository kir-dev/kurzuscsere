package hu.sch.kurzuscsere.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Kresshy
 * @author balo
 */
@Entity
@Table(name = "lessons")
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq")
    private Long id;
    private String name;
    private String classCode;

    public Lesson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the classCode
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * @param classCode the classCode to set
     */
    public void setClassCode(final String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return getName() + ":" + getClassCode();
    }
}
