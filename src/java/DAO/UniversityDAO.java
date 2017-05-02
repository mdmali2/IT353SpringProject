/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Account;
import Model.University;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author IT353S730
 */
@ManagedBean
@RequestScoped
public class UniversityDAO {

    /**
     * Creates a new instance of UniversityDAO
     */
    public UniversityDAO() {
    }
    
    public ArrayList findAllUniversities() {
        String query = "select * from UNIVERSITY";
        ArrayList aProfileCollection = selectAccountsFromDB(query);
        return aProfileCollection;
    }
    
       private ArrayList selectAccountsFromDB(String query) {
        ArrayList aUniversityCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/Project353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String universityName, location, description, linkAddress;
            boolean highlighted;
            University university;
            while (rs.next()) {
                universityName = rs.getString("UniversityName");
                highlighted = rs.getBoolean("Highlighted");
                location = rs.getString("Location");
                description = rs.getString("Description");
                linkAddress = rs.getString("LinkAddress");

                university = new University(universityName, location, description, linkAddress, highlighted);
                aUniversityCollection.add(university);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aUniversityCollection;
    }
       
    
    
}
