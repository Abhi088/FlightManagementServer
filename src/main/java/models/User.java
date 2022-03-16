package models;

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
    public Long id;
    
    @Unique
    @Column(name = "username")
    public String username;
    
    @Column(name = "pass_word")
    public String password;
    
    @Column(name = "fullname")
    public String fullname;
    
    public boolean isAdmin;
    
    public User() {}
    
    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }
 
}