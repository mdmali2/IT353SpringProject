/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UniversityDAO;
import Model.University;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author IT353S730
 */
@ManagedBean
@RequestScoped
public class UniversityController {

    /**
     * Creates a new instance of UniversityBean
     */
    
    private University university;
    private List<University> universities;
    private String hilightedUniversity;
    private String hilightedLink;
    
    public UniversityController() {
        UniversityDAO dao = new UniversityDAO();
        List<University> list = dao.findAllUniversities();
        this.universities = list;
    }
    public void changeHighlighted(){
        UniversityDAO dao = new UniversityDAO();
        dao.changeHighlightedUni(getHilightedUniversity());
        List<University> list = dao.findAllUniversities();
        this.setUniversities(list);
        
    }
    
    public String displayHighlighted(){
        for(int i = 0; i < getUniversities().size(); i++){
            if(getUniversities().get(i).isHighlighted()){
                setUniversity(getUniversities().get(i));
            }
        }
        
        setHilightedLink(getUniversity().getLinkAddress());
        
        String highlighted = "The highlighted University is: " + getUniversity().getUniversityName() + "!!<br/><br/>They are located in: " +
                getUniversity().getLocation() + "<br/><br/>Check out their website!! - ";
                
        return highlighted;
    }

    /**
     * @return the university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(University university) {
        this.university = university;
    }

    /**
     * @return the universities
     */
    public List<University> getUniversities() {
        return universities;
    }

    /**
     * @param universities the universities to set
     */
    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    /**
     * @return the universityNames
     */
    public List<String> getUniversityNames() {
        List<String> universityNames = new ArrayList<String>();
        for(int i =0; i < getUniversities().size(); i++){
            String name = getUniversities().get(i).getUniversityName();
            universityNames.add(name);
        }
       
        return universityNames;
    }

    /**
     * @return the hilightedUniversity
     */
    public String getHilightedUniversity() {
        return hilightedUniversity;
    }

    /**
     * @param hilightedUniversity the hilightedUniversity to set
     */
    public void setHilightedUniversity(String hilightedUniversity) {
        this.hilightedUniversity = hilightedUniversity;
    }

    /**
     * @return the hilightedLink
     */
    public String getHilightedLink() {
        return hilightedLink;
    }

    /**
     * @param hilightedLink the hilightedLink to set
     */
    public void setHilightedLink(String hilightedLink) {
        this.hilightedLink = hilightedLink;
    }

  
    
}
