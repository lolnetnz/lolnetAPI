/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi;

/**
 *
 * @author James
 */
public class APIKeyNotSetException extends Exception{
    public String message;

    public APIKeyNotSetException(String message){
        this.message = message;
    }

    // Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return message;
    }
}
