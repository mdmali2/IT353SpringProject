/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UniversityDAO;
import Model.University;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
    
    public UniversityController() {
        UniversityDAO dao = new UniversityDAO();
        List<University> list = dao.findAllUniversities();
        this.universities = list;
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
        for(int i =0; i < universities.size(); i++){
            String name = universities.get(i).getUniversityName();
            universityNames.add(name);
        }
       
        return universityNames;
    }

  
    
}
