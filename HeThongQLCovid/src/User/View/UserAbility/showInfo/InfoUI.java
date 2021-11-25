package User.View.UserAbility.showInfo;

import User.CovidPatient.CovidPatient;
import User.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InfoUI extends JPanel {

    ArrayList<CovidPatient> listPatient;

    public InfoUI(String username) throws SQLException {
        this.setLayout(null);
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);



        JLabel LID = new JLabel();
        LID.setBounds(10,10,200,30);
        JLabel Lname = new JLabel();
        Lname.setBounds(10,40,200,30);
        JLabel LDOB = new JLabel();
        LDOB.setBounds(10,70,200,30);
        JLabel Ladd = new JLabel();
        Ladd.setBounds(10,100,200,30);
        JLabel Ltreatmentplace = new JLabel();
        Ltreatmentplace.setBounds(10,130,200,30);

        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM covid_patient\n"+
                "WHERE citizen_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listPatient = new ArrayList<CovidPatient>();
        CovidPatient temp = new CovidPatient();
        while(rs.next()){

            temp.set_citizen_id(rs.getString("citizen_id"));
            temp.set_name(rs.getString("full_name"));
            temp.set_dob(rs.getString("date_of_birth"));
            temp.set_address(rs.getString("citizen_address"));
            temp.set_status(rs.getString("_condition"));
            temp.set_treatmentArea(rs.getString("treatment_place_id"));
            temp.set_patientRelavent(rs.getString("related_to"));

            listPatient.add(temp);
        }
        System.out.println(temp.getInfo());

        LID.setText("ID: "+temp.get_citizen_id());
        Lname.setText("Name: "+temp.get_name());
        LDOB.setText("DOB: "+temp.get_dob().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
        Ladd.setText("Address:      "+temp.get_address());
        Ltreatmentplace.setText("Citizen ID: "+temp.get_citizen_id());

        this.add(LID);
        this.add(Lname);
        this.add(LDOB);
        this.add(Ladd);
        this.add(Ltreatmentplace);

        //------------------------------------------------
    }
}
