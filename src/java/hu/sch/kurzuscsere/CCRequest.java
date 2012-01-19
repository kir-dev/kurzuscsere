/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kresshy
 */
public class CCRequest {

    private int id;
    private User usr;
    private String from;
    private List<String> to;
    
    public CCRequest() {
        to = new ArrayList<String>();       
        usr = new User();
        from = "";
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
     * @return the usr
     */
    public User getUsr() {
        return usr;
    }

    /**
     * @param usr the usr to set
     */
    public void setUsr(User usr) {
        this.usr = usr;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    public void addTo(String course) {
        to.add(course);
    }
    
    public String getTo(String course) {
        int idx = to.indexOf(course);
        if (idx != -1) return to.get(idx);
        else return "Not found";
    }
        
}
