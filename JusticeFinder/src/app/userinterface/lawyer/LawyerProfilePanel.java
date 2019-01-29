/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.userinterface.lawyer;

import app.utils.CommonUtils;
import app.data.Network;
import app.data.directories.Directory;
import app.data.directories.interfaces.DirectoryEntry;
import app.data.org.StateBarAssociation;
import app.entities.user.Address;
import app.entities.user.Lawyer;
import app.entities.workqueues.LawyerApprovalRequest;
import app.userinterface.common.CustomPanel;
import app.userinterface.sba.ViewSBARequestsPanel;
import app.utils.ConfigUtil;
import app.utils.email.EmailTemplateFormatter;
import app.utils.email.EmailUtil;
import app.utils.email.templates.Templates;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class LawyerProfilePanel extends CustomPanel {

    private Directory<String, StateBarAssociation> sbaNeedApprovalList;
    private List<String> areaOfPractice;
    private boolean readOnly;
    private Lawyer lawyer;
    private File picFile;
    private BufferedImage pic;
    final JFileChooser fc = new JFileChooser();
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String SSN_REGEX = "^\\d{3}-\\d{2}-\\d{4}$";
    /**
     * Creates new form LawyerProfilePanel1
     */
    public LawyerProfilePanel(Lawyer lawyer,boolean readOnly) {
        initComponents();
        this.makeTransparent(this);
        this.readOnly = readOnly;
        this.lawyer = lawyer;
        populateForm(lawyer);
        initDisplay(readOnly,lawyer);
        sbaNeedApprovalList = new Directory<>();
        areaOfPractice = new ArrayList<>();
        
        if(!readOnly){
            picPanel.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent me) {
                    super.mouseClicked(me); 
                    int returnVal = fc.showOpenDialog(LawyerProfilePanel.this);

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
                }

            });
        }
    }

    private void initDisplay(boolean readOnly, Lawyer lawyer) {
        addSBA.setVisible(!readOnly);
        addPracticeBtn.setVisible(!readOnly);
        saveBtn.setVisible(!readOnly && lawyer!=null);
        sbaAvailableList.setVisible(!readOnly);
        toggleReadOnly(this, readOnly);
    }

    private void populateForm(Lawyer lawyer) {
        if (lawyer != null) {
            DefaultListModel model = new DefaultListModel();
            for (StateBarAssociation s : lawyer.getAllowedStateBars().getAllEntries()) {
                if(s.isActive())
                    model.addElement(s);
            }
            
            sbaList.setModel(model);
            
            model = new DefaultListModel();
            sbaNeedApprovalList = lawyer.getRequestedStateBars();
            for (StateBarAssociation s : sbaNeedApprovalList.getAllEntries()) {
                if(s.isActive())
                    model.addElement(s);
            }
            sbaReqList.setModel(model);
            
            fnameTxt.setText(lawyer.getFirstName());
            lnameTxt.setText(lawyer.getLastName());
            pic = lawyer.getPic();
            streetAddrTxt.setText(lawyer.getAddress().getStreet());
            cityTxt.setText(lawyer.getAddress().getCity());
            stateTxt.setText(lawyer.getAddress().getState());
            zipcodeTxt.setText(lawyer.getAddress().getZipcode());
            phnNumberTxt.setText(lawyer.getWorkContact().getContactNumber());
            emailTxt.setText(lawyer.getEmail());
            feesTxt.setText(lawyer.getFees().toString());
            lawSchoolTxt.setText(lawyer.getLawSchool());
            ssnTxt.setText(lawyer.getSsn());
            
            model = new DefaultListModel();
            areaOfPractice = lawyer.getAreaOfPractice();
            for(String area: areaOfPractice){
                model.addElement(area);
            }
            practiceList.setModel(model);
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
        
        sbaAvailableList.setModel(new DefaultComboBoxModel(Network.getInstance().getSTATE_BAR_ASSOCIATIONS().getAllEntries().toArray()));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        picPanel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fnameTxt = new javax.swing.JTextField();
        lnameTxt = new javax.swing.JTextField();
        phnNumberTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        zipcodeTxt = new javax.swing.JTextField();
        cityTxt = new javax.swing.JTextField();
        stateTxt = new javax.swing.JTextField();
        streetAddrTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ssnTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        feesTxt = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lawSchoolTxt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sbaList = new javax.swing.JList<>();
        sbaAvailableList = new javax.swing.JComboBox<>();
        addSBA = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        sbaReqList = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        practiceList = new javax.swing.JList<>();
        practiceTxt = new javax.swing.JTextField();
        addPracticeBtn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 102));
        setName("NA"); // NOI18N
        setOpaque(false);

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setName(""); // NOI18N

        jPanel6.setBackground(Color.decode(ConfigUtil.getProp("basecolor"))
        );
        jPanel6.setName("NA"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Profile"));
        jPanel1.setOpaque(false);

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
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        jLabel2.setText("First Name");

        jLabel3.setText("Last Name");

        fnameTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lnameTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        phnNumberTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        emailTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        zipcodeTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cityTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        stateTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        streetAddrTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setText("Street");

        jLabel5.setText("Phone Number");

        jLabel6.setText("Email");

        jLabel7.setText("City");

        jLabel8.setText("Zipcode");

        jLabel9.setText("State");

        jLabel13.setText("SSN");

        ssnTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setText("Fees");

        feesTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ssnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetAddrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(cityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(stateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zipcodeTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(phnNumberTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(feesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(streetAddrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(stateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zipcodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ssnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phnNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(feesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Education Details"));
        jPanel7.setOpaque(false);

        jLabel10.setText("Law School");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lawSchoolTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lawSchoolTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("State Bar Associations"));
        jPanel4.setOpaque(false);

        jScrollPane1.setViewportView(sbaList);

        addSBA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/images/path6261.png"))); // NOI18N
        addSBA.setText("Add");
        addSBA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSBAActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(sbaReqList);

        jLabel11.setText("Approved");

        jLabel12.setText("Requested");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(sbaAvailableList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(addSBA))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbaAvailableList, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSBA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Area Of Practice"));
        jPanel5.setOpaque(false);

        jScrollPane3.setViewportView(practiceList);

        addPracticeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/images/path6261.png"))); // NOI18N
        addPracticeBtn.setText("Add Area Of Practice");
        addPracticeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPracticeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(practiceTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPracticeBtn)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(practiceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPracticeBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/images/path7395.png"))); // NOI18N
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if(validateandGetLawyer(false)==null){
            return;
        }
        
        for (StateBarAssociation sba : lawyer.getRequestedStateBars().getAllEntries()) {
            LawyerApprovalRequest req = (LawyerApprovalRequest) sba.getWorkQueue().createNewWorkItem(lawyer.getAccount(), sba.getAdmin().getAccount(), "Request");
            
            String LAWYER_NAME = lawyer.getFirstName()+" "+lawyer.getLastName();
            String SBA_NAME = sba.getStateBarAssociationName();
            String body = "";
            try {
                body = EmailTemplateFormatter.getMessage(Templates.LAWYER_REQUEST.getPageName(), SBA_NAME, LAWYER_NAME);
            } catch (IOException ex) {
                Logger.getLogger(ViewSBARequestsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            EmailUtil.sendMail(sba.getEmail(), "Lawyer Approval Request", body);
        }
        
        JOptionPane.showMessageDialog(this, "Data Updated");
    }//GEN-LAST:event_saveBtnActionPerformed

    private void addSBAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSBAActionPerformed
        
        if(sbaAvailableList.getSelectedItem()==null){
            return;
        }
        StateBarAssociation assoc = (StateBarAssociation) sbaAvailableList.getSelectedItem();
        if(lawyer!=null){
            List<StateBarAssociation> list = lawyer.getAllowedStateBars().getAllEntries();
            for(StateBarAssociation a:list){
                if(a.isActive()){
                    if(assoc.getStateBarAssociationName().equals(a.getStateBarAssociationName())){
                        JOptionPane.showMessageDialog(this, "State Bar Already approved");
                        return;
                    }
                }
            }
        }
        try {
            // TODO add your handling code here:
            sbaNeedApprovalList.addNew((StateBarAssociation) sbaAvailableList.getSelectedItem());
        } catch (Exception ex) {
            Logger.getLogger(LawyerProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultListModel model = new DefaultListModel();
        for (StateBarAssociation s : sbaNeedApprovalList.getAllEntries()) {
            model.addElement(s);
        }

        sbaReqList.setModel(model);
        
    }//GEN-LAST:event_addSBAActionPerformed

    private void addPracticeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPracticeBtnActionPerformed
        
        if(practiceTxt.getText()!=null && !practiceTxt.getText().trim().equals("")){
            areaOfPractice.add(practiceTxt.getText());
            
            DefaultListModel model = new DefaultListModel();
            for (String s : areaOfPractice) {
                model.addElement(s);
            }

            practiceList.setModel(model);
        }
        
    }//GEN-LAST:event_addPracticeBtnActionPerformed

    public Lawyer validateandGetLawyer(boolean isCreatedNow){
     
        if(lawyer==null){
            lawyer = new Lawyer();
        }
        
        if(fnameTxt.getText()== null || fnameTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your first name");
            return null;
        }
        if(lnameTxt.getText()== null || lnameTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your last name");
            return null;
        }
        if(ssnTxt.getText()== null || ssnTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your social security number (xxx-xx-xxxx)");
            return null;
        }
//        if(ssnTxt.getText()!= null && !Pattern.matches(SSN_REGEX, ssnTxt.getText()) ){
//            JOptionPane.showMessageDialog(this, "Please enter a valid SSN");
//            return null;
//        }
        if(emailTxt.getText()== null || emailTxt.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter your email address");
            return null;
        }
        if(emailTxt.getText()!=null && !Pattern.matches(EMAIL_REGEX, emailTxt.getText()) ){
            JOptionPane.showMessageDialog(this, "Please enter a valid email address");
            return null;
        }
        if(isCreatedNow && sbaNeedApprovalList.size()==0){
            JOptionPane.showMessageDialog(this, "Please select alteast one State Bar Association");
            return null;
        }
        lawyer.setFirstName(fnameTxt.getText());
        lawyer.setLastName(lnameTxt.getText());
        lawyer.setEmail(emailTxt.getText());
        lawyer.getAddress().setStreet(streetAddrTxt.getText());
        lawyer.getAddress().setCity(cityTxt.getText());
        lawyer.getAddress().setState(stateTxt.getText());
        lawyer.getAddress().setZipcode(zipcodeTxt.getText());
        lawyer.getWorkContact().setContactNumber(phnNumberTxt.getText());
        lawyer.setRequestedStateBars(sbaNeedApprovalList);
        lawyer.setSsn(ssnTxt.getText());
        lawyer.setAreaOfPractice(areaOfPractice);
        lawyer.setPic(pic);
        lawyer.setLawSchool(lawSchoolTxt.getText());
        try{
            lawyer.setFees(Double.parseDouble(feesTxt.getText()));
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please enter a valid number in Fees");
            return null;
        }
        return lawyer;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPracticeBtn;
    private javax.swing.JButton addSBA;
    private javax.swing.JTextField cityTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField feesTxt;
    private javax.swing.JTextField fnameTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField lawSchoolTxt;
    private javax.swing.JTextField lnameTxt;
    private javax.swing.JTextField phnNumberTxt;
    private javax.swing.JLabel picPanel;
    private javax.swing.JList<String> practiceList;
    private javax.swing.JTextField practiceTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> sbaAvailableList;
    private javax.swing.JList<String> sbaList;
    private javax.swing.JList<String> sbaReqList;
    private javax.swing.JTextField ssnTxt;
    private javax.swing.JTextField stateTxt;
    private javax.swing.JTextField streetAddrTxt;
    private javax.swing.JTextField zipcodeTxt;
    // End of variables declaration//GEN-END:variables
}
