/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.userinterface.legalEntity;

import app.data.Network;
import app.data.analytics.SorterFactory;
import app.data.directories.Directory;
import app.data.org.StateBarAssociation;
import app.entities.user.Lawyer;
import app.userinterface.common.CustomPanel;
import app.utils.CommonUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Saurabh Gujare (NUID : 001424874)
 */
public class SearchLawyerPanel extends CustomPanel {

    /**
     * Creates new form SearchLawyerPanel
     */
    Directory<String, Lawyer> LAWYER_DIRECTORY;
    List<StateBarAssociation> sbaArrList;
    private List<JLabel> starList;
    
    Comparator sortor;
    int selectedRating = 1;
    
    public SearchLawyerPanel(Directory<String, Lawyer> LAWYER_DIRECTORY) {
        initComponents();
        this.makeTransparent(this);
        this.LAWYER_DIRECTORY = LAWYER_DIRECTORY;
        ComponentAdapter adapter = new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent ce) {
                super.componentShown(ce); 
                populateTable(LAWYER_DIRECTORY);
            }
            
        };
        this.addComponentListener(adapter);
        this.sbaArrList = Network.getInstance().getSTATE_BAR_ASSOCIATIONS().getAllEntries();
        
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        
        for(SorterFactory s : SorterFactory.values()){
            comboModel.addElement(s.getSorter());
        }
        sorterCombo.setModel(comboModel);
        sortor = (Comparator) sorterCombo.getSelectedItem();
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        model.addElement("ALL");
        for(StateBarAssociation s: sbaArrList){
            model.addElement(s);
        }
        sbaList.setModel(model);
        
        populateTable(LAWYER_DIRECTORY);
        
        starList= new ArrayList<>();
        for(int count =1;count <= 5;count++){
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(30,30));
            label.setToolTipText(count+"");
            
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                    JLabel label = (JLabel)e.getSource();
                    int r = Integer.parseInt(label.getToolTipText());
                    updateRating(r);
                    selectedRating = r;
                }
                
            });
            
            starList.add(label);
            rateGrid.add(label);
        }
        updateRating(selectedRating);
    }
    private void populateTable(Directory<String, Lawyer> LAWYER_DIRECTORY){
        
        resultPanel.setBackground(Color.red);
        resultPanel.removeAll();
        List<Lawyer> list = LAWYER_DIRECTORY.getAllEntries();
        list = filter(list);
        Collections.sort(list,sortor);
        resultPanel.add(new searchResultPanel(list));
        resultPanel.revalidate();
        resultPanel.repaint();
    }
    
    private void updateRating(int rate){
        try {
            int count = 0;
            for(JLabel l: starList){
                if(count<rate){
                    CommonUtils.initPicPanel("src/app/images/starFilled.png", l);
                }
                else{
                    CommonUtils.initPicPanel("src/app/images/star.png", l);
                }
                count++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    private List<Lawyer> filter(List<Lawyer> list){
        
        
        Double min = null; 
        Double max = null; 
        
        try{
            if(!minFees.getText().equals("")){
                min = Double.parseDouble(minFees.getText());
            }
            
            if(!minFees.getText().equals("")){
                max = Double.parseDouble(maxFees.getText());
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid fees value.");
        }
        
        StateBarAssociation selectedSBA = null;
        if(sbaList.getSelectedIndex() != 0){
            selectedSBA = (StateBarAssociation) sbaList.getSelectedItem();
        }
         
        
        String typeOfHelp = helpTxt.getText();
        
        List<Lawyer> resultList = new ArrayList<>();
        
        for(Lawyer l : list){
            boolean add = true;
            
            int count = 0;
            for(StateBarAssociation assoc :l.getAllowedStateBars().getAllEntries()){
                if(assoc.isActive()){
                    count++;
                }
            }
            
            if(count<=0){
                add = add && false;
            }
            //fees filter
            if(max!=null && min!=null ){
                if(l.getFees() ==null){
                    add = add && false;
                }
                else{
                    add = add && l.getFees()>=min && l.getFees()<=max;
                }
            }
            else{
                add = add && true;
            }
            
            if(selectedSBA==null){
                add = add && true;
            }
            else{
                add = add && l.getAllowedStateBars().contains(selectedSBA);
            }
            
            if(typeOfHelp.equals("")){
                add = add && true;
            }
            else{
                if(l.getAreaOfPractice().isEmpty()){
                    add = add && false;
                }
                else{
                    for(String areaOfPractice : l.getAreaOfPractice()){
                        add = add && areaOfPractice.contains(typeOfHelp);
                        break;
                    }
                }
            }
            
            add = add && (l.getRating()>=selectedRating);
            
            
            if(add){
                resultList.add(l);
            }
            
        }
        
        return resultList;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        minFees = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        maxFees = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sbaList = new javax.swing.JComboBox<>();
        searchBtn = new javax.swing.JButton();
        helpTxt = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rateGrid = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sorterCombo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultPanel = new javax.swing.JPanel();

        setOpaque(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Fliter"));
        jPanel3.setOpaque(false);

        jPanel4.setOpaque(false);

        jLabel3.setText("Fees Range");

        jLabel4.setText("to");

        jLabel1.setText("Type Of Legal Assistance");

        jLabel5.setText("State bar association");

        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/images/path12500.png"))); // NOI18N
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        clearBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/images/path13634.png"))); // NOI18N
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Rating");

        jPanel5.setOpaque(false);

        rateGrid.setOpaque(false);
        rateGrid.setLayout(new java.awt.GridLayout(0, 5, 5, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rateGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rateGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel7.setText("& Up");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sbaList, 0, 200, Short.MAX_VALUE)
                    .addComponent(helpTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(minFees, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxFees, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(helpTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxFees, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(minFees, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(clearBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sbaList, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jLabel2.setText("Sort By");

        sorterCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sorterComboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sorterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sorterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setOpaque(false);

        resultPanel.setOpaque(false);
        resultPanel.setLayout(new javax.swing.BoxLayout(resultPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(resultPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        populateTable(LAWYER_DIRECTORY);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        helpTxt.setText("");
        minFees.setText("");
        maxFees.setText("");
        sbaList.setSelectedIndex(0);
        selectedRating = 1;
        updateRating(selectedRating);
        populateTable(LAWYER_DIRECTORY);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void sorterComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sorterComboItemStateChanged
        // TODO add your handling code here:
        sortor = (Comparator) sorterCombo.getSelectedItem();
        populateTable(LAWYER_DIRECTORY);
    }//GEN-LAST:event_sorterComboItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField helpTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField maxFees;
    private javax.swing.JTextField minFees;
    private javax.swing.JPanel rateGrid;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JComboBox<String> sbaList;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox<String> sorterCombo;
    // End of variables declaration//GEN-END:variables
}
