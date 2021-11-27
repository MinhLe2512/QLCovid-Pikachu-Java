package User.View.UserAbility.showInfo;

import DailyNecessityStorage.PackagePurchase;
import User.CovidPatient.PatientHistory;
import User.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PackageUI extends JPanel {

    ArrayList<PackagePurchase> listPurchase;
    PtablePatientHistory tableHistory;
    public PackageUI(String username) throws SQLException {
        this.setLayout(null); /////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0, 80, 400, 390);
        this.setVisible(false);

        // getting data from database
        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM ql_order\n"+
                "WHERE customer_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listPurchase = new ArrayList<PackagePurchase>();
        while(rs.next()){

            listPurchase.add(new PackagePurchase(
                    rs.getString("order_id"),
                    rs.getString("customer_id"),
                    rs.getString("package_id"),
                    rs.getString("order_date")
            ));
        }

        for(PackagePurchase x : listPurchase){
            System.out.println(x.getInfo());
        }
        tableHistory = new PtablePatientHistory(username);
        this.add(tableHistory);
    }
}
