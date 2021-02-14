/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.ultilities;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author USER
 */
public class UserError extends HashMap<String, String> implements Serializable{
    private boolean error = false;
    
    public UserError(){
        super();
    }

    @Override
    public String put(String key, String value) {
        String put = super.put(key, value); //To change body of generated methods, choose Tools | Templates.
        this.error = true;
        return put;
    }

    public boolean isError() {
        return error;
    }
    
}
