/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.model.ManagerDB;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
/**
 *
 * @author nhonnhon
 */
public class Manager_updatepatient extends javax.swing.JPanel {

    /**
     * Creates new form Manager_updatepatient
     */
    ManagerDB db = new ManagerDB();
    JDialog d;
    DefaultComboBoxModel cbmodeltplace, cbmodelconditon;
    int tplaceindexincombobox;
    String treatmentid, conditionnow;
    Object[][] treatment, condition;
    String id;
    public Manager_updatepatient(String i) throws SQLException{
        initComponents();
        id = i;
        initTreatment();
        initCondition();
        initDialog();
    }
    void initCondition() throws SQLException{
        conditionnow = db.get("Select condition from covid_patient where citizen_id = " + id);
        Vector<String> vt = new Vector<String>();
        vt.add("F0"); vt.add("F1"); vt.add("F2"); vt.add("F3");
        cbmodelconditon = new DefaultComboBoxModel(vt);
        d3con.setModel(cbmodelconditon);
        if(conditionnow.equals("F0")) d3con.setSelectedIndex(0);
        else if(conditionnow.equals("F1")) d3con.setSelectedIndex(1);
        else if(conditionnow.equals("F2")) d3con.setSelectedIndex(2);
        else if(conditionnow.equals("F2")) d3con.setSelectedIndex(3);
    }
    void initTreatment() throws SQLException{
        treatmentid = db.get("Select treatment_place_id from covid_patient where citizen_id = " + id);
        treatment = db.getdata("select treatment_place_id, treatment_place_name from treatment_place where current_holding < capacity or treatment_place_id = " +id);
        Vector<String> vt = new Vector<String>();
        for(int i = 0; i<treatment.length; i++){
            if(treatment[i][0].toString().equals(treatmentid)) tplaceindexincombobox = i;
            vt.add(treatment[i][1].toString());
        }
        cbmodeltplace = new DefaultComboBoxModel(vt);
        d3place.setModel(cbmodeltplace);
        d3place.setSelectedIndex(tplaceindexincombobox);
    }
    void initDialog() throws SQLException{
        String name = db.get("Select full_name from covid_patient where citizen_id = " + id);
        d2id.setText(name);
        d2id.setEditable(false);
        d = new JDialog();
        d.setSize(360, 220);
        d.add(this);
        d.setResizable(false);
        d.setModal(true);
        d.setLocationRelativeTo(null);
        d.setTitle("Update patient "+ name + " ("+id+")");
        d.setVisible(true);
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
        textlabel = new javax.swing.JLabel();
        d2id = new javax.swing.JTextField();
        d3con = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        d3place = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        d2update = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));

        textlabel.setBackground(java.awt.Color.white);
        textlabel.setForeground(new java.awt.Color(102, 102, 102));
        textlabel.setText("Citizen ID");

        d2id.setBackground(java.awt.Color.white);
        d2id.setForeground(new java.awt.Color(102, 102, 102));

        d3con.setBackground(java.awt.Color.white);
        d3con.setForeground(new java.awt.Color(102, 102, 102));
        d3con.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setBackground(java.awt.Color.white);
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Condition");

        d3place.setBackground(java.awt.Color.white);
        d3place.setForeground(new java.awt.Color(102, 102, 102));
        d3place.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setBackground(java.awt.Color.white);
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Treatment place");

        d2update.setBackground(new java.awt.Color(51, 51, 51));
        d2update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d2update.setForeground(new java.awt.Color(255, 255, 255));
        d2update.setText("Update");
        d2update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                d2updateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(textlabel)
                        .addGap(0, 284, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(d3place, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(d3con, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(d2id))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(d2update, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(textlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d2id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d3con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d3place, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(d2update, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void d2updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_d2updateMouseClicked
        String newtreatmentid = treatment[d3place.getSelectedIndex()][0].toString();
        if(!newtreatmentid.equals(treatmentid)){
            String newqr = "update covid_patient set treatment_place_id = " + newtreatmentid +" where citizen_id = " +id;
            newqr += "update treatment_place set current_holding = current_holding + 1 where treatment_place_id = " +newtreatmentid;
            newqr += "update treatment_place set current_holding = current_holding - 1 where treatment_place_id = " +treatmentid;
            db.update(newqr);
        }
        String newcondition = d3con.getSelectedItem().toString()
        if(!newcondition.equals(conditionnow)){
            if(newcondition == "F0"){
                setF0(id);
            }
            if(newcondition == "F1"){
                setF1(id);
            }
            if(newcondition == "F2"){
                setF2(id);
            }
            if(newcondition == "F3"){
                setF3(id);
            }
        }
        JDialog d1 = new JDialog(d, "");
        d1.add(new JLabel("   Update patient state successfully!"));
        d1.setSize(200, 100);
        d1.setModal(true);
        d1.setLocationRelativeTo(null);
        d1.setVisible(true);
        d.setVisible(false);
    }//GEN-LAST:event_d2updateMouseClicked
    void setF0(String x){
        String newqr = "Update covid_patient set condition = 'F0' where citizen_id = " + x;
        db.update(newqr);
        newqr = "select citizen_id, condition from covid_patient where related_to = "+ x;
        Object[][] obj = db.getdata(newqr);
        for(int i = 0; i< obj.length; i++){
            if(!"F0".equals(obj[i][1].toString())) setF1(obj[i][0].toString());
        }
    }
    void setF1(String x){
        String newqr = "Update covid_patient set condition = 'F1' where citizen_id = " + x;
        db.update(newqr);
        newqr = "select citizen_id, condition from covid_patient where related_to = "+ x;
        Object[][] obj = db.getdata(newqr);
        for(int i = 0; i< obj.length; i++){
            if(!"F0".equals(obj[i][1].toString()) || !"F1".equals(obj[i][1].toString())) setF2(obj[i][0].toString());
        }
    }
    void setF2(String x){
        String newqr = "Update covid_patient set condition = 'F2' where citizen_id = " + x;
        db.update(newqr);
        newqr = "select citizen_id, condition from covid_patient where related_to = "+ x;
        Object[][] obj = db.getdata(newqr);
        for(int i = 0; i< obj.length; i++){
            if(!"F0".equals(obj[i][1].toString()) || !"F1".equals(obj[i][1].toString())|| !"F2".equals(obj[i][1].toString())) setF3(obj[i][0].toString());
        }
    }
    void setF3(String x){
        String newqr = "Update covid_patient set condition = 'F3' where citizen_id = " + x;
        db.update(newqr);
        newqr = "select citizen_id, condition from covid_patient where related_to = "+ x;
        Object[][] obj = db.getdata(newqr);
        for(int i = 0; i< obj.length; i++){
            if(!"F0".equals(obj[i][1].toString()) || !"F1".equals(obj[i][1].toString())|| !"F2".equals(obj[i][1].toString())|| !"F3".equals(obj[i][1].toString())){
                newqr = "Update covid_patient set condition = null where citizen_id = " + x;
                db.update(newqr);
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField d2id;
    private javax.swing.JButton d2update;
    private javax.swing.JComboBox<String> d3con;
    private javax.swing.JComboBox<String> d3place;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel textlabel;
    // End of variables declaration//GEN-END:variables
}
