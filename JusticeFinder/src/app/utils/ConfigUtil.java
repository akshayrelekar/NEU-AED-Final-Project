/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class ConfigUtil {
    
    private static final String CONFIG_PROP_FILE = "src/app/utils/config.properties";
    
    private static Properties loadProperties(){
        try {
            InputStream in = new FileInputStream(CONFIG_PROP_FILE);
            Properties prop = new Properties();
            prop.load(in);
            return prop;
        } 
        catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String getProp(String property){
        Properties prop = loadProperties();
        return prop.getProperty(property);
    }
}
