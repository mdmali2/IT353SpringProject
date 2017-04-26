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
    
    //applytoschool strings
    private String gpa;
    private String activites;
    private String desireduni;
    private String highscool;
    private String sat;
    private String act;
    
    
    public Account() {
       
    }
    
    public Account(String first, String last, String email, String pass){
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.password = pass;
        this.isAdmin = 0;
        this.act=act;
        this.sat=sat;
        this.highscool=highscool;
        this.desireduni=desireduni;
        this.activites=activites;
        this.gpa=gpa;
                
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
     * @return the activites
     */
    public String getActivites() {
        return activites;
    }

    /**
     * @param activites the activites to set
     */
    public void setActivites(String activites) {
        this.activites = activites;
    }

    /**
     * @return the desireduni
     */
    public String getDesireduni() {
        return desireduni;
    }

    /**
     * @param desireduni the desireduni to set
     */
    public void setDesireduni(String desireduni) {
        this.desireduni = desireduni;
    }

    /**
     * @return the highscool
     */
    public String getHighscool() {
        return highscool;
    }

    /**
     * @param highscool the highscool to set
     */
    public void setHighscool(String highscool) {
        this.highscool = highscool;
    }

    /**
     * @return the sat
     */
    public String getSat() {
        return sat;
    }

    /**
     * @param sat the sat to set
     */
    public void setSat(String sat) {
        this.sat = sat;
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
     * @return the exampleData
     */
    public Map<String, String> getExampleData() {
        return exampleData;
    }

    /**
     * @param exampleData the exampleData to set
     */
    public void setExampleData(Map<String, String> exampleData) {
        this.exampleData = exampleData;
    }

    /**
     * @return the searchUni
     */
    public String getSearchUni() {
        return searchUni;
    }

    /**
     * @param searchUni the searchUni to set
     */
    public void setSearchUni(String searchUni) {
        this.searchUni = searchUni;
    }

    /**
     * @param uni the uni to set
     */
    public void setUni(String uni) {
        this.uni = uni;
    }

}
