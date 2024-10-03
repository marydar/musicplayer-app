package org.example.mplayer.exceptions;

public class InvalidFormatException extends Exception{
    public InvalidFormatException(){
        super("InvalidFormatException");
    }
    public InvalidFormatException(String txt){
        super(txt);
    }
}
