/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author IT353S741
 */
@ManagedBean
@RequestScoped
public class Search {
//possible search options
 private  String firstName;
    private String Firstname;
    private String desiredschool;
    private String gpa;
    private String act;
    private String username;
    
   

    public Search(){
        
    }

    /**
     * @return the Firstname
     */
    public String getFirstname() {
        return Firstname;
    }

    /**
     * @param Firstname the Firstname to set
     */
    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    /**
     * @return the desiredschool
     */
    public String getDesiredschool() {
        return desiredschool;
    }

    /**
     * @param desiredschool the desiredschool to set
     */
    public void setDesiredschool(String desiredschool) {
        this.desiredschool = desiredschool;
    }

    /**
     * @return the gpa
     */
    public String getGpa() {
        return gpa;
    }

    /**
     * @param gpa the gpa to set
     */
    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    /**
     * @return the act
     */
    public String getAct() {
        return act;
    }

    /**
     * @param act the act to set
     */
    public void setAct(String act) {
        this.act = act;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
