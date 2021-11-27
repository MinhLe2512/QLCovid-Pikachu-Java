package User.View.UserAbility.showInfo;


import User.CovidPatient.PatientHistory;
import User.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PtablePatientHistory extends JPanel{

    ArrayList<PatientHistory> historyList;
    JTable PatientHistoryTalbe;
    DefaultTableModel modelListHistory;

    public PtablePatientHistory(String username)  throws SQLException {

        // getting data from database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM patient_history\n"+
                "WHERE patient_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        historyList = new ArrayList<PatientHistory>();
        while(rs.next()){

            historyList.add(new PatientHistory(
                    rs.getString("patient_history_id"),
                    rs.getString("patient_id"),
                    rs.getString("patient_action"),
                    rs.getString("patient_date")
                    ));
        }

        for(PatientHistory x : historyList){
            System.out.println(x.getInfo());
        }


        this.setOpaque(true);
        this.setBounds(50,0,350,100);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 250));

        modelListHistory = new DefaultTableModel(
                new String[] { "ID","Patient ID","Patient action","Date"},
                0
        );
        for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }
        for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }
        for(PatientHistory x : historyList){
            modelListHistory.addRow(x.getObject());
        }

        PatientHistoryTalbe = new JTable(modelListHistory);//modelListPackage
        //packageTalbe.setBounds(10,10,400,200);
        //packageTalbe.setPreferredScrollableViewportSize(new Dimension(300, 10));

        PatientHistoryTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(PatientHistoryTalbe);
        this.add(pane, BorderLayout.CENTER);
        PatientHistoryTalbe.setDefaultEditor(Object.class, null);
        PatientHistoryTalbe.getTableHeader().setReorderingAllowed(false);







    }
}
