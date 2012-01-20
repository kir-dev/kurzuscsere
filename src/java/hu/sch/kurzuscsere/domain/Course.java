/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.domain;

/**
 *
 * @author Kresshy
 */
public class Course {

    private int id;
    private String courseCode;
    
    public Course() {
        courseCode = "";        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    
    
    
}
