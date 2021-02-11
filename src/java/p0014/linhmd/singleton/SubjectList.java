/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.singleton;


import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import p0014.linhmd.dao.SubjectDAO;
import p0014.linhmd.dto.Subject;

/**
 *
 * @author USER
 */
public class SubjectList {
    private static Logger LOGGER;
    
    private static List<Subject> SUBJECTS;
    
    static{
        LOGGER = Logger.getLogger(SubjectList.class);
        try {
            SUBJECTS = new SubjectDAO().getAllSubject();
        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        }
    }
    
    public static List<Subject> getInstance(){
        return SUBJECTS;
    }
}
