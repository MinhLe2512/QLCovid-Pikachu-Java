/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.example.qlcovid.jframe;

import com.example.qlcovid.model.ManagerDB;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author nhonnhon
 */
public class ManagerUI extends javax.swing.JFrame {
    int showTab = 1;
    static ManagerDB db;
    static DefaultTableModel tbmodel_1;
    static DefaultComboBoxModel cbmodel_1_1;
    static DefaultComboBoxModel cbmodel_1_2;
    static DefaultTableModel tbmodel_2;
    static DefaultComboBoxModel cbmodel_2_1;
    static DefaultComboBoxModel cbmodel_2_2;
    static Hashtable<String, String> toname;
    public ManagerUI() throws SQLException {
        initComponents();
        db=new ManagerDB();
        init();
    }
    private void init() throws SQLException{
        String [] columnNames1 = {"Citizen ID", "Fullname", "Date of birth", "Address", "Condition", "Treatment place", "Related"};
        String [] columnNames2 = {"Package ID", "Name", "Limit", "Start date", "End date", "Price"};
        try {
            this.tbmodel_1 = new DefaultTableModel(db.getdata(getquery()), columnNames1){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
        } catch (SQLException ex) {
            Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cbmodel_1_1 = new DefaultComboBoxModel(columnNames1);
        this.cbmodel_1_2 = new DefaultComboBoxModel(columnNames1);
        
        Object[] noCol1 = new Object[tbmodel_1.getRowCount()];
        for(int i = 0; i<tbmodel_1.getRowCount() ; i++) noCol1[i] = i + 1;
        tbmodel_1.addColumn("No.", noCol1);
        jtb_1.setModel(tbmodel_1);
        jtb_1.moveColumn(jtb_1.getColumnCount()-1, 0);
        
        cb_1_search.setModel(cbmodel_1_1);
        cb_1_sort.setModel(cbmodel_1_2);
        try {
            showTab = 2;
            this.tbmodel_2 = new DefaultTableModel(db.getdata(getquery()), columnNames2){
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            showTab = 1;
        } catch (SQLException ex) {
            Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cbmodel_2_1 = new DefaultComboBoxModel(columnNames2);
        this.cbmodel_2_2 = new DefaultComboBoxModel(columnNames2);
        
        
        Object[]noCol2 = new Object[tbmodel_2.getRowCount()];
        for(int i = 0; i<tbmodel_2.getRowCount() ; i++) noCol2[i] = i + 1;
        tbmodel_2.addColumn("No.", noCol2);
        jtb_2.setModel(tbmodel_2);
        jtb_2.moveColumn(jtb_2.getColumnCount()-1, 0);
        
        cb_2_search.setModel(cbmodel_2_1);
        cb_2_sort.setModel(cbmodel_2_2);
        this.jpn_main_stat.setVisible(false);
        this.jpn_main_covidmg.setVisible(false);
        this.jpn_main_necitems.setVisible(false);
        this.jpn_main_covidmg.setVisible(true);
        this.setSize(1200,600);
        this.setLocationRelativeTo(null);
        
        initbtn();
    }
    
    private void initbtn() {
        btn_1_update.setEnabled(false);
        btn_1_remove.setEnabled(false);
        btn_1_view_up.setEnabled(false);
        btn_1_view_down.setEnabled(false);
        btn_1_view_cancel.setEnabled(false);
        btn_2_update.setEnabled(false);
        btn_2_remove.setEnabled(false);
    }
    
    private void changecard() {
        this.jpn_main_stat.setVisible(false);
        this.jpn_main_covidmg.setVisible(false);
        this.jpn_main_necitems.setVisible(false);
        switch(showTab){
            case 1:
                this.jpn_main_covidmg.setVisible(true);
                break;
            case 2:
                this.jpn_main_necitems.setVisible(true);
                break;
            case 3:
                this.jpn_main_stat.setVisible(true);
                break;
        }
        this.setVisible(true);
    }
    private void resettable() {
        resettable(getquery());
    }
    private void resettable(String query) {
        String [] columnNames1 = {"Citizen ID", "Fullname", "Date of birth", "Address", "Condition", "Treatment place", "Related"};
        String [] columnNames2 = {"Package ID", "Name", "Limit", "Start date", "End date", "Price"};
        if(showTab==1) {
            try {
                this.tbmodel_1 = new DefaultTableModel(db.getdata(query), columnNames1){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            } catch (SQLException ex) {
                Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] noCol1 = new Object[tbmodel_1.getRowCount()];
            for(int i = 0; i<tbmodel_1.getRowCount() ; i++) noCol1[i] = i + 1;
            tbmodel_1.addColumn("No.", noCol1);
            jtb_1.setModel(tbmodel_1);
            jtb_1.moveColumn(jtb_1.getColumnCount()-1, 0);
        }
        else if(showTab ==2){
            try {
                this.tbmodel_2 = new DefaultTableModel(db.getdata(query), columnNames2){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
            } catch (SQLException ex) {
                Logger.getLogger(ManagerUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[]noCol2 = new Object[tbmodel_2.getRowCount()];
            for(int i = 0; i<tbmodel_2.getRowCount() ; i++) noCol2[i] = i + 1;
            tbmodel_2.addColumn("No.", noCol2);
            jtb_2.setModel(tbmodel_2);
            jtb_2.moveColumn(jtb_2.getColumnCount()-1, 0);
        }
    }

    private String getquery() {
        String query = "";
        if(showTab==1) {
            query = "select citizen_id, full_name, date_of_birth, concat(ward_name, ', ' , district_name, ', ', province_name) as citizen_address, condition, treatment_place_name, related_to from covid_patient join ward on ward.ward_id = covid_patient.citizen_address join district_has_ward on ward.ward_id = district_has_ward.ward_id join district on district.district_id = district_has_ward.district_id join province_has_district on province_has_district.district_id = district.district_id join province on province.province_id = province_has_district.province_id join treatment_place on treatment_place.treatment_place_id = covid_patient.treatment_place_id ";
            if(btn_1_search.isSelected()){
                query += " where covid_patient.";
                query += tocolname(cb_1_search.getSelectedItem().toString());
                if("Fullname".equals(cb_1_search.getSelectedItem().toString())||"Date of birth".equals(cb_1_search.getSelectedItem().toString())){
                    query += " like '%";
                    query += jt_1.getText();
                    query += "%' ";
                }
                else{
                    query += " = '";
                    query += jt_1.getText();
                    query += "' ";
                }
                query += " and covid_patient.condition is not null ";
            }
            else{
                query += " where covid_patient.condition is not null ";
            }
            if(btn_1_sort.isSelected()){
                query += " order by covid_patient.";
                query += tocolname(cb_1_sort.getSelectedItem().toString());
                if(ckb_1_des.isSelected()) query += " desc";
            }
            
        }
        else if(showTab == 2){
            query = "select * from dbo.package ";
            if(btn_2_search.isSelected()){
                query += " where ";
                query += tocolname(cb_2_search.getSelectedItem().toString());
                if("Name".equals(cb_2_search.getSelectedItem().toString())||"Start date".equals(cb_2_search.getSelectedItem().toString())||"Start date".equals(cb_2_search.getSelectedItem().toString())){
                    query += " like '%";
                    query += jt_2.getText();
                    query += "%' ";
                }
                else{
                    query += " = '";
                    query += jt_2.getText();
                    query += "' ";
                }
            }
            if(btn_2_sort.isSelected()){
                query += "order by ";
                query += tocolname(cb_2_sort.getSelectedItem().toString());
                if(ckb_2_des.isSelected()) query += " desc";
            }
        }
        return query;
    }
    
    private String tocolname(String name){
        if(name.equals("Citizen ID")) return "citizen_id";
        else if(name.equals("Fullname")) return "full_name";
        else if(name.equals("Date of birth")) return "date_of_birth";
        else if(name.equals("Address")) return "citizen_address";
        else if(name.equals("Condition")) return "condition";
        else if(name.equals("Treatment place")) return "treatment_place_id";
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

        jpn_main_covidmg = new javax.swing.JPanel();
        jpn_left = new javax.swing.JPanel();
        jlb_1_tabname = new javax.swing.JLabel();
        jpn_table = new javax.swing.JPanel();
        jsc_1 = new javax.swing.JScrollPane();
        jtb_1 = new javax.swing.JTable();
        btn_1_add = new javax.swing.JButton();
        btn_1_update = new javax.swing.JButton();
        btn_1_remove = new javax.swing.JButton();
        btn_1_totab3 = new javax.swing.JButton();
        btn_1_totab2 = new javax.swing.JButton();
        jpn_right1 = new javax.swing.JPanel();
        jpn_right = new javax.swing.JPanel();
        jlb_1_search = new javax.swing.JLabel();
        cb_1_search = new javax.swing.JComboBox<>();
        jt_1 = new javax.swing.JTextField();
        btn_1_searchmore = new javax.swing.JButton();
        jlb_1_sort = new javax.swing.JLabel();
        cb_1_sort = new javax.swing.JComboBox<>();
        btn_1_sortmore = new javax.swing.JButton();
        jlb_1_view = new javax.swing.JLabel();
        btn_1_view_cancel = new javax.swing.JButton();
        btn_1_view_down = new javax.swing.JButton();
        btn_1_view_up = new javax.swing.JButton();
        btn_1_search = new javax.swing.JToggleButton();
        btn_1_sort = new javax.swing.JToggleButton();
        ckb_1_des = new javax.swing.JCheckBox();
        jpn_main_necitems = new javax.swing.JPanel();
        jpn_left1 = new javax.swing.JPanel();
        jlb_2_tabname = new javax.swing.JLabel();
        jpn_table1 = new javax.swing.JPanel();
        jsc_2 = new javax.swing.JScrollPane();
        jtb_2 = new javax.swing.JTable();
        btn_2_add = new javax.swing.JButton();
        btn_2_update = new javax.swing.JButton();
        btn_2_remove = new javax.swing.JButton();
        btn_2_totab3 = new javax.swing.JButton();
        btn_2_totab1 = new javax.swing.JButton();
        jpn_right2 = new javax.swing.JPanel();
        jpn_right3 = new javax.swing.JPanel();
        jlb_2_search = new javax.swing.JLabel();
        cb_2_search = new javax.swing.JComboBox<>();
        jt_2 = new javax.swing.JTextField();
        btn_2_searchmore = new javax.swing.JButton();
        jlb_2_sort = new javax.swing.JLabel();
        cb_2_sort = new javax.swing.JComboBox<>();
        btn_2_sortmore = new javax.swing.JButton();
        btn_2_sort = new javax.swing.JToggleButton();
        btn_2_search = new javax.swing.JToggleButton();
        ckb_2_des = new javax.swing.JCheckBox();
        jpn_main_stat = new javax.swing.JPanel();
        jpn_left2 = new javax.swing.JPanel();
        jlb_3_tabname = new javax.swing.JLabel();
        btn_3_totab2 = new javax.swing.JButton();
        btn_3_totab1 = new javax.swing.JButton();
        jpn_right4 = new javax.swing.JPanel();
        jpn_right5 = new javax.swing.JPanel();
        jlb_3_realtime = new javax.swing.JLabel();
        jmb1 = new javax.swing.JMenuBar();
        jmn1 = new javax.swing.JMenu();
        jmi1 = new javax.swing.JMenuItem();
        jmi2 = new javax.swing.JMenuItem();
        jmn2 = new javax.swing.JMenu();
        jmn3 = new javax.swing.JMenu();
        jmi4 = new javax.swing.JMenuItem();
        jmi5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Covid Management");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(3000, 1800));
        setMinimumSize(new java.awt.Dimension(1040, 440));
        setPreferredSize(new java.awt.Dimension(1600, 900));
        getContentPane().setLayout(new java.awt.CardLayout());

        jpn_main_covidmg.setBackground(new java.awt.Color(204, 204, 204));

        jpn_left.setBackground(new java.awt.Color(255, 255, 255));

        jlb_1_tabname.setBackground(new java.awt.Color(204, 204, 204));
        jlb_1_tabname.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jlb_1_tabname.setForeground(new java.awt.Color(102, 102, 102));
        jlb_1_tabname.setText("Covid Management");

        jsc_1.setBackground(new java.awt.Color(255, 255, 255));
        jsc_1.setForeground(new java.awt.Color(51, 51, 51));
        jsc_1.setAutoscrolls(true);
        jsc_1.setPreferredSize(new java.awt.Dimension(452, 200));

        jtb_1.setBackground(new java.awt.Color(255, 255, 255));
        jtb_1.setForeground(new java.awt.Color(102, 102, 102));
        jtb_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }

        )
        {public boolean isCellEditable(int row, int column){return true;}}
    );
    jtb_1.setDragEnabled(true);
    jtb_1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jtb_1MouseClicked(evt);
        }
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jtb_1MouseClicked(evt);
        }
    });
    jsc_1.setViewportView(jtb_1);

    javax.swing.GroupLayout jpn_tableLayout = new javax.swing.GroupLayout(jpn_table);
    jpn_table.setLayout(jpn_tableLayout);
    jpn_tableLayout.setHorizontalGroup(
        jpn_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jsc_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jpn_tableLayout.setVerticalGroup(
        jpn_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jsc_1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
    );

    btn_1_add.setBackground(new java.awt.Color(51, 51, 51));
    btn_1_add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btn_1_add.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_add.setText("Add");
    btn_1_add.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            btn_1_addMouseReleased(evt);
        }
    });

