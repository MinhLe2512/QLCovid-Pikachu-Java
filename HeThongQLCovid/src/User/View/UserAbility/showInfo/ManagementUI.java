package User.View.UserAbility.showInfo;

import User.CovidPatient.CovidPatient;
import User.CovidPatient.PatientHistory;
import User.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManagementUI extends JPanel {

    ArrayList<PatientHistory> listTreament;
    PtablePatientHistory PtableHistory;

    public ManagementUI(String username) throws SQLException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.setVisible(false);


        // connect to database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM patient_history\n"+
                "WHERE patient_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listTreament = new ArrayList<PatientHistory>();
        PatientHistory temp = new PatientHistory();
        while(rs.next()){

            temp.set_treatment_ID(rs.getString("patient_history_id"));
            temp.set_patient_ID(rs.getString("patient_id"));
            temp.set_patientAction(rs.getString("patient_action"));
            temp.set_startDate(rs.getString("patient_date"));

            listTreament.add(temp);
        }
        System.out.println("Management History "+temp.getInfo());

        PtableHistory = new PtablePatientHistory(username);
        this.add(PtableHistory);


    }
}



















