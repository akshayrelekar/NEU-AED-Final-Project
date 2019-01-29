/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.userinterface.legalEntity;

import app.data.Network;
import app.data.org.Organization;
import app.entities.user.Lawyer;
import app.entities.user.LegalEntity;
import app.userinterface.common.CustomPanel;
import app.utils.CommonUtils;
import app.utils.ConfigUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Saurabh Gujare (NUID : 001424874)
 */
public class ViewLEProfilePanel extends CustomPanel {

    /**
     * Creates new form ViewLEProfilePanel
     */
    LegalEntity legalEntity;
    private boolean readOnly;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private File picFile;
    private BufferedImage pic;
    final JFileChooser fc = new JFileChooser();
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String SSN_REGEX = "^\\d{3}-\\d{2}-\\d{4}$";
    public ViewLEProfilePanel(LegalEntity legalEntity,boolean readOnly) {
        initComponents();
        this.makeTransparent(this);
        this.readOnly = readOnly;
        this.legalEntity = legalEntity;
        populateText(legalEntity);
        
        if(!readOnly){
            picPanel.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent me) {
                    super.mouseClicked(me); 
                    int returnVal = fc.showOpenDialog(ViewLEProfilePanel.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        try {
                            BufferedImage img = null;
                            try {
                                img = ImageIO.read(fc.getSelectedFile());
                            } catch (IOException e) {
                            }
                            CommonUtils.initPicPanel(img, picPanel);
                            pic = img;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }}
            );
        }
        initDisplay(readOnly, legalEntity);
        domainList.setModel(new DefaultComboBoxModel(Network.getInstance().getPUBLIC_DOMAIN().getAllEntries().toArray()));
    }

    public LegalEntity validateandGetLE(boolean isCreatedNow){
    
        if(legalEntity==null){
            legalEntity = new LegalEntity();
        }
        
        if(fnameTxt.getText()== null || fnameTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter tje first name");
            return null;
        }
        if(lnameTxt.getText()== null || lnameTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter the last name");
            return null;
        }
        if(ssnTxt.getText()== null || ssnTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter the social security number(xxx-xx-xxxx)");
            return null;
        }
        if(ssnTxt.getText()!= null && !Pattern.matches(SSN_REGEX, ssnTxt.getText()) ){
            JOptionPane.showMessageDialog(this, "Please enter a valid SSN");
            return null;
        }
        if(emailTxt.getText()== null || emailTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter an email address");
            return null;
        }
        if(emailTxt.getText()!=null && !Pattern.matches(EMAIL_REGEX, emailTxt.getText()) ){
            JOptionPane.showMessageDialog(this, "Please enter a valid email address");
            return null;
        }
        legalEntity.setFirstName(fnameTxt.getText());
//        legalEntity.setMiddleName(mnameTxt.getText());
        legalEntity.setLastName(lnameTxt.getText());
        legalEntity.getPriamryAddress().setStreet(streetTxt.getText());
        legalEntity.getPriamryAddress().setCity(cityTxt.getText());
        legalEntity.getPriamryAddress().setState(stateTxt.getText());
        legalEntity.getPriamryAddress().setZipcode(zipTxt.getText());
        legalEntity.getPrimaryContact().setContactNumber(phoneTxt.getText());
        legalEntity.setEmail(emailTxt.getText());
        legalEntity.setSsn(ssnTxt.getText());
        legalEntity.setPic(pic);
        if(domainList.getSelectedItem()!=null){
            legalEntity.setParent((Organization) domainList.getSelectedItem());
        }
        else{
            JOptionPane.showMessageDialog(this, "Please select a Domain");
            return null;
        }
        try {
            legalEntity.setDob(sdf.parse(dobTxt.getText()));
        } catch (ParseException ex) {
            //Logger.getLogger(ViewLEProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return legalEntity;
    }
    
    private void initDisplay(boolean readOnly, LegalEntity legalEntity) {
        saveBtn.setVisible(!readOnly && legalEntity!=null);
        domainList.setEnabled(!readOnly);
        toggleReadOnly(this, readOnly);
    }
    
    private void toggleReadOnly(Container container,boolean readOnly){
        for(Component c: container.getComponents()){
            if(c instanceof JTextField){
                ((JTextField)c).setEnabled(!readOnly);
            }
            else if(c instanceof Container){
                toggleReadOnly((Container) c,readOnly);
            }
        }
        
    }
    
    private void populateText(LegalEntity legalEntity){
        if(legalEntity != null){
        fnameTxt.setText(legalEntity.getFirstName());
//        mnameTxt.setText(legalEntity.getMiddleName());
        lnameTxt.setText(legalEntity.getLastName());
        streetTxt.setText(legalEntity.getPriamryAddress().getStreet());
        cityTxt.setText(legalEntity.getPriamryAddress().getCity());
        stateTxt.setText(legalEntity.getPriamryAddress().getState());
        zipTxt.setText(legalEntity.getPriamryAddress().getZipcode());
        phoneTxt.setText(legalEntity.getPrimaryContact().getContactNumber());
        emailTxt.setText(legalEntity.getEmail());
        ssnTxt.setText(legalEntity.getSsn());
        dobTxt.setText(legalEntity.getDob().toString());
        pic = legalEntity.getPic();
        
        }
        try {
            if(pic==null){
                CommonUtils.initPicPanel("src/app/images/add.png", picPanel);
            }
            else{
                CommonUtils.initPicPanel(pic, picPanel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        picPanel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fnameTxt = new javax.swing.JTextField();
        phoneTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        zipTxt = new javax.swing.JTextField();
        cityTxt = new javax.swing.JTextField();
        stateTxt = new javax.swing.JTextField();
        streetTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ssnTxt = new javax.swing.JTextField();
        lnameTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        dobTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        domainList = new javax.swing.JComboBox<>();
        saveBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("NA"); // NOI18N

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel5.setBackground(Color.decode(ConfigUtil.getProp("basecolor"))
        );
        jPanel5.setOpaque(false);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setOpaque(false);

        picPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picPanel.setPreferredSize(new java.awt.Dimension(200, 240));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setOpaque(false);

        jLabel2.setText("First Name:");

        fnameTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fnameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameTxtActionPerformed(evt);
            }
        });

        phoneTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        emailTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        zipTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cityTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        stateTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        streetTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setText("Street");

        jLabel5.setText("Phone Number:");

        jLabel6.setText("Email:");

        jLabel7.setText("City");

        jLabel8.setText("Zipcode");

        jLabel9.setText("State");

        jLabel10.setText("SSN:");

        ssnTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lnameTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel11.setText("Last Name:");

        dobTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel12.setText("DOB:");

        jLabel1.setText("Domain");

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/images/path7395.png"))); // NOI18N
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(zipTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fnameTxt)
                    .addComponent(lnameTxt)
                    .addComponent(streetTxt)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(phoneTxt)
                    .addComponent(dobTxt)
                    .addComponent(emailTxt)
                    .addComponent(ssnTxt)
                    .addComponent(domainList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addGap(157, 157, 157))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(domainList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ssnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(streetTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(zipTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dobTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fnameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameTxtActionPerformed
        // TODO add your handling code here:       
    }//GEN-LAST:event_fnameTxtActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if(validateandGetLE(false)==null){
            return;
        }
        JOptionPane.showMessageDialog(null, "Updated");
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cityTxt;
    private javax.swing.JTextField dobTxt;
    private javax.swing.JComboBox<String> domainList;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField fnameTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lnameTxt;
    private javax.swing.JTextField phoneTxt;
    private javax.swing.JLabel picPanel;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField ssnTxt;
    private javax.swing.JTextField stateTxt;
    private javax.swing.JTextField streetTxt;
    private javax.swing.JTextField zipTxt;
    // End of variables declaration//GEN-END:variables
}
