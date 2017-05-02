package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private String universityOC;
    private String majorOC;
    private String testScore;
    private String scoreType;
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
        return getIsAdmin();
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
        setUni(getExampleData().get(getSearchUni()));
    }

    public String getSearchString() {
        return getSearchUni();
    }

    public void setSearchString(String searchString) {
        this.setSearchUni(searchString);
    }

    public String getUni() {
        return uni;
    }

    /**
     * @return the gpa
     */
    

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

    /**
     * @return the universityOC
     */
    public String getUniversityOC() {
        return universityOC;
    }

    /**
     * @param universityOC the universityOC to set
     */
    public void setUniversityOC(String universityOC) {
        this.universityOC = universityOC;
    }

    /**
     * @return the majorOC
     */
    public String getMajorOC() {
        return majorOC;
    }

    /**
     * @param majorOC the majorOC to set
     */
    public void setMajorOC(String majorOC) {
        this.majorOC = majorOC;
    }

    /**
     * @return the testScore
     */
    public String getTestScore() {
        return testScore;
    }

    /**
     * @param testScore the testScore to set
     */
    public void setTestScore(String testScore) {
        this.testScore = testScore;
    }

    /**
     * @return the scoreType
     */
    public String getScoreType() {
        return scoreType;
    }

    /**
     * @param scoreType the scoreType to set
     */
    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    /**
     * @return the isAdmin
     */
    public int getIsAdmin() {
        return isAdmin;
    }
public void sendEmail() {
        String to = email;
        // Sender's email ID needs to be mentioned
        String from = "it353projectspring17@gmail.com";
        String host = "smtp.gmail.com";
        

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
       properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("it353projectspring17@gmail.com", "spring2017");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Appointment Requested");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>You have signed up for the webapp!</h1>",
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    
    }
}
