/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils.email;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class EmailTemplateFormatter {

    public static String getMessage(String templateName, String... args) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/app/utils/email/templates/"+templateName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        reader.close();
        return String.format(stringBuilder.toString(),args).replace("%%", "%");
        
    }

}
