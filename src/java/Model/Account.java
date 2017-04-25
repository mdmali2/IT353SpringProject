package Model;

import java.util.HashMap;
import java.util.Map;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author it353S733
 */
public class Account {

    private String firstName;
    private String lastName;
    private String password;
    private String confirmPass;
    private String email;
    private int isAdmin;
    
    public Account() {
       
    }
    
    public Account(String first, String last, String email, String pass){
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.password = pass;
        this.isAdmin = 0;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmpass) {
        this.confirmPass = confirmpass;
    }
    

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the isAdmin
     */
    public int isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    //possible search 
   private Map<String, String> exampleData = new HashMap<String, String>() {{ 
        put("ISU", "Illinois State Univerity"); 
        put("UoI", "University of Illinois");
    }};

    private String searchUni; 
    private String uni;

    public void updateUni(AjaxBehaviorEvent event) {
        uni = exampleData.get(searchUni);
    }

    public String getSearchString() {
        return searchUni;
    }

    public void setSearchString(String searchString) {
        this.searchUni = searchString;
    }

    public String getUni() {
        return uni;
    }

}
