package org.example.mplayer.exceptions;

public class FreeAccountLimitException extends Exception{
    public FreeAccountLimitException(){
        super("Free account limit reached");
    }
    public FreeAccountLimitException(String message){
        super(message);
    }
}
