/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.model.ManagerDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author nhonnhon
 */
public class ManagerPatient extends javax.swing.JFrame {

    public String ID, name, year, month, day, province, district, ward, condition, relatedto, password;
    DefaultComboBoxModel cbmodel_year, cbmodel_month, cbmodel_day, cbmodel_province, cbmodel_district, cbmodel_ward, cbmodel_tplace;
    Object[][] provinceObj, wardObj, distObj, tplaceObj;
    public ManagerPatient() { //create
        initComponents();
        cb_pw.setVisible(false);
        initDate();
        initAddress();
        initInfo();
    }
    public ManagerPatient(String ID) {
        initComponents();
        cb_pw.setVisible(true);
        tf_pw.setEnabled(false);
        initDate();
        initAddress();
        initInfo();
    }
    void initInfo(){
        ManagerDB db = new ManagerDB();
        try {
            tplaceObj = db.getdata("select treatment_place_id, treatment_place_name from treatment_place");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vector<String> treatmentName = new Vector<String>();
        for(int i =0 ;i < tplaceObj.length; i++){
            treatmentName.add(tplaceObj[i][1].toString());
        }
        cbmodel_tplace = new DefaultComboBoxModel(treatmentName);
        cb_tplace.setModel(cbmodel_tplace);
        
    }
    void initAddress(){
        cb_district.setEnabled(false);
        cb_ward.setEnabled(false);
        ManagerDB db = new ManagerDB();
        try {
            provinceObj = db.getdata("select province_id, province_name from province");
        } catch (SQLException ex) {
            Logger.getLogger(ManagerPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vector<String> provinceName = new Vector<String>();
        for(int i =0 ;i < provinceObj.length; i++){
            provinceName.add(provinceObj[i][1].toString());
        }
        cbmodel_province = new DefaultComboBoxModel(provinceName);
        cb_province.setModel(cbmodel_province);
        cb_province.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                try {
                    distObj = db.getdata("select district.district_id, district.district_name from province join province_has_district on province.province_id = province_has_district.province_id join district on district.district_id = province_has_district.district_id where province.province_id = " 
                            + String.valueOf(provinceObj[cb_province.getSelectedIndex()][0]));
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerPatient.class.getName()).log(Level.SEVERE, null, ex);
                }
                Vector<String> districtName = new Vector<String>();
                for(int i =0 ;i < distObj.length; i++){
                    districtName.add(distObj[i][1].toString());
                }
                cbmodel_district = new DefaultComboBoxModel(districtName);
                cb_district.setModel(cbmodel_district);
                cb_district.setEnabled(true);
            }
        });
        cb_district.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                try {
                    wardObj = db.getdata("select ward.ward_id, ward.ward_name from district join district_has_ward on district.district_id =  district_has_ward.district_id join ward on ward.ward_id = district_has_ward.ward_id where district.district_id = " 
                            + String.valueOf(distObj[cb_district.getSelectedIndex()][0]));
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerPatient.class.getName()).log(Level.SEVERE, null, ex);
                }
                Vector<String> districtName = new Vector<String>();
                for(int i =0 ;i < wardObj.length; i++){
                    districtName.add(wardObj[i][1].toString());
                }
                cbmodel_ward = new DefaultComboBoxModel(districtName);
                cb_ward.setModel(cbmodel_ward);
                cb_ward.setEnabled(true);
            }
        });
    }
    
    void initDate(){
        cb_month.setEnabled(false);
        cb_day.setEnabled(false);
        cbmodel_month = new DefaultComboBoxModel();
        cbmodel_month.addElement(Integer.toString(Calendar.getInstance().get(Calendar.MONTH)+1));
        cb_month.setModel(cbmodel_month);
        cbmodel_day = new DefaultComboBoxModel();
        cbmodel_day.addElement(Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
        cb_day.setModel(cbmodel_day);
        Vector<String> year = new Vector<String>();
        for(int i = Calendar.getInstance().get(Calendar.YEAR); i> 1900; i--){
            year.add(String.valueOf(i));
        }
        cbmodel_year = new DefaultComboBoxModel(year);
        cb_year.setModel(cbmodel_year);
        cb_year.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
              int maxmonth = 12;
              if(Integer.parseInt(cb_year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
              lb_error.setText(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
              Vector<String> month = new Vector<String>();
              for(int i =0; i<maxmonth; i++) month.add(String.valueOf(i+1));
              cbmodel_month = new DefaultComboBoxModel(month);
              cb_month.setModel(cbmodel_month);
              cb_month.setEnabled(true);
            }
        });
        cb_month.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
              int maxday = 31;
              int month = Integer.parseInt(cb_month.getSelectedItem().toString());
              if(month == 2){
                  if(Integer.parseInt(cb_year.getSelectedItem().toString())% 4 ==0 && Integer.parseInt(cb_year.getSelectedItem().toString())% 100!=0)  maxday = 29;
                  else maxday = 28;
              }
              else if(month == 4 || month == 6 || month == 9 || month == 11)maxday = 30;
              if(month == Calendar.getInstance().get(Calendar.MONTH)+1 && Integer.parseInt(cb_year.getSelectedItem().toString()) == Calendar.getInstance().get(Calendar.YEAR)) maxday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
              Vector<String> day = new Vector<String>();
              for(int i =1; i<=maxday; i++) day.add(String.valueOf(i));
              cbmodel_day = new DefaultComboBoxModel(day);
              cb_day.setModel(cbmodel_day);
              cb_day.setEnabled(true);
            }
        });
    }
    
    void close() throws SQLException{
        ManagerDB db = new ManagerDB();
        //check id
        if(this.tf_id.getText().isEmpty() || this.tf_id.getText().length() < 3|| this.tf_id.getText().length() > 10){ this.lb_error.setText("Invalid id"); return; }
        try { int intValue = Integer.parseInt(this.tf_id.getText()); } catch (NumberFormatException e) { this.lb_error.setText("Invalid id."); return; }
        if(db.count("Select count(username) from ql_user where username = " + this.tf_id.getText())>=1){ this.lb_error.setText("Invalid id...."); return; }
        
        //check name
        if(this.tf_name.getText().isEmpty()||this.tf_name.getText().length() < 1 || this.tf_name.getText().length() > 50){ this.lb_error.setText("Invalid name"); }
        
        //check related
        if(tf_related.getText().isEmpty()){this.lb_error.setText("Invalid related id."); return; }
        else if(db.count("select count(citizen_id) from covid_patient where condition != 'F3' and citizen_id = " + tf_related.getText().toString()) == 0) { this.lb_error.setText("Invalid related id"); return;} 
        
        //check date + address
        if(!cb_day.isEnabled()) {this.lb_error.setText("Please input patient's DOB."); return; }
        if(!cb_ward.isEnabled()) {this.lb_error.setText("Please input patient's Address."); return; }
        
        //check pw
        if(false) {this.lb_error.setText("Invalid password"); return; } 
        
        //ok
        this.lb_error.setText("Successfully!");
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_id = new java.awt.TextField();
        jLabel2 = new javax.swing.JLabel();
        tf_name = new java.awt.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_related = new java.awt.TextField();
        jLabel8 = new javax.swing.JLabel();
        tf_pw = new javax.swing.JPasswordField();
        btn_add = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        lb_error = new javax.swing.JLabel();
        cb_pw = new javax.swing.JCheckBox();
        cb_year = new javax.swing.JComboBox<>();
        cb_month = new javax.swing.JComboBox<>();
        cb_day = new javax.swing.JComboBox<>();
        cb_province = new javax.swing.JComboBox<>();
        cb_district = new javax.swing.JComboBox<>();
        cb_ward = new javax.swing.JComboBox<>();
        cb_tplace = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Citizen ID");

        tf_id.setForeground(new java.awt.Color(51, 51, 51));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Full name");

        tf_name.setForeground(new java.awt.Color(51, 51, 51));

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Date of birth");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Address");

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Treatment place");

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Related");

        tf_related.setForeground(new java.awt.Color(51, 51, 51));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("New password");

        tf_pw.setBackground(new java.awt.Color(255, 255, 255));
        tf_pw.setForeground(new java.awt.Color(51, 51, 51));

        btn_add.setBackground(new java.awt.Color(51, 51, 51));
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("Update/Add");
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_addMouseClicked(evt);
            }
        });

        btn_cancel.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancel.setForeground(new java.awt.Color(51, 51, 51));
        btn_cancel.setText("Cancel");

        lb_error.setBackground(new java.awt.Color(204, 0, 0));
        lb_error.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lb_error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_error.setToolTipText("");

        cb_year.setBackground(new java.awt.Color(255, 255, 255));
        cb_year.setForeground(new java.awt.Color(51, 51, 51));
        cb_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year" }));

        cb_month.setBackground(new java.awt.Color(255, 255, 255));
        cb_month.setForeground(new java.awt.Color(51, 51, 51));
        cb_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        cb_day.setBackground(new java.awt.Color(255, 255, 255));
        cb_day.setForeground(new java.awt.Color(51, 51, 51));
        cb_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        cb_province.setBackground(new java.awt.Color(255, 255, 255));
        cb_province.setForeground(new java.awt.Color(51, 51, 51));
        cb_province.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Province" }));

        cb_district.setBackground(new java.awt.Color(255, 255, 255));
        cb_district.setForeground(new java.awt.Color(51, 51, 51));
        cb_district.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "District" }));

        cb_ward.setBackground(new java.awt.Color(255, 255, 255));
        cb_ward.setForeground(new java.awt.Color(51, 51, 51));
        cb_ward.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ward" }));

        cb_tplace.setBackground(new java.awt.Color(255, 255, 255));
        cb_tplace.setForeground(new java.awt.Color(51, 51, 51));
        cb_tplace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Address" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(2, 2, 2)
                                .addComponent(cb_pw))
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tf_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cb_year, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cb_province, 0, 116, Short.MAX_VALUE))
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cb_month, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cb_district, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cb_ward, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cb_day, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tf_related, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tf_pw, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel6)
                                    .addComponent(cb_tplace, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_province, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_district, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(tf_related, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_error, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb_pw)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseClicked
        
        close();
    }//GEN-LAST:event_btn_addMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerPatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JComboBox<String> cb_day;
    private javax.swing.JComboBox<String> cb_district;
    private javax.swing.JComboBox<String> cb_month;
    private javax.swing.JComboBox<String> cb_province;
    private javax.swing.JCheckBox cb_pw;
    private javax.swing.JComboBox<String> cb_tplace;
    private javax.swing.JComboBox<String> cb_ward;
    private javax.swing.JComboBox<String> cb_year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_error;
    private java.awt.TextField tf_id;
    private java.awt.TextField tf_name;
    private javax.swing.JPasswordField tf_pw;
    private java.awt.TextField tf_related;
    // End of variables declaration//GEN-END:variables

    
}
