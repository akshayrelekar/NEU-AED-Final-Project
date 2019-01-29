/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.userinterface.common;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class CustomPanel extends JPanel {

    protected void makeTransparent(Container panel) {

        if (!(panel instanceof Header) && (panel instanceof JPanel || panel instanceof CustomPanel)) {
            JPanel p = ((JPanel) panel);
            
            if(p.getName()==null || !p.getName().equals("NA")){
                p.setOpaque(false);
            }
        }
        for (Component c : panel.getComponents()) {

            if (!(c instanceof Header) && (c instanceof JPanel || c instanceof CustomPanel)) {
                JPanel p = ((JPanel) c);

                if (p.getName()==null || !p.getName().equals("NA")) {
                    p.setOpaque(false);
                }
            }

            if (!(c instanceof Header) && c instanceof Container) {
                makeTransparent((Container) c);
            }
        }
    }
}
