package org.example.mplayer.exceptions;

public class UserNotFoundException extends FailedLoginException{
    public UserNotFoundException(){
        super("User not found");
    }
    public UserNotFoundException(String txt){
        super(txt);
    }
}
