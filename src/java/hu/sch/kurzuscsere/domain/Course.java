package hu.sch.kurzuscsere.domain;

/**
 *
 * @author Kresshy
 */
public class Course {

    private Long id;
    private String courseCode;

    public Course() {
        id = 0L;
        courseCode = "";
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
