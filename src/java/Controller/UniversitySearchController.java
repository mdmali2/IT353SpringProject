/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.UniversitySearchDAO;
import Model.UniversitySearch;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author IT353S741
 */
@ManagedBean
@SessionScoped
public class UniversitySearchController implements Serializable {

    private List<UniversitySearch> searchresults;
    private String searchKeyword;

    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword1) {
        this.searchKeyword = searchKeyword1;
    }
    /**
     * Creates a new instance of SearchController
     */
    public UniversitySearchController() {

    }
    public List<UniversitySearch> getSearchResult() {
        UniversitySearchDAO asearch = new UniversitySearchDAO();
        this.searchresults= asearch.findByUniversityName(searchKeyword);
        return searchresults;
    }

    public void setTheSearch(List<UniversitySearch> searchresults) {
        this.searchresults = searchresults;
    }

}