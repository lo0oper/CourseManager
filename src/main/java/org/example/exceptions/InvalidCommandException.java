package org.example.exceptions;

public class InvalidCommandException extends  Exception{
    private InvalidCommandException(String message){
        super(message);
    }
}
