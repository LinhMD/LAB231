/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.ultilities;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection() throws ClassNotFoundException, SQLException, NamingException {
//        Context context = new InitialContext();
//        Context tomcatContext = (Context)context.lookup("java:comp/env");
//        DataSource da = (DataSource) tomcatContext.lookup("jdbc/p0014");
//        return da.getConnection();


        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. tao connection String
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Quiz";
        //3. open connection
        return DriverManager.getConnection(url, "sa", "123456");

        
    }


}
