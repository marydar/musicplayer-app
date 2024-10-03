package org.example.mplayer.exceptions;

public class NotEnoughCreditException extends Exception{
    public NotEnoughCreditException(){
        super("Not enough credit");
    }
    public NotEnoughCreditException(String message){
        super(message);
    }
}
