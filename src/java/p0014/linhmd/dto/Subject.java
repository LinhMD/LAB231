/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Subject implements Serializable{
    private String code;
    private String name;
    private int time;
    private int limit;

    public Subject(String code, String name, int time, int limit) {
        this.code = code;
        this.name = name;
        this.time = time;
        this.limit = limit;
    }
    
    public Subject(List<String> data){
        this(data.get(0), data.get(1), Integer.parseInt(data.get(2)), Integer.parseInt(data.get(3)));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.code);
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
        final Subject other = (Subject) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return this.code;
    }
    
    
    
}
