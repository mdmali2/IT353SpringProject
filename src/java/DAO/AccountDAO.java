package DAO;

import Model.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author it353S733
 */
public class AccountDAO {

    public ArrayList findByAccountEmail(String username) {
        String query = "SELECT * FROM Account ";
        query += "WHERE username = '" + username + "'";

        ArrayList aProfileCollection = selectAccountsFromDB(query);
        return aProfileCollection;
    }
    
     public ArrayList searchForStudent(String username) {
        String query = "SELECT * FROM Account ";
        query += "WHERE username = '" + username + "'";

        ArrayList aProfileCollection = selectAccountsFromDB(query);
        return aProfileCollection;
    }

    private ArrayList selectAccountsFromDB(String query) {
        ArrayList aUserCollection = new ArrayList();
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
            String first, last, email, pass;
            Account account;
            while (rs.next()) {
                first = rs.getString("FirstName");
                last = rs.getString("LastName");
                pass = rs.getString("Password");
                email = rs.getString("Username");

                account = new Account(first, last, email, pass);
                aUserCollection.add(account);
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
        return aUserCollection;
    }

    public int login(Account account) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String username = account.getEmail();
        String password = account.getPassword();
        int a = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            Connection connection = DriverManager.getConnection(myDB, "itkstu", "student");
            String sql = "select * from Account";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fn = rs.getString("FIRSTNAME");
                String ln = rs.getString("LASTNAME");
                String e = rs.getString("USERNAME");
                String p = rs.getString("PASSWORD");
                int admin = rs.getInt("ADMIN");
                if (username.equals(e)) {
                    if (!password.equals(p)) {
                        return 0;
                    } else if (admin == 1) {
                        account.setEmail(e);
                        account.setFirstName(fn);
                        account.setLastName(ln);
                        account.setIsAdmin(admin);
                        account.setPassword(password);
                        return 2;
                    } else {
                        account.setEmail(e);
                        account.setFirstName(fn);
                        account.setLastName(ln);
                        account.setIsAdmin(admin);
                        account.setPassword(password);
                        return 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return a;
    }

     public int register(Account account) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return 0;
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            Connection connection;
            connection = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement st = connection.createStatement();
            
            String testString="'','','";
            
            if(account.getScoreType().equals("PSAT/NMSQT")){
                testString = account.getTestScore()+ ",null,null";
            }
            
            if(account.getScoreType().equals("SAT")){
                testString = "null," + account.getTestScore()+ ",null";
            }
            
            if(account.getScoreType().equals("ACT")){
                testString = "null,null," + account.getTestScore()+ "";
            }
            
            String sql = "INSERT INTO Account VALUES ('"
                    + account.getFirstName() + "','" + account.getLastName() + "','"
                    + account.getEmail() + "','" + account.getPassword() + "', "
                    + "0, '" + account.getUniversityOC() + "','" + account.getMajorOC() + "'," + testString + ",null)";
            st.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
        return 1;
    }

    public int changePassword(Account account) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return 0;
        }

        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            Connection connection = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement st = connection.createStatement();
            String sql = "UPDATE ACCOUNT SET password = '"
                    + account.getPassword()
                    + "' WHERE username = '"
                    + account.getEmail() + "'";
            st.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
        return 1;
    }
   
    public int uploadImage(FileUploadEvent event, Account account) {
        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            Connection connection = DriverManager.getConnection(myDB, "itkstu", "student");
            String sql = "insert into Photos (FILENAME, EMAIL, RATING, TOTAL, IMAGECONTENT, SUBMISSIONDATE)";
            String values = "values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql + values);
            ps.setString(1, event.getFile().getFileName());
            ps.setString(2, account.getEmail());
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.setBytes(5, event.getFile().getContents());
            ps.setDate(6, new Date(Calendar.getInstance().getTime().getTime()));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
        return 1;
    }
    
    
    public void updateProfile(Account account)
    {
        try
        {
            String myDB = "jdbc:derby://localhost:1527/Project353";
            Connection connection = DriverManager.getConnection(myDB, "itkstu", "student");
            String sql = "UPDATE ACCOUNT SET "
                    + "FIRSTNAME = '"+ account.getFirstName() + "', "
                    + "LASTNAME = '" + account.getLastName() + "', "
                    + "PASSWORD = '" + account.getPassword() +"' "
                    + "WHERE USERNAME = '" + account.getEmail()+"'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            ps.close();
        } 
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    //public int searchForAccount(Account account)
    ///{
        
       // return 
   // }
    
}
