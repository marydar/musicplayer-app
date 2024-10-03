package org.example.mplayer.model.user;

import java.util.Date;

public class Admin extends User{

    public Admin(String password,String username, String name, String emailAddress, String phoneNumber,
            Date birthDate) {
        super(password,username, name, emailAddress, phoneNumber, birthDate);
        
    }

    private static Admin admin;
    public static Admin getAdmin(String password,String username, String name, String emailAddress, String phoneNumber,
         Date birthDate){
        if(admin==null){
            admin = new Admin(password,username, name, emailAddress, phoneNumber, birthDate);
            return admin;
        }
        else{
            return admin;
        }

    }
    public static Admin getAdmin(){
        return admin;
    }


    
    
}
