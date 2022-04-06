package models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_data")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String email;
    
    private String username;
    
    private String pass_word;
    
    private String first_name;
    
    private String middle_name;
    
    private String last_name;
    
    private Date dob;
    
    private String gender;
    
    private Boolean isAdmin;
    
    public User() {}
    
    public User(String email, String username, String pass_word, String first_name, String middle_name, String last_name, Date dob, String gender) {
        this.email = email;
    	this.username = username;
        this.pass_word = pass_word;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.isAdmin = false;
    }
    
    public Boolean getIsAdmin() {
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
    
    public String getFirst_name() {
    	return this.first_name;
    }
    
    public String getMiddle_name() {
    	return this.middle_name;
    }
    
    public String getLast_name() {
    	return this.last_name;
    }
    
    public Date getDob() {
    	return this.dob;
    }
    
    public String getGender() {
    	return this.gender;
    }
 
}