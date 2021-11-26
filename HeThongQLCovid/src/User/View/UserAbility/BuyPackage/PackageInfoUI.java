package User.View.UserAbility.BuyPackage;

import DailyNecessityStorage.PackageClass;
import User.CovidPatient.CovidPatient;
import User.CovidPatient.PatientHistory;
import User.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PackageInfoUI extends JPanel{


    PtablePackage Ptable;

    public PackageInfoUI() throws SQLException {
        // init panel
        this.setLayout(null);
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        //setPreferredSize(new Dimension(500, 250));
        this.setVisible(false);

        Ptable = new PtablePackage();




        this.add(Ptable);


    }
}
