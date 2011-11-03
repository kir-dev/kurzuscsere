/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import java.util.Date;

/**
 *
 * @author Kresshy
 */
public class Lesson {
    
    private String name;
    private String courseFrom;
    private String courseTo;
    private Date timeFrom;
    private Date timeTo;
    
    private static final String LIMITER = "|";
    
    public Lesson() {
    
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
     * @return the courseFrom
     */
    public String getCourseFrom() {
        return courseFrom;
    }

    /**
     * @param courseFrom the courseFrom to set
     */
    public void setCourseFrom(String courseFrom) {
        this.courseFrom = courseFrom;
    }

    /**
     * @return the courseTo
     */
    public String getCourseTo() {
        return courseTo;
    }

    /**
     * @param courseTo the courseTo to set
     */
    public void setCourseTo(String courseTo) {
        this.courseTo = courseTo;
    }

    /**
     * @return the timeFrom
     */
    public Date getTimeFrom() {
        return timeFrom;
    }

    /**
     * @param timeFrom the timeFrom to set
     */
    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    /**
     * @return the timeTo
     */
    public Date getTimeTo() {
        return timeTo;
    }

    /**
     * @param timeTo the timeTo to set
     */
    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return name+LIMITER+courseFrom+LIMITER+courseTo+LIMITER+timeFrom.toString()+LIMITER+timeTo.toString();
    }
    
    
    
    
}
