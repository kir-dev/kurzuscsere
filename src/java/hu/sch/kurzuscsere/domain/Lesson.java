package hu.sch.kurzuscsere.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kresshy
 */
public class Lesson {

    private int id;
    private String name;
    private String classCode;
    private List<Course> courses;

    public Lesson() {
        courses = new ArrayList<Course>();
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
}
