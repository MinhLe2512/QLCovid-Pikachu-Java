/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.model.ManagerDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nhonnhon
 */
public class Manager_mainframe extends javax.swing.JFrame {
    Vector<String> Column1 = new Vector<String>();
    Vector<String> Column2 = new Vector<String>();
    DefaultTableModel tbmodel1, tbmodel2;
    DefaultComboBoxModel cbmodel1search, cbmodel1sort, cbmode21search, cbmode21sort;
    ManagerDB db = new ManagerDB();
    int showingTab;
    String queryOfRelated;
    public Manager_mainframe() {
        initComponents();
        showingTab=1;
        this.setLocationRelativeTo(null);
        initUI();
        this.setTitle("Covid Management");
        queryOfRelated = "";
    }
    void initUI() throws SQLException{
    //!!!!!TAB 01!!!!!!!//
        //init checkbox button
        cbid1.setSelected(true);
        cbname1.setSelected(true);
        cbdob1.setSelected(true);
        cbaddress1.setSelected(true);
        cbcondition1.setSelected(true);
        cbplace1.setSelected(true);
        cbrelated1.setSelected(true);
        
        cbid1.setVisible(false);
        cbname1.setVisible(false);
        cbdob1.setVisible(false);
        cbaddress1.setVisible(false);
        cbcondition1.setVisible(false);
        cbplace1.setVisible(false);
        cbrelated1.setVisible(false);
        jLabel7.setVisible(false);
        btnup1.setEnabled(false);
        btndown1.setEnabled(false);
        btncancel1.setEnabled(false);
        btnupdate1.setEnabled(false);
        btnremove1.setEnabled(false);
        
        //init table
        Object[][] data = db.getdata(getSelected1() + " where condition  is not null ");
        tbmodel1 = new DefaultTableModel(data, Column1.toArray()){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        tb1.setModel(tbmodel1);
        Object[] noCol1 = new Object[tbmodel1.getRowCount()];
        for(int i = 0; i<tbmodel1.getRowCount() ; i++) noCol1[i] = i + 1;
        tbmodel1.addColumn("No.", noCol1);
        tb1.setModel(tbmodel1);
        tb1.moveColumn(tb1.getColumnCount()-1, 0);
        
        //init combobox
        cbmodel1search = new DefaultComboBoxModel(Column1);
        cbmodel1sort = new DefaultComboBoxModel(Column1);
        cbsearch1.setModel(cbmodel1search);
        cbsort1.setModel(cbmodel1sort);
        
    //!!!!!TAB 02!!!!!!!//
        tb3.setModel(tbmodel1);
    }
    void resettable1() throws SQLException{
        String newqr = "";
        newqr += getSelected1();
        newqr += getWhere1();
        newqr += getSort1();
        Object[][] data = db.getdata(newqr);
        tbmodel1 = new DefaultTableModel(data, Column1.toArray());
        tb1.setModel(tbmodel1);
        Object[] noCol1 = new Object[tbmodel1.getRowCount()];
        for(int i = 0; i<tbmodel1.getRowCount() ; i++) noCol1[i] = i + 1;
        tbmodel1.addColumn("No.", noCol1);
        tb1.setModel(tbmodel1);
        tb1.moveColumn(tb1.getColumnCount()-1, 0);
    }
    String getcurquery(){
        String newqr = "";
        newqr += getSelected1();
        newqr += getWhere1();
        newqr += getSort1();
        return newqr;
    }
    String getSort1(){
        if(btnsort1.isSelected()){
            String newqr = " order by ";
            newqr += tocolname(cbsearch1.getSelectedItem().toString()) + " ";
            if(cksort1.isSelected()) newqr += "desc ";
            return newqr;
        }
        return "";
    }
    String getWhere1(){
        String newqr = " where condition  is not null ";
        if(!"".equals(queryOfRelated)) {newqr += " and " + queryOfRelated;}
        if(tbsearch1.getText().isEmpty() || tbsearch1.getText().length() ==0) tbsearch1.setText(" 0 ");
        if(btnsearch1.isSelected()){
            newqr += " and ";
            if(tocolname(cbsearch1.getSelectedItem().toString()).equals("full_name")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like N'%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("citizen_address")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like N'%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("treatment_place.treatment_place_name")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like N'%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("date_of_birth")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " like '%" + tbsearch1.getText()+ "%' ";
            else if(tocolname(cbsearch1.getSelectedItem().toString()).equals("condition")) newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " = '" + tbsearch1.getText()+ "' ";
            else  
                try{
                    newqr+= tocolname(cbsearch1.getSelectedItem().toString()) + " = '" + String.valueOf(Integer.valueOf(tbsearch1.getText()))+ "' ";
                }
                catch (NumberFormatException ex){
                    JDialog d = new JDialog(this, "ERROR!");
                    d.add(new JLabel("      ID must be integer!"));
                    d.setSize(200, 100);
                    d.setVisible(true);
                    d.setLocationRelativeTo(null);
                }
        }
        return newqr;
    }
    String getSelected1(){
        String newqr = "select ";
        Column1 = new Vector<String>();
        if(cbid1.isSelected()){ //citizenid
            newqr += " citizen_id, ";
            Column1.add("Citizen ID");
        }
        if(cbname1.isSelected()){ //citizenid
            newqr += " full_name, ";
            Column1.add("Full name");
        }
        if(cbdob1.isSelected()){ //citizenid
            newqr += " date_of_birth, ";
            Column1.add("Date of birth");
        }
        if(cbaddress1.isSelected()){ //citizenid
            newqr += " citizen_address, ";
            Column1.add("Address");
        }
        if(cbcondition1.isSelected()){ //citizenid
            newqr += " condition, ";
            Column1.add("Condition");
        }
        if(cbplace1.isSelected()){
            newqr += " treatment_place_name, ";
            Column1.add("Treatment place");
        }
        if(cbrelated1.isSelected()){ //citizenid
            newqr += " related_to, ";
            Column1.add("Related");
        }
        newqr = newqr.substring(0, newqr.length() - 2);
        if(newqr.equals("selec")) newqr = "select citizen_id";
        newqr += " from covid_patient join treatment_place on covid_patient.treatment_place_id = treatment_place.treatment_place_id ";
        return newqr;
    }
    
    void resettable2(){
        
    }
    
    private String tocolname(String name){
        if(name.equals("Citizen ID")) return "citizen_id";
        else if(name.equals("Full name")) return "full_name";
        else if(name.equals("Date of birth")) return "date_of_birth";
        else if(name.equals("Address")) return "citizen_address";
        else if(name.equals("Condition")) return "condition";
        else if(name.equals("Treatment place")) return "treatment_place.treatment_place_name";
        else if(name.equals("Related")) return "related_to";
        else if(name.equals("Package ID")) return "package_id";
        else if(name.equals("Name")) return "name";
        else if(name.equals("Limit")) return "limit";
        else if(name.equals("Start date")) return "package_start";
        else if(name.equals("End date")) return "package_end";
        else return "price";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedpane = new javax.swing.JTabbedPane();
        panel1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        sc1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnadd1 = new javax.swing.JButton();
        btnupdate1 = new javax.swing.JButton();
        btnremove1 = new javax.swing.JButton();
        btnback1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbsearch1 = new javax.swing.JComboBox<>();
        btnsearchmore = new javax.swing.JButton();
        cbsort1 = new javax.swing.JComboBox<>();
        btnsortmore1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cksort1 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        btnup1 = new javax.swing.JButton();
        btndown1 = new javax.swing.JButton();
        btncancel1 = new javax.swing.JButton();
        btnsort1 = new javax.swing.JToggleButton();
        btnsearch1 = new javax.swing.JToggleButton();
        jLabel7 = new javax.swing.JLabel();
        cbid1 = new javax.swing.JCheckBox();
        cbname1 = new javax.swing.JCheckBox();
        cbaddress1 = new javax.swing.JCheckBox();
        cbrelated1 = new javax.swing.JCheckBox();
        cbplace1 = new javax.swing.JCheckBox();
        cbcondition1 = new javax.swing.JCheckBox();
        cbdob1 = new javax.swing.JCheckBox();
        tbsearch1 = new javax.swing.JTextField();
        panel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        sc2 = new javax.swing.JScrollPane();
        tb3 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnadd2 = new javax.swing.JButton();
        btnupdate2 = new javax.swing.JButton();
        btnremove2 = new javax.swing.JButton();
        btnback3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbsearch2 = new javax.swing.JComboBox<>();
        btnsearchmore2 = new javax.swing.JButton();
        cbsort2 = new javax.swing.JComboBox<>();
        btnsortmore2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cksort2 = new javax.swing.JRadioButton();
        btnsort2 = new javax.swing.JToggleButton();
        btnsearch2 = new javax.swing.JToggleButton();
        jLabel15 = new javax.swing.JLabel();
        tbsearch2 = new javax.swing.JTextField();
        cbid2 = new javax.swing.JCheckBox();
        cbstart2 = new javax.swing.JCheckBox();
        cbend2 = new javax.swing.JCheckBox();
        cbprice2 = new javax.swing.JCheckBox();
        cblimit2 = new javax.swing.JCheckBox();
        cbname2 = new javax.swing.JCheckBox();
        panel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 700));

        tabbedpane.setBackground(new java.awt.Color(51, 51, 51));
        tabbedpane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabbedpane.setForeground(new java.awt.Color(255, 255, 255));
        tabbedpane.setToolTipText("");
        tabbedpane.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabbedpane.setMinimumSize(new java.awt.Dimension(1200, 600));

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        sc1.setBackground(new java.awt.Color(51, 51, 51));
        sc1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sc1.setForeground(new java.awt.Color(255, 255, 255));

        tb1.setBackground(new java.awt.Color(255, 255, 255));
        tb1.setForeground(new java.awt.Color(102, 102, 102));
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }

        ){public boolean isCellEditable(int row, int column){return false;}});
        tb1.setAutoscrolls(false);
        tb1.setCellSelectionEnabled(true);
        tb1.setRequestFocusEnabled(false);
        tb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb1MouseReleased(evt);
            }
        });
        sc1.setViewportView(tb1);

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Covid Patients");

        btnadd1.setBackground(new java.awt.Color(51, 51, 51));
        btnadd1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd1.setForeground(new java.awt.Color(255, 255, 255));
        btnadd1.setText("Add");

        btnupdate1.setBackground(new java.awt.Color(51, 51, 51));
        btnupdate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdate1.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate1.setText("Update");

        btnremove1.setBackground(new java.awt.Color(51, 51, 51));
        btnremove1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnremove1.setForeground(new java.awt.Color(255, 255, 255));
        btnremove1.setText("Remove");

        btnback1.setBackground(new java.awt.Color(51, 51, 51));
        btnback1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback1.setForeground(new java.awt.Color(255, 255, 255));
        btnback1.setText("Reset");
        btnback1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnback1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sc1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnremove1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnback1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnback1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnadd1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                        .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnremove1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Search");

        cbsearch1.setBackground(new java.awt.Color(255, 255, 255));
        cbsearch1.setForeground(new java.awt.Color(102, 102, 102));
        cbsearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnsearchmore.setBackground(new java.awt.Color(204, 204, 204));
        btnsearchmore.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearchmore.setForeground(new java.awt.Color(102, 102, 102));
        btnsearchmore.setText("...");
        btnsearchmore.setToolTipText("");

        cbsort1.setBackground(new java.awt.Color(255, 255, 255));
        cbsort1.setForeground(new java.awt.Color(102, 102, 102));
        cbsort1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnsortmore1.setBackground(new java.awt.Color(204, 204, 204));
        btnsortmore1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsortmore1.setForeground(new java.awt.Color(102, 102, 102));
        btnsortmore1.setText("...");
        btnsortmore1.setToolTipText("");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Sort");

        cksort1.setBackground(new java.awt.Color(0, 255, 255));
        cksort1.setForeground(new java.awt.Color(102, 102, 102));
        cksort1.setText("descesding");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Find related");

        btnup1.setBackground(new java.awt.Color(51, 51, 51));
        btnup1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnup1.setForeground(new java.awt.Color(255, 255, 255));
        btnup1.setText("Up");
        btnup1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnup1MouseClicked(evt);
            }
        });

        btndown1.setBackground(new java.awt.Color(51, 51, 51));
        btndown1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndown1.setForeground(new java.awt.Color(255, 255, 255));
        btndown1.setText("Down");
        btndown1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndown1MouseClicked(evt);
            }
        });

        btncancel1.setBackground(new java.awt.Color(204, 204, 204));
        btncancel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btncancel1.setForeground(new java.awt.Color(102, 102, 102));
        btncancel1.setText("X");
        btncancel1.setToolTipText("");
        btncancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncancel1MouseClicked(evt);
            }
        });

        btnsort1.setBackground(new java.awt.Color(51, 51, 51));
        btnsort1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsort1.setForeground(new java.awt.Color(255, 255, 255));
        btnsort1.setText("Sort");
        btnsort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsort1MouseClicked(evt);
            }
        });

        btnsearch1.setBackground(new java.awt.Color(51, 51, 51));
        btnsearch1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearch1.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch1.setText("Search");
        btnsearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearch1MouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Show columns");

        cbid1.setBackground(new java.awt.Color(0, 255, 255));
        cbid1.setForeground(new java.awt.Color(102, 102, 102));
        cbid1.setText("CitizenID");

        cbname1.setBackground(new java.awt.Color(0, 255, 255));
        cbname1.setForeground(new java.awt.Color(102, 102, 102));
        cbname1.setText("Full name");

        cbaddress1.setBackground(new java.awt.Color(0, 255, 255));
        cbaddress1.setForeground(new java.awt.Color(102, 102, 102));
        cbaddress1.setText("Address");

        cbrelated1.setBackground(new java.awt.Color(0, 255, 255));
        cbrelated1.setForeground(new java.awt.Color(102, 102, 102));
        cbrelated1.setText("Related");

        cbplace1.setBackground(new java.awt.Color(0, 255, 255));
        cbplace1.setForeground(new java.awt.Color(102, 102, 102));
        cbplace1.setText("Treatment place");

        cbcondition1.setBackground(new java.awt.Color(0, 255, 255));
        cbcondition1.setForeground(new java.awt.Color(102, 102, 102));
        cbcondition1.setText("Condition");

        cbdob1.setBackground(new java.awt.Color(0, 255, 255));
        cbdob1.setForeground(new java.awt.Color(102, 102, 102));
        cbdob1.setText("Date of birth");

        tbsearch1.setBackground(new java.awt.Color(255, 255, 255));
        tbsearch1.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnup1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndown1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tbsearch1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbaddress1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbname1)
                                            .addComponent(cbdob1)
                                            .addComponent(cbid1))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbrelated1)
                                            .addComponent(cbplace1)
                                            .addComponent(cbcondition1)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbsort1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsort1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsortmore1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cksort1))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(cbsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnsearchmore, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch1)
                    .addComponent(btnsearchmore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cksort1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsort1)
                    .addComponent(btnsortmore1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnup1)
                    .addComponent(btndown1)
                    .addComponent(btncancel1))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbid1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbname1)
                                .addGap(26, 26, 26))
                            .addComponent(cbdob1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbcondition1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbplace1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbrelated1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbaddress1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedpane.addTab("Covid patients", panel1);

        panel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        sc2.setBackground(new java.awt.Color(51, 51, 51));
        sc2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sc2.setForeground(new java.awt.Color(255, 255, 255));

        tb3.setBackground(new java.awt.Color(255, 255, 255));
        tb3.setForeground(new java.awt.Color(102, 102, 102));
        tb3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }

        ){public boolean isCellEditable(int row, int column){return false;}});
        tb3.setAutoscrolls(false);
        tb3.setCellSelectionEnabled(true);
        tb3.setRequestFocusEnabled(false);
        sc2.setViewportView(tb3);

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Necessary package");

        btnadd2.setBackground(new java.awt.Color(51, 51, 51));
        btnadd2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd2.setForeground(new java.awt.Color(255, 255, 255));
        btnadd2.setText("Add");

        btnupdate2.setBackground(new java.awt.Color(51, 51, 51));
        btnupdate2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdate2.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate2.setText("Update");

        btnremove2.setBackground(new java.awt.Color(51, 51, 51));
        btnremove2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnremove2.setForeground(new java.awt.Color(255, 255, 255));
        btnremove2.setText("Remove");

        btnback3.setBackground(new java.awt.Color(51, 51, 51));
        btnback3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnback3.setForeground(new java.awt.Color(255, 255, 255));
        btnback3.setText("Reset");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnremove2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnupdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnback3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addGap(35, 35, 35))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnback3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnadd2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                        .addComponent(btnupdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnremove2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 255, 255));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Search");

        cbsearch2.setBackground(new java.awt.Color(255, 255, 255));
        cbsearch2.setForeground(new java.awt.Color(102, 102, 102));
        cbsearch2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnsearchmore2.setBackground(new java.awt.Color(204, 204, 204));
        btnsearchmore2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearchmore2.setForeground(new java.awt.Color(102, 102, 102));
        btnsearchmore2.setText("...");
        btnsearchmore2.setToolTipText("");

        cbsort2.setBackground(new java.awt.Color(255, 255, 255));
        cbsort2.setForeground(new java.awt.Color(102, 102, 102));
        cbsort2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnsortmore2.setBackground(new java.awt.Color(204, 204, 204));
        btnsortmore2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsortmore2.setForeground(new java.awt.Color(102, 102, 102));
        btnsortmore2.setText("...");
        btnsortmore2.setToolTipText("");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Sort");

        cksort2.setBackground(new java.awt.Color(0, 255, 255));
        cksort2.setForeground(new java.awt.Color(102, 102, 102));
        cksort2.setText("descesding");

        btnsort2.setBackground(new java.awt.Color(51, 51, 51));
        btnsort2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsort2.setForeground(new java.awt.Color(255, 255, 255));
        btnsort2.setText("Sort");

        btnsearch2.setBackground(new java.awt.Color(51, 51, 51));
        btnsearch2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearch2.setForeground(new java.awt.Color(255, 255, 255));
        btnsearch2.setText("Search");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Show columns");

        tbsearch2.setBackground(new java.awt.Color(255, 255, 255));
        tbsearch2.setForeground(new java.awt.Color(102, 102, 102));

        cbid2.setBackground(new java.awt.Color(0, 255, 255));
        cbid2.setForeground(new java.awt.Color(102, 102, 102));
        cbid2.setText("Package ID");

        cbstart2.setBackground(new java.awt.Color(0, 255, 255));
        cbstart2.setForeground(new java.awt.Color(102, 102, 102));
        cbstart2.setText("Start date");

        cbend2.setBackground(new java.awt.Color(0, 255, 255));
        cbend2.setForeground(new java.awt.Color(102, 102, 102));
        cbend2.setText("End date");

        cbprice2.setBackground(new java.awt.Color(0, 255, 255));
        cbprice2.setForeground(new java.awt.Color(102, 102, 102));
        cbprice2.setText("Price");

        cblimit2.setBackground(new java.awt.Color(0, 255, 255));
        cblimit2.setForeground(new java.awt.Color(102, 102, 102));
        cblimit2.setText("Limit");

        cbname2.setBackground(new java.awt.Color(0, 255, 255));
        cbname2.setForeground(new java.awt.Color(102, 102, 102));
        cbname2.setText("Package name");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tbsearch2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cbsort2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsort2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsortmore2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cblimit2)
                                .addComponent(cbid2)
                                .addComponent(cbname2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbstart2)
                                .addComponent(cbend2)
                                .addComponent(cbprice2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cksort2))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(cbsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnsearchmore2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch2)
                    .addComponent(btnsearchmore2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cksort2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbsort2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsort2)
                    .addComponent(btnsortmore2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(cbstart2)
                            .addGap(52, 52, 52))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(cbend2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbprice2)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cblimit2)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(cbid2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbname2)
                            .addGap(26, 26, 26))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedpane.addTab("Necessary package", panel4);

        panel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setBackground(new java.awt.Color(51, 51, 51));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("System statistics");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(765, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(35, 35, 35))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel16)
                .addGap(496, 496, 496))
        );

        jPanel12.setBackground(new java.awt.Color(0, 255, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedpane.addTab("Covid patients", panel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseClicked
        resetbtn1();
        //System.out.println(tb1.getValueAt(tb1.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tb1MouseClicked

    private void btnup1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnup1MouseClicked
        if(btnup1.isEnabled()){
            String upID = tb1.getValueAt(tb1.getSelectedRow(), 7).toString();
            this.queryOfRelated = " citizen_id = " + upID +" ";
            resettable1();
            resetbtn1();
        }
    }//GEN-LAST:event_btnup1MouseClicked

    private void btndown1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndown1MouseClicked
        if(btndown1.isEnabled()){
            String downID = tb1.getValueAt(tb1.getSelectedRow(), 1).toString();
            this.queryOfRelated = " related_to = " + downID+ " ";
            resettable1();
            resetbtn1();
        }
    }//GEN-LAST:event_btndown1MouseClicked

    private void btncancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancel1MouseClicked
        if(btncancel1.isEnabled()){
            this.queryOfRelated = "";
            resettable1();
            resetbtn1();
        }
    }//GEN-LAST:event_btncancel1MouseClicked

    private void tb1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb1MouseReleased
        resetbtn1();
    }//GEN-LAST:event_tb1MouseReleased

    private void btnsort1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsort1MouseClicked
        if(btnsort1.isEnabled()){
            resettable1();
            resetbtn1();
        }
    }//GEN-LAST:event_btnsort1MouseClicked

    private void btnsearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearch1MouseClicked
        if(btnsearch1.isEnabled()){
            resettable1();
            resetbtn1();
        }
    }//GEN-LAST:event_btnsearch1MouseClicked

    private void btnback1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnback1KeyPressed
        if(btnback1.isEnabled()){
            this.queryOfRelated="";
            btnsearch1.setSelected(false);
            btnsort1.setSelected(false);
            resettable1();
            resetbtn1();
        }
    }//GEN-LAST:event_btnback1KeyPressed

    void resetbtn1(){
        btnup1.setEnabled(false);
        btndown1.setEnabled(false);
        btncancel1.setEnabled(false);
        btnupdate1.setEnabled(false);
        btnremove1.setEnabled(false);
        //for updown, remove, update
        if(tb1.getSelectedRow()!=-1){
            if(!"F0".equals(tb1.getValueAt(tb1.getSelectedRow(), 5).toString())) btnup1.setEnabled(true);
            if(!"F3".equals(tb1.getValueAt(tb1.getSelectedRow(), 5).toString())) btndown1.setEnabled(true);
            btnupdate1.setEnabled(true);
            btnremove1.setEnabled(true);
        }
        if(!this.queryOfRelated.equals("")) btncancel1.setEnabled(true);
    }
    
    //</editor-fold>
    
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
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager_mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager_mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btnadd2;
    private javax.swing.JButton btnback1;
    private javax.swing.JButton btnback3;
    private javax.swing.JButton btncancel1;
    private javax.swing.JButton btndown1;
    private javax.swing.JButton btnremove1;
    private javax.swing.JButton btnremove2;
    private javax.swing.JToggleButton btnsearch1;
    private javax.swing.JToggleButton btnsearch2;
    private javax.swing.JButton btnsearchmore;
    private javax.swing.JButton btnsearchmore2;
    private javax.swing.JToggleButton btnsort1;
    private javax.swing.JToggleButton btnsort2;
    private javax.swing.JButton btnsortmore1;
    private javax.swing.JButton btnsortmore2;
    private javax.swing.JButton btnup1;
    private javax.swing.JButton btnupdate1;
    private javax.swing.JButton btnupdate2;
    private javax.swing.JCheckBox cbaddress1;
    private javax.swing.JCheckBox cbcondition1;
    private javax.swing.JCheckBox cbdob1;
    private javax.swing.JCheckBox cbend2;
    private javax.swing.JCheckBox cbid1;
    private javax.swing.JCheckBox cbid2;
    private javax.swing.JCheckBox cblimit2;
    private javax.swing.JCheckBox cbname1;
    private javax.swing.JCheckBox cbname2;
    private javax.swing.JCheckBox cbplace1;
    private javax.swing.JCheckBox cbprice2;
    private javax.swing.JCheckBox cbrelated1;
    private javax.swing.JComboBox<String> cbsearch1;
    private javax.swing.JComboBox<String> cbsearch2;
    private javax.swing.JComboBox<String> cbsort1;
    private javax.swing.JComboBox<String> cbsort2;
    private javax.swing.JCheckBox cbstart2;
    private javax.swing.JRadioButton cksort1;
    private javax.swing.JRadioButton cksort2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel6;
    private javax.swing.JScrollPane sc1;
    private javax.swing.JScrollPane sc2;
    private javax.swing.JTabbedPane tabbedpane;
    private javax.swing.JTable tb1;
    private javax.swing.JTable tb3;
    private javax.swing.JTextField tbsearch1;
    private javax.swing.JTextField tbsearch2;
    // End of variables declaration//GEN-END:variables
}