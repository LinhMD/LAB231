/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;

/**
 *
 * @author USER
 */
public class User implements Serializable{
    private String email;
    private String name;
    private boolean admin;

    public User(String email, String name, boolean isAdmin) {
        this.email = email;
        this.name = name;
        this.admin = isAdmin;
    }
    
    public User(List<String> userData){
        this(userData.get(0),
                userData.get(1),
                userData.get(2).equals("1"));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return email;
    }
}
