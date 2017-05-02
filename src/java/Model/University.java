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
 * @author IT353S730
 */
@ManagedBean
@RequestScoped
public class University {

    private String universityName;
    private String location;
    private String description;
    private String linkAddress;
    private boolean highlighted;
    
    public University() {
        
    }
    
    public University(String universityName, String location, String description, String linkAddress, boolean highlighted) {
        this.universityName = universityName;
        this.location = location;
        this.description = description;
        this.linkAddress = linkAddress;
        this.highlighted = highlighted;
    }

    /**
     * @return the universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the linkAddress
     */
    public String getLinkAddress() {
        return linkAddress;
    }

    /**
     * @param linkAddress the linkAddress to set
     */
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    /**
     * @return the highlighted
     */
    public boolean isHighlighted() {
        return highlighted;
    }

    /**
     * @param highlighted the highlighted to set
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
    
}
