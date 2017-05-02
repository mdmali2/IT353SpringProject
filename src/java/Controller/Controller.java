/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Model.Account;
import DAO.AccountDAO;
import Model.Applicant;
import java.util.Date;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author it353S733
 */
@ManagedBean(eager=false)
@SessionScoped
public class Controller {

    private Account account;
    private Applicant applicant;
    private String applicantResponse;
    private boolean send;

    public Controller() {
        account = new Account();
        applicant = new Applicant();
    }
    
    
    //This method calls the method to sign you in
    public String signIn() {
        AccountDAO ad = new AccountDAO();
        if(account.getEmail().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Login", "Invalid Username/Password. Please try again.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index.xhtml";
        }
        if(account.getPassword().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Login", "Invalid Username/Password. Please try again.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index.xhtml";
        }
        int x = ad.login(account);
        if (x == 1) {
            return "dashboard.xhtml";
        } else if (x == 2) {
            return "a_dashboard.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Login", "Invalid Username/Password. Please try again.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "login.xhtml";
        }
    }

    //This method calls the method to register you into the DB
    public String authenticate() {
        //Making sure all values have been inputed
        if (account.getFirstName().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a your firstname.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        if (account.getLastName().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a your lastname.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        if (account.getEmail().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a email.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        if (account.getEmail().charAt(0)=='.' ||
                account.getEmail().charAt(account.getEmail().length()-1)=='.' ||
                account.getEmail().charAt(0)=='@' ||
                account.getEmail().charAt(account.getEmail().length()-1)=='@' ||
                !account.getEmail().contains("@") ||
                !account.getEmail().contains(".")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a valid email.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        if (account.getPassword().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a password.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        if (account.getConfirmPass().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please confirm your password.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        //Making sure password and confirm password are the same
        if (!account.getPassword().equals(account.getConfirmPass())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Passwords don't match.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "register.xhtml";
        }
        //Register
        AccountDAO ad = new AccountDAO();
        if (ad.register(account) == 1) {
            return "index.xhtml";
        } else {
            return "register.xhtml";
        }
    }
    
      public void upload(FileUploadEvent event) {
        AccountDAO ad = new AccountDAO();
        int x = ad.uploadImage(event);
    }
      public String apply()
      {
          AccountDAO ad = new AccountDAO();
          if (ad.apply(applicant) == 1) {
            return "success.xhtml";
        } else {
            return "apply.xhtml";
        }
         
      }

    /**
     * checks if username is already DB and notifies using AJAX
     */
    public String checkValidEmail() {
        AccountDAO accDao = new AccountDAO();
        ArrayList accCollection = accDao.findByAccountEmail(account.getEmail());
        if (!accCollection.isEmpty()) {
            return "<span style=\"color:red\">Email already used!</span>";
        } else {
            return "";
        }
    }
    
   public ArrayList<Account> getStudents() {
        return AccountDAO.getStudents();
    }
   
    public String checkAccountInfo() {
        AccountDAO accDao = new AccountDAO();
        ArrayList accCollection = accDao.findByAccountEmail(account.getEmail());
        if (accCollection.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Could not verify your account "
                    + "with the information provided. Please try again.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "forgotpassword.xhtml";
        } else {
            Account a = (Account) accCollection.get(0);
            if (account.getFirstName().toUpperCase().equals(a.getFirstName().toUpperCase())
                    && account.getLastName().toUpperCase().equals(a.getLastName().toUpperCase())
                    && account.getEmail().toUpperCase().equals(a.getEmail().toUpperCase())) {
                return "changepassword.xhtml";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Could not verify your account "
                        + "with the information provided. Please try again.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "forgotpassword.xhtml";
            }
        }
    }

    public String changePassword() {
        if(account.getPassword().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter your new password.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "changepassword.xhtml";
        }
        if(account.getConfirmPass().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please confirm your password");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "changepassword.xhtml";
        }
        if(!account.getPassword().equals(account.getConfirmPass())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Passwords don't match.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "changepassword.xhtml";
        }
        else{
            AccountDAO accDao = new AccountDAO();
            accDao.changePassword(account);
        }
        return "index.xhtml";
        
    }
    public String getResponse() {
        String resultStr = "";
        resultStr += "Hello " + applicant.getFirstName() + "<br/>";
        resultStr += " you have applied to: " + applicant.getUniversityOC() + "<br/>";
        resultStr += " Best of luck with you application. We will be in touch soon! ";
        applicantResponse = resultStr;
        return applicantResponse;
            }

    /*
    * sends email
     */
    public void sendEmail(String recipient, String sender, String subject, String content) {
        String to = recipient;
        String from = sender;
        String host = "smtp.gmail.com";
        String username = "it353project@gmail.com";
        String password = "nickandcharlie";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            message.setText(content);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    
    public String logout()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        AccountDAO ad = new AccountDAO();
        return "index.xhtml";
    }
    
    public String updateAccount()
    {
        boolean update = true;
        //Making sure all values have been inputed
        if (account.getFirstName().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a your firstname.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            update = false;
        }
        if (account.getLastName().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a your lastname.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            update = false;
        }
        if (account.getPassword().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please enter a password.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            update = false;
        }
        if (account.getConfirmPass().equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Please confirm your password.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            update = false;
        }
        //Making sure password and confirm password are the same
        if (!account.getPassword().equals(account.getConfirmPass())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Inputs!", "Passwords don't match.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            update = false;
        }
       AccountDAO ad = new AccountDAO();
       if(update) ad.updateProfile(account);
       
       return "dashboard.xhtml";
    }
    
    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }
    /**
     * @return the applicant
     */
    public Applicant getApplicant() {
        return applicant;
    }

    /**
     * @param applicant the to set applicant
     */
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    /**
     * @return the response
     */
    public String getApplicantResponse() {
        return applicantResponse;
    }

    /**
     * @param applicantResponse the response to set
     */
    public void setApplicantResponse(String applicantResponse) {
        this.applicantResponse = applicantResponse;
    }
    
    
       	 public String sendEmail2() {
       
   send=true;
       if (send=true){
           account.sendEmail();

       
}
    return "a_dashboard.xhtml";   
}

      
      
    
}
