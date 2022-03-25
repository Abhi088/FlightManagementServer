package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.checkerframework.common.aliasing.qual.Unique;

@Entity(name = "user_data")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String email;
    
    private String username;
    
    private String pass_word;
    
    private String firstname;
    
    private String middlename;
    
    private String lastname;
    
    private Date dob;
    
    private String gender;
    
    private boolean isAdmin;
    
    public User() {}
    
    public User(String email, String username, String pass_word, String firstname, String middlename, String lastname, Date dob, String gender) {
        this.email = email;
    	this.username = username;
        this.pass_word = pass_word;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
    }
    
    public boolean getIsAdmin() {
    	return this.isAdmin;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public String getPass_word() {
    	return this.pass_word;
    }
    
    public String getFirstname() {
    	return this.firstname;
    }
    
    public String getMiddlename() {
    	return this.middlename;
    }
    
    public String getLastname() {
    	return this.lastname;
    }
    
    public Date getDob() {
    	return this.dob;
    }
    
    public String getGender() {
    	return this.gender;
    }
 
}