/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SearchDAO;
import Model.Search;
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
public class SearchController implements Serializable {

    private List<Search> searchresults;
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
    public SearchController() {

    }
    public List<Search> getSearchResults() {
        SearchDAO asearch = new SearchDAO();
        this.searchresults= asearch.findByAccountEmail(searchKeyword);
        return searchresults;
    }

    public void setTheSearch(List<Search> searchresults) {
        this.searchresults = searchresults;
    }

}