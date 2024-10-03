package org.example.mplayer.model.user;

import java.util.Date;

public abstract class User {
    private final String username;
    private String password;
    private String name;//type?
    private String emailAddress;//?
    private String phoneNumber;
    private Date birthDate;
    private static long counter =0;
    public User(String password,String username, String name, String emailAddress, String phoneNumber,
            Date birthDate) {
        counter++;
        this.username = username;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;  
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public static long getCounter() {
        return counter;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    @Override
    public String toString(){
        return "User "+username+"("+name+")\n";
    }
    
}
