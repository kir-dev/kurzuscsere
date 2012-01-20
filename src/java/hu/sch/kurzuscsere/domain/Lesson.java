/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kresshy
 */
public class Lesson {
    
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
    
    public void addTo(Course course) {
        courses.add(course);
    }
    
    public Course getTo(Course course) {
        int idx = courses.indexOf(course);
        if (idx != -1) return courses.get(idx);
        else return new Course();
    }
    
    
}