    btn_1_update.setBackground(new java.awt.Color(51, 51, 51));
    btn_1_update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btn_1_update.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_update.setText("Update");

    btn_1_remove.setBackground(new java.awt.Color(51, 51, 51));
    btn_1_remove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btn_1_remove.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_remove.setText("Remove");

    btn_1_totab3.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_totab3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btn_1_totab3.setForeground(new java.awt.Color(255, 255, 255));
    btn_1_totab3.setText("Statistical system");
    btn_1_totab3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_totab3MouseClicked(evt);
        }
    });

    btn_1_totab2.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_totab2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btn_1_totab2.setForeground(new java.awt.Color(255, 255, 255));
    btn_1_totab2.setText("Neccessary items");
    btn_1_totab2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_totab2MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jpn_leftLayout = new javax.swing.GroupLayout(jpn_left);
    jpn_left.setLayout(jpn_leftLayout);
    jpn_leftLayout.setHorizontalGroup(
        jpn_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_leftLayout.createSequentialGroup()
            .addGap(63, 63, 63)
            .addGroup(jpn_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_leftLayout.createSequentialGroup()
                    .addComponent(btn_1_totab2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_1_totab3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                    .addComponent(jlb_1_tabname))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_leftLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btn_1_remove)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_1_update)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_1_add))
                .addComponent(jpn_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(25, 25, 25))
    );
    jpn_leftLayout.setVerticalGroup(
        jpn_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_leftLayout.createSequentialGroup()
            .addGroup(jpn_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_leftLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jlb_1_tabname))
                .addGroup(jpn_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_1_totab2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_1_totab3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jpn_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_1_add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_1_update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_1_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(18, Short.MAX_VALUE))
    );

    jpn_right1.setBackground(new java.awt.Color(51, 51, 51));

    jpn_right.setBackground(new java.awt.Color(204, 204, 204));

    jlb_1_search.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jlb_1_search.setForeground(new java.awt.Color(102, 102, 102));
    jlb_1_search.setText("Search");

    cb_1_search.setBackground(new java.awt.Color(255, 255, 255));
    cb_1_search.setForeground(new java.awt.Color(102, 102, 102));
    cb_1_search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cb_1_search.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            cb_1_searchMouseClicked(evt);
        }
        public void mousePressed(java.awt.event.MouseEvent evt) {
            cb_1_searchMouseClicked(evt);
        }
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            cb_1_searchMouseClicked(evt);
        }
    });

    jt_1.setBackground(new java.awt.Color(255, 255, 255));
    jt_1.setForeground(new java.awt.Color(102, 102, 102));
    jt_1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jt_1MouseClicked(evt);
        }
    });

    btn_1_searchmore.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_searchmore.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_searchmore.setText("...");
    btn_1_searchmore.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_searchmoreMouseClicked(evt);
        }
    });

    jlb_1_sort.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jlb_1_sort.setForeground(new java.awt.Color(102, 102, 102));
    jlb_1_sort.setText("Sort");

    cb_1_sort.setBackground(new java.awt.Color(255, 255, 255));
    cb_1_sort.setForeground(new java.awt.Color(102, 102, 102));
    cb_1_sort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cb_1_sort.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            cb_1_sortMouseClicked(evt);
        }
        public void mousePressed(java.awt.event.MouseEvent evt) {
            cb_1_sortMouseClicked(evt);
        }
    });

    btn_1_sortmore.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_sortmore.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_sortmore.setText("jButton9");
    btn_1_sortmore.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_sortmoreMouseClicked(evt);
        }
    });

    jlb_1_view.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jlb_1_view.setForeground(new java.awt.Color(102, 102, 102));
    jlb_1_view.setText("View related people");

    btn_1_view_cancel.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_view_cancel.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_view_cancel.setText("X");
    btn_1_view_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_view_cancelMouseReleased(evt);
        }
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            btn_1_view_cancelMouseReleased(evt);
        }
    });

    btn_1_view_down.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_view_down.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_view_down.setText("Down");
    btn_1_view_down.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            btn_1_view_downMouseReleased(evt);
        }
    });

    btn_1_view_up.setBackground(new java.awt.Color(102, 102, 102));
    btn_1_view_up.setForeground(new java.awt.Color(204, 204, 204));
    btn_1_view_up.setText("Up");
    btn_1_view_up.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            btn_1_view_upMouseReleased(evt);
        }
    });

    btn_1_search.setText("Search");
    btn_1_search.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_searchMouseClicked(evt);
        }
    });

    btn_1_sort.setText("Sort");
    btn_1_sort.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_1_sortMouseClicked(evt);
        }
    });

    ckb_1_des.setBackground(new java.awt.Color(204, 204, 204));
    ckb_1_des.setForeground(new java.awt.Color(102, 102, 102));
    ckb_1_des.setText("descending");
    ckb_1_des.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            ckb_1_desMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jpn_rightLayout = new javax.swing.GroupLayout(jpn_right);
    jpn_right.setLayout(jpn_rightLayout);
    jpn_rightLayout.setHorizontalGroup(
        jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_rightLayout.createSequentialGroup()
            .addContainerGap(18, Short.MAX_VALUE)
            .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jlb_1_search)
                .addComponent(jt_1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpn_rightLayout.createSequentialGroup()
                    .addComponent(cb_1_search, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_1_search, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_1_searchmore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpn_rightLayout.createSequentialGroup()
                        .addComponent(jlb_1_sort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckb_1_des))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_rightLayout.createSequentialGroup()
                        .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpn_rightLayout.createSequentialGroup()
                                .addComponent(cb_1_sort, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_1_sort, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_rightLayout.createSequentialGroup()
                                .addComponent(jlb_1_view)
                                .addGap(49, 49, 49)))
                        .addComponent(btn_1_sortmore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpn_rightLayout.createSequentialGroup()
                        .addComponent(btn_1_view_up, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_1_view_down, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btn_1_view_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(19, Short.MAX_VALUE))
    );
    jpn_rightLayout.setVerticalGroup(
        jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_rightLayout.createSequentialGroup()
            .addGap(33, 33, 33)
            .addComponent(jlb_1_search)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jt_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_1_searchmore)
                .addComponent(cb_1_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_1_search))
            .addGap(18, 18, 18)
            .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlb_1_sort)
                .addComponent(ckb_1_des))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cb_1_sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_1_sortmore)
                .addComponent(btn_1_sort))
            .addGap(26, 26, 26)
            .addComponent(jlb_1_view)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_1_view_up)
                .addComponent(btn_1_view_down)
                .addComponent(btn_1_view_cancel))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jpn_right1Layout = new javax.swing.GroupLayout(jpn_right1);
    jpn_right1.setLayout(jpn_right1Layout);
    jpn_right1Layout.setHorizontalGroup(
        jpn_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_right1Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jpn_right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jpn_right1Layout.setVerticalGroup(
        jpn_right1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jpn_right, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jpn_main_covidmgLayout = new javax.swing.GroupLayout(jpn_main_covidmg);
    jpn_main_covidmg.setLayout(jpn_main_covidmgLayout);
    jpn_main_covidmgLayout.setHorizontalGroup(
        jpn_main_covidmgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_main_covidmgLayout.createSequentialGroup()
            .addComponent(jpn_left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jpn_right1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jpn_main_covidmgLayout.setVerticalGroup(
        jpn_main_covidmgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jpn_left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jpn_right1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    getContentPane().add(jpn_main_covidmg, "card2");

    jpn_main_necitems.setBackground(new java.awt.Color(204, 204, 204));

    jpn_left1.setBackground(new java.awt.Color(255, 255, 255));

    jlb_2_tabname.setBackground(new java.awt.Color(204, 204, 204));
    jlb_2_tabname.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
    jlb_2_tabname.setForeground(new java.awt.Color(102, 102, 102));
    jlb_2_tabname.setText("Necessary items");

    jsc_2.setBackground(new java.awt.Color(255, 255, 255));
    jsc_2.setForeground(new java.awt.Color(51, 51, 51));
    jsc_2.setAutoscrolls(true);
    jsc_2.setPreferredSize(new java.awt.Dimension(452, 200));

    jtb_2.setBackground(new java.awt.Color(255, 255, 255));
    jtb_2.setForeground(new java.awt.Color(102, 102, 102));
    jtb_2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    )
    {public boolean isCellEditable(int row, int column){return false;}}
    );
    jtb_2.setDragEnabled(true);
    jsc_2.setViewportView(jtb_2);

    javax.swing.GroupLayout jpn_table1Layout = new javax.swing.GroupLayout(jpn_table1);
    jpn_table1.setLayout(jpn_table1Layout);
    jpn_table1Layout.setHorizontalGroup(
        jpn_table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jsc_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jpn_table1Layout.setVerticalGroup(
        jpn_table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jsc_2, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
    );

    btn_2_add.setBackground(new java.awt.Color(51, 51, 51));
    btn_2_add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btn_2_add.setForeground(new java.awt.Color(204, 204, 204));
    btn_2_add.setText("Add");

    btn_2_update.setBackground(new java.awt.Color(51, 51, 51));
    btn_2_update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btn_2_update.setForeground(new java.awt.Color(204, 204, 204));
    btn_2_update.setText("Update");

    btn_2_remove.setBackground(new java.awt.Color(51, 51, 51));
    btn_2_remove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btn_2_remove.setForeground(new java.awt.Color(204, 204, 204));
    btn_2_remove.setText("Remove");

    btn_2_totab3.setBackground(new java.awt.Color(102, 102, 102));
    btn_2_totab3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btn_2_totab3.setForeground(new java.awt.Color(255, 255, 255));
    btn_2_totab3.setText("Statistical system");
    btn_2_totab3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_2_totab3MouseClicked(evt);
        }
    });

    btn_2_totab1.setBackground(new java.awt.Color(102, 102, 102));
    btn_2_totab1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btn_2_totab1.setForeground(new java.awt.Color(255, 255, 255));
    btn_2_totab1.setText("Covid management");
    btn_2_totab1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_2_totab1MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jpn_left1Layout = new javax.swing.GroupLayout(jpn_left1);
    jpn_left1.setLayout(jpn_left1Layout);
    jpn_left1Layout.setHorizontalGroup(
        jpn_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_left1Layout.createSequentialGroup()
            .addGap(63, 63, 63)
            .addGroup(jpn_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_left1Layout.createSequentialGroup()
                    .addComponent(btn_2_totab1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_2_totab3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                    .addComponent(jlb_2_tabname))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_left1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btn_2_remove)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_2_update)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_2_add))
                .addComponent(jpn_table1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(25, 25, 25))
    );
    jpn_left1Layout.setVerticalGroup(
        jpn_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_left1Layout.createSequentialGroup()
            .addGroup(jpn_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_left1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jlb_2_tabname))
                .addGroup(jpn_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_2_totab1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_2_totab3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jpn_table1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_2_add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_2_update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_2_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(18, Short.MAX_VALUE))
    );

    jpn_right2.setBackground(new java.awt.Color(51, 51, 51));

    jpn_right3.setBackground(new java.awt.Color(204, 204, 204));

    jlb_2_search.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jlb_2_search.setForeground(new java.awt.Color(102, 102, 102));
    jlb_2_search.setText("Search");

    cb_2_search.setBackground(new java.awt.Color(255, 255, 255));
    cb_2_search.setForeground(new java.awt.Color(102, 102, 102));
    cb_2_search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cb_2_search.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            cb_2_searchMouseClicked(evt);
        }
        public void mousePressed(java.awt.event.MouseEvent evt) {
            cb_2_searchMouseClicked(evt);
        }
    });

    jt_2.setBackground(new java.awt.Color(255, 255, 255));
    jt_2.setForeground(new java.awt.Color(102, 102, 102));
    jt_2.setAutoscrolls(false);
    jt_2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jt_2MouseClicked(evt);
        }
    });

    btn_2_searchmore.setBackground(new java.awt.Color(102, 102, 102));
    btn_2_searchmore.setForeground(new java.awt.Color(204, 204, 204));
    btn_2_searchmore.setText("...");
    btn_2_searchmore.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_2_searchmoreMouseClicked(evt);
        }
    });

    jlb_2_sort.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jlb_2_sort.setForeground(new java.awt.Color(102, 102, 102));
    jlb_2_sort.setText("Sort");

    cb_2_sort.setBackground(new java.awt.Color(255, 255, 255));
    cb_2_sort.setForeground(new java.awt.Color(102, 102, 102));
    cb_2_sort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cb_2_sort.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            cb_2_sortMouseClicked(evt);
        }
        public void mousePressed(java.awt.event.MouseEvent evt) {
            cb_2_sortMouseClicked(evt);
        }
    });

    btn_2_sortmore.setBackground(new java.awt.Color(102, 102, 102));
    btn_2_sortmore.setForeground(new java.awt.Color(204, 204, 204));
    btn_2_sortmore.setText("jButton9");
    btn_2_sortmore.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_2_sortmoreMouseClicked(evt);
        }
    });

    btn_2_sort.setText("Sort");
    btn_2_sort.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_2_sortMouseClicked(evt);
        }
    });

    btn_2_search.setText("Search");
    btn_2_search.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_2_searchMouseClicked(evt);
        }
    });

    ckb_2_des.setBackground(new java.awt.Color(204, 204, 204));
    ckb_2_des.setForeground(new java.awt.Color(102, 102, 102));
    ckb_2_des.setText("descending");
    ckb_2_des.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            ckb_2_desMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jpn_right3Layout = new javax.swing.GroupLayout(jpn_right3);
    jpn_right3.setLayout(jpn_right3Layout);
    jpn_right3Layout.setHorizontalGroup(
        jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_right3Layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addGroup(jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jlb_2_search)
                .addComponent(jt_2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpn_right3Layout.createSequentialGroup()
                    .addComponent(cb_2_search, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_2_search, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_2_searchmore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpn_right3Layout.createSequentialGroup()
                        .addComponent(jlb_2_sort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckb_2_des))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpn_right3Layout.createSequentialGroup()
                        .addComponent(cb_2_sort, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_2_sort, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btn_2_sortmore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(19, Short.MAX_VALUE))
    );
    jpn_right3Layout.setVerticalGroup(
        jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_right3Layout.createSequentialGroup()
            .addGap(33, 33, 33)
            .addComponent(jlb_2_search)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jt_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_2_searchmore)
                .addComponent(cb_2_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_2_search))
            .addGap(18, 18, 18)
            .addGroup(jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jlb_2_sort)
                .addComponent(ckb_2_des))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jpn_right3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cb_2_sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_2_sortmore)
                .addComponent(btn_2_sort))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jpn_right2Layout = new javax.swing.GroupLayout(jpn_right2);
    jpn_right2.setLayout(jpn_right2Layout);
    jpn_right2Layout.setHorizontalGroup(
        jpn_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_right2Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jpn_right3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jpn_right2Layout.setVerticalGroup(
        jpn_right2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jpn_right3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jpn_main_necitemsLayout = new javax.swing.GroupLayout(jpn_main_necitems);
    jpn_main_necitems.setLayout(jpn_main_necitemsLayout);
    jpn_main_necitemsLayout.setHorizontalGroup(
        jpn_main_necitemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_main_necitemsLayout.createSequentialGroup()
            .addComponent(jpn_left1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jpn_right2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jpn_main_necitemsLayout.setVerticalGroup(
        jpn_main_necitemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jpn_left1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jpn_right2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    getContentPane().add(jpn_main_necitems, "card2");

    jpn_main_stat.setBackground(new java.awt.Color(204, 204, 204));

    jpn_left2.setBackground(new java.awt.Color(255, 255, 255));

    jlb_3_tabname.setBackground(new java.awt.Color(204, 204, 204));
    jlb_3_tabname.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
    jlb_3_tabname.setForeground(new java.awt.Color(102, 102, 102));
    jlb_3_tabname.setText("Statistical system");

    btn_3_totab2.setBackground(new java.awt.Color(102, 102, 102));
    btn_3_totab2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btn_3_totab2.setForeground(new java.awt.Color(255, 255, 255));
    btn_3_totab2.setText("Necessary items");
    btn_3_totab2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_3_totab2MouseClicked(evt);
        }
    });

    btn_3_totab1.setBackground(new java.awt.Color(102, 102, 102));
    btn_3_totab1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btn_3_totab1.setForeground(new java.awt.Color(255, 255, 255));
    btn_3_totab1.setText("Covid management");
    btn_3_totab1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btn_3_totab1MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jpn_left2Layout = new javax.swing.GroupLayout(jpn_left2);
    jpn_left2.setLayout(jpn_left2Layout);
    jpn_left2Layout.setHorizontalGroup(
        jpn_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_left2Layout.createSequentialGroup()
            .addGap(63, 63, 63)
            .addComponent(btn_3_totab1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btn_3_totab2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
            .addComponent(jlb_3_tabname)
            .addGap(25, 25, 25))
    );
    jpn_left2Layout.setVerticalGroup(
        jpn_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_left2Layout.createSequentialGroup()
            .addGroup(jpn_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpn_left2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jlb_3_tabname))
                .addGroup(jpn_left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_3_totab1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_3_totab2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(407, Short.MAX_VALUE))
    );

    jpn_right4.setBackground(new java.awt.Color(51, 51, 51));

    jpn_right5.setBackground(new java.awt.Color(204, 204, 204));

    jlb_3_realtime.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jlb_3_realtime.setForeground(new java.awt.Color(102, 102, 102));
    jlb_3_realtime.setText("REALTIME");

    javax.swing.GroupLayout jpn_right5Layout = new javax.swing.GroupLayout(jpn_right5);
    jpn_right5.setLayout(jpn_right5Layout);
    jpn_right5Layout.setHorizontalGroup(
        jpn_right5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_right5Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jlb_3_realtime, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(101, Short.MAX_VALUE))
    );
    jpn_right5Layout.setVerticalGroup(
        jpn_right5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_right5Layout.createSequentialGroup()
            .addGap(24, 24, 24)
            .addComponent(jlb_3_realtime)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jpn_right4Layout = new javax.swing.GroupLayout(jpn_right4);
    jpn_right4.setLayout(jpn_right4Layout);
    jpn_right4Layout.setHorizontalGroup(
        jpn_right4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_right4Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jpn_right5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jpn_right4Layout.setVerticalGroup(
        jpn_right4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jpn_right5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jpn_main_statLayout = new javax.swing.GroupLayout(jpn_main_stat);
    jpn_main_stat.setLayout(jpn_main_statLayout);
    jpn_main_statLayout.setHorizontalGroup(
        jpn_main_statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpn_main_statLayout.createSequentialGroup()
            .addComponent(jpn_left2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jpn_right4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jpn_main_statLayout.setVerticalGroup(
        jpn_main_statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jpn_left2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jpn_right4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    getContentPane().add(jpn_main_stat, "card2");

    jmn1.setText("File");

    jmi1.setText("Import New User");
    jmn1.add(jmi1);

    jmi2.setText("Export database");
    jmn1.add(jmi2);

    jmb1.add(jmn1);

    jmn2.setText("Edit");
    jmb1.add(jmn2);

    jmn3.setText("Account");

    jmi4.setText("Manage account");
    jmn3.add(jmi4);

    jmi5.setText("Sign out");
    jmn3.add(jmi5);

    jmb1.add(jmn3);

    setJMenuBar(jmb1);
    jmb1.getAccessibleContext().setAccessibleName("");

    getAccessibleContext().setAccessibleName("jfrm");

    pack();
    }// </editor-fold>//GEN-END:initComponents

    //change card
    private void btn_1_totab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_totab3MouseClicked
        showTab = 3;
        changecard();
    }//GEN-LAST:event_btn_1_totab3MouseClicked

    private void btn_1_totab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_totab2MouseClicked
        showTab = 2;
        changecard();
    }//GEN-LAST:event_btn_1_totab2MouseClicked

    private void btn_2_totab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2_totab3MouseClicked
        showTab = 3;
        changecard();
    }//GEN-LAST:event_btn_2_totab3MouseClicked

    private void btn_2_totab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2_totab1MouseClicked
        showTab = 1;
        changecard();
    }//GEN-LAST:event_btn_2_totab1MouseClicked

    private void btn_3_totab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3_totab2MouseClicked
        showTab = 2;
        changecard();
    }//GEN-LAST:event_btn_3_totab2MouseClicked

    private void btn_3_totab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3_totab1MouseClicked
        showTab = 1;
        changecard();
    }//GEN-LAST:event_btn_3_totab1MouseClicked

    //execute event
    private void jtb_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_1MouseClicked
        //System.out.println(jtb_1.getSelectedRow());
        //System.out.println(jtb_1.getSelectedColumn());
        setViewButton();
    }//GEN-LAST:event_jtb_1MouseClicked

    private void setViewButton(){
        if(!"F0".equals(tbmodel_1.getValueAt(jtb_1.getSelectedRow(), 4).toString())) btn_1_view_up.setEnabled(true);
        else btn_1_view_up.setEnabled(false);
        if(!"F3".equals(tbmodel_1.getValueAt(jtb_1.getSelectedRow(), 4).toString())) btn_1_view_down.setEnabled(true);
        else btn_1_view_down.setEnabled(false);
    }
    private void setViewButtonOff(){
        btn_1_view_up.setEnabled(false);
        btn_1_view_down.setEnabled(false);
        btn_1_search.setEnabled(false);
        btn_1_sort.setEnabled(false);
    }
    private void btn_1_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_searchMouseClicked
        if(btn_1_search.isEnabled()) resettable(); 
    }//GEN-LAST:event_btn_1_searchMouseClicked

    private void btn_1_sortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_sortMouseClicked
        if(btn_1_sort.isEnabled())resettable();
    }//GEN-LAST:event_btn_1_sortMouseClicked

    private void jt_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_1MouseClicked
        if(btn_1_search.isEnabled() && btn_1_search.isSelected()) {btn_1_search.setSelected(false);resettable();}    
    }//GEN-LAST:event_jt_1MouseClicked

    private void cb_1_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_1_searchMouseClicked
        if(btn_1_search.isEnabled() && btn_1_search.isSelected()) {btn_1_search.setSelected(false);resettable();}        
    }//GEN-LAST:event_cb_1_searchMouseClicked

    private void btn_1_searchmoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_searchmoreMouseClicked
        if(btn_1_search.isEnabled() && btn_1_search.isSelected()) {btn_1_search.setSelected(false);resettable();}    
    }//GEN-LAST:event_btn_1_searchmoreMouseClicked

    private void btn_1_sortmoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_sortmoreMouseClicked
        if(btn_1_sort.isEnabled()&& btn_1_sort.isSelected()) {btn_1_sort.setSelected(false);resettable();}    
    }//GEN-LAST:event_btn_1_sortmoreMouseClicked

    private void ckb_1_desMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckb_1_desMouseClicked
        if(btn_1_sort.isEnabled()&& btn_1_sort.isSelected()) {btn_1_sort.setSelected(false);resettable();}        
    }//GEN-LAST:event_ckb_1_desMouseClicked

    private void cb_1_sortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_1_sortMouseClicked
        if(btn_1_sort.isEnabled()&& btn_1_sort.isSelected()) {btn_1_sort.setSelected(false);resettable();}      
    }//GEN-LAST:event_cb_1_sortMouseClicked

    private void btn_2_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2_searchMouseClicked
        if(btn_2_search.isEnabled()) resettable();
    }//GEN-LAST:event_btn_2_searchMouseClicked

    private void btn_2_sortMouseClicked(java.awt.event.MouseEvent evt) {                                        
        if(btn_2_sort.isEnabled())resettable();
    }                                       

    private void cb_2_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_2_searchMouseClicked
        if(btn_2_search.isEnabled()&& btn_2_search.isSelected()) {btn_2_search.setSelected(false);resettable();}    
    }//GEN-LAST:event_cb_2_searchMouseClicked

    private void btn_2_searchmoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2_searchmoreMouseClicked
        if(btn_2_search.isEnabled()&& btn_2_search.isSelected()){btn_2_search.setSelected(false);resettable();} 
    }//GEN-LAST:event_btn_2_searchmoreMouseClicked

    private void jt_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_2MouseClicked
        if(btn_2_search.isEnabled()&& btn_2_search.isSelected()) {btn_2_search.setSelected(false);resettable();} 
    }//GEN-LAST:event_jt_2MouseClicked

    private void cb_2_sortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_2_sortMouseClicked
        if(btn_2_sort.isEnabled()&& btn_2_sort.isSelected()) {btn_2_sort.setSelected(false);resettable();} 
    }//GEN-LAST:event_cb_2_sortMouseClicked

    private void btn_2_sortmoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2_sortmoreMouseClicked
        if(btn_2_sort.isEnabled()&& btn_2_sort.isSelected()) {btn_2_sort.setSelected(false);resettable();} 
    }//GEN-LAST:event_btn_2_sortmoreMouseClicked

    private void ckb_2_desMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckb_2_desMouseClicked
        if(btn_2_sort.isEnabled()&& btn_2_sort.isSelected()) {btn_2_sort.setSelected(false);resettable();} 
    }//GEN-LAST:event_ckb_2_desMouseClicked

    private void btn_1_view_upMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_view_upMouseReleased
        if(btn_1_view_up.isEnabled()){
        String upID = tbmodel_1.getValueAt(jtb_1.getSelectedRow(), 6).toString();
        String query = "select citizen_id, full_name, date_of_birth, concat(ward_name, ', ' , district_name, ', ', province_name) as citizen_address, condition, treatment_place_name, related_to from covid_patient join ward on ward.ward_id = covid_patient.citizen_address join district_has_ward on ward.ward_id = district_has_ward.ward_id join district on district.district_id = district_has_ward.district_id join province_has_district on province_has_district.district_id = district.district_id join province on province.province_id = province_has_district.province_id join treatment_place on treatment_place.treatment_place_id = covid_patient.treatment_place_id  where covid_patient.condition is not null and covid_patient.citizen_id = " + upID;
        resettable(query);
        setViewButtonOff();
        btn_1_view_cancel.setEnabled(true);
        }
    }//GEN-LAST:event_btn_1_view_upMouseReleased

    private void btn_1_view_downMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_view_downMouseReleased
        if(btn_1_view_down.isEnabled()){
        String downID = tbmodel_1.getValueAt(jtb_1.getSelectedRow(), 0).toString();
        String query = "select citizen_id, full_name, date_of_birth, concat(ward_name, ', ' , district_name, ', ', province_name) as citizen_address, condition, treatment_place_name, related_to from covid_patient join ward on ward.ward_id = covid_patient.citizen_address join district_has_ward on ward.ward_id = district_has_ward.ward_id join district on district.district_id = district_has_ward.district_id join province_has_district on province_has_district.district_id = district.district_id join province on province.province_id = province_has_district.province_id join treatment_place on treatment_place.treatment_place_id = covid_patient.treatment_place_id  where covid_patient.condition is not null and covid_patient.related_to = " + downID;
        resettable(query);
        setViewButtonOff();
        btn_1_view_cancel.setEnabled(true);
        }
    }//GEN-LAST:event_btn_1_view_downMouseReleased

    private void btn_1_view_cancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_view_cancelMouseReleased
        if(btn_1_view_cancel.isEnabled()){
        resettable();
        setViewButtonOff();
        btn_1_search.setEnabled(true);
        btn_1_sort.setEnabled(true);
        }
    }//GEN-LAST:event_btn_1_view_cancelMouseReleased

    private void btn_1_addMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1_addMouseReleased
        ManagerPatient mgPt = new ManagerPatient();
        mgPt.setLocationRelativeTo(null);
        mgPt.setTitle("Add a new patient");
        mgPt.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mgPt.setVisible(true);
    }//GEN-LAST:event_btn_1_addMouseReleased

    private void btn_2_sortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2_sortMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_2_sortMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void ManageUImain(String args[]) {
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
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_1_add;
    private javax.swing.JButton btn_1_remove;
    private javax.swing.JToggleButton btn_1_search;
    private javax.swing.JButton btn_1_searchmore;
    private javax.swing.JToggleButton btn_1_sort;
    private javax.swing.JButton btn_1_sortmore;
    private javax.swing.JButton btn_1_totab2;
    private javax.swing.JButton btn_1_totab3;
    private javax.swing.JButton btn_1_update;
    private javax.swing.JButton btn_1_view_cancel;
    private javax.swing.JButton btn_1_view_down;
    private javax.swing.JButton btn_1_view_up;
    private javax.swing.JButton btn_2_add;
    private javax.swing.JButton btn_2_remove;
    private javax.swing.JToggleButton btn_2_search;
    private javax.swing.JButton btn_2_searchmore;
    private javax.swing.JToggleButton btn_2_sort;
    private javax.swing.JButton btn_2_sortmore;
    private javax.swing.JButton btn_2_totab1;
    private javax.swing.JButton btn_2_totab3;
    private javax.swing.JButton btn_2_update;
    private javax.swing.JButton btn_3_totab1;
    private javax.swing.JButton btn_3_totab2;
    private javax.swing.JComboBox<String> cb_1_search;
    private javax.swing.JComboBox<String> cb_1_sort;
    private javax.swing.JComboBox<String> cb_2_search;
    private javax.swing.JComboBox<String> cb_2_sort;
    private javax.swing.JCheckBox ckb_1_des;
    private javax.swing.JCheckBox ckb_2_des;
    private javax.swing.JLabel jlb_1_search;
    private javax.swing.JLabel jlb_1_sort;
    private javax.swing.JLabel jlb_1_tabname;
    private javax.swing.JLabel jlb_1_view;
    private javax.swing.JLabel jlb_2_search;
    private javax.swing.JLabel jlb_2_sort;
    private javax.swing.JLabel jlb_2_tabname;
    private javax.swing.JLabel jlb_3_realtime;
    private javax.swing.JLabel jlb_3_tabname;
    private javax.swing.JMenuBar jmb1;
    private javax.swing.JMenuItem jmi1;
    private javax.swing.JMenuItem jmi2;
    private javax.swing.JMenuItem jmi4;
    private javax.swing.JMenuItem jmi5;
    private javax.swing.JMenu jmn1;
    private javax.swing.JMenu jmn2;
    private javax.swing.JMenu jmn3;
    private javax.swing.JPanel jpn_left;
    private javax.swing.JPanel jpn_left1;
    private javax.swing.JPanel jpn_left2;
    private javax.swing.JPanel jpn_main_covidmg;
    private javax.swing.JPanel jpn_main_necitems;
    private javax.swing.JPanel jpn_main_stat;
    private javax.swing.JPanel jpn_right;
    private javax.swing.JPanel jpn_right1;
    private javax.swing.JPanel jpn_right2;
    private javax.swing.JPanel jpn_right3;
    private javax.swing.JPanel jpn_right4;
    private javax.swing.JPanel jpn_right5;
    private javax.swing.JPanel jpn_table;
    private javax.swing.JPanel jpn_table1;
    private javax.swing.JScrollPane jsc_1;
    private javax.swing.JScrollPane jsc_2;
    private javax.swing.JTextField jt_1;
    private javax.swing.JTextField jt_2;
    private javax.swing.JTable jtb_1;
    private javax.swing.JTable jtb_2;
    // End of variables declaration//GEN-END:variables


}
