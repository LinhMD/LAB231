/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0014.linhmd.ultilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author USER
 */
public class PropertiesLoader {
    private static final Logger LOGGER = Logger.getLogger(PropertiesLoader.class);
    public static Properties loadProperties(String path){
        Properties properties = new Properties();
        
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            // close objects
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return properties;
    }
}
