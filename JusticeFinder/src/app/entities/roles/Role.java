/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.roles;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public class Role<P extends JPanel>{

    private final Class<P> workspace;

    public Role(Class<P> workspace) {
        this.workspace = workspace;
    }
    
    public JPanel getNewWorkspace() {
        
        try {
            return workspace.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(Role.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Role.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
