package org.example.mplayer.exceptions;

public class FailedLoginException extends Exception{
    public FailedLoginException(){
        super("FailedLoginException");
    }
    public FailedLoginException(String txt){
        super(txt);
    }
}
