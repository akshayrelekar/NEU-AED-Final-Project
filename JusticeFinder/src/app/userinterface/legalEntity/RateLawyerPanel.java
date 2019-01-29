/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.userinterface.legalEntity;

import app.data.Network;
import app.data.Session;
import app.data.directories.Directory;
import app.entities.user.Lawyer;
import app.entities.user.LegalEntity;
import app.userinterface.common.CustomPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

/**
 *
 * @author PC
 */
public class RateLawyerPanel extends CustomPanel {

    /**
     * Creates new form RateLawyerPage
     */
    public RateLawyerPanel() {
        initComponents();
        this.makeTransparent(this);
        ComponentAdapter adapter = new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent ce) {
                super.componentShown(ce); 
                populateTable(Network.getInstance().getLAWYER_DIRECTORY());
            }
            
        };
        
        this.addComponentListener(adapter);
        populateTable(Network.getInstance().getLAWYER_DIRECTORY());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        allLawyersPanel = new javax.swing.JPanel();

        allLawyersPanel.setLayout(new javax.swing.BoxLayout(allLawyersPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(allLawyersPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allLawyersPanel;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    
    private void populateTable(Directory<String, Lawyer> LAWYER_DIRECTORY){
        
        allLawyersPanel.removeAll();
        List<Lawyer> list = LAWYER_DIRECTORY.getAllEntries();
        for(Lawyer l: list){
            if(((LegalEntity)Session.getUserAccount().getUser()).getLawyers().contains(l)){
                allLawyersPanel.add(new LawyersRowPanel(l));
            }
        }
        allLawyersPanel.revalidate();
        allLawyersPanel.repaint();
    }
}