package org.example.mplayer.exceptions;

public class WrongPaswordException extends FailedLoginException{
    public WrongPaswordException(){
        super("Wrong password");
    }
    public WrongPaswordException(String txt){
        super(txt);
    }
}
