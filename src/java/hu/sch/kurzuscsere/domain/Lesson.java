package hu.sch.kurzuscsere.domain;

import java.io.Serializable;

/**
 *
 * @author Kresshy
 */
public class Lesson implements Serializable{

    private Long id;
    private String name;
    private String classCode;

    public Lesson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public void setName(String name) {
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
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return getName() + getClassCode();
    }
}
