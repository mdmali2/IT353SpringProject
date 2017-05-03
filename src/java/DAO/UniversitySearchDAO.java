/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Model.UniversitySearch;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author IT353S741
 */
@ManagedBean
@RequestScoped
public class UniversitySearchDAO implements Serializable {

    
    
    /**
     * Creates a new instance of SearchDAO
     */
    public UniversitySearchDAO() {
    }
    //searches for username only as of right now
    public ArrayList findByUniversityName(String universityName) {
        String query = "SELECT * FROM  UNIVERSITY ";
        query += "WHERE UNIVERSITYNAME = '" + universityName + "'";

       ArrayList aProfileCollection = selectAccountsFromDB(query);
        return aProfileCollection;
    }
    
    private ArrayList selectAccountsFromDB(String sqlStr) {
        ArrayList aSearchBeanCollection = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/Project353"; 
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            UniversitySearch asearch = new UniversitySearch();
            while (rs.next()) {
                asearch.setUniversityName(rs.getString("UniversityName"));
                asearch.setLocation(rs.getString("Location"));
                asearch.setDescription(rs.getString("Description"));
                aSearchBeanCollection.add(asearch);
                asearch = new UniversitySearch();
            }    
    rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aSearchBeanCollection;
    }
}
