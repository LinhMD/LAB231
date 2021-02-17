/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.dao;

import p0014.linhmd.dto.User;
import p0014.linhmd.ultilities.SQLQuery;

import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class UserDAO {
    public User login(String userid, String password) throws SQLException{
        String sql = "SELECT u.email, u.name, u.role \n" +
                        "FROM _user u\n" +
                        "WHERE u.email = ? AND u.password = ? AND u.status = ?";
        Vector<Vector<String>> result = SQLQuery.executeQuery(sql, userid, password, 1);
        if(!result.isEmpty()){
            return new User(result.get(0));
        }
        return null;
    }
    
    public boolean registerNewUser(User user, String password) throws SQLException{
        String sql = "insert into _user(email, name, password, role, status)\n" +
                    "values (?, ?, ?, ?, 1)";
        return SQLQuery.executeNonQuery(sql, user.getEmail(), user.getName(), password, user.isAdmin()? 1: 0);
        
    }
}
