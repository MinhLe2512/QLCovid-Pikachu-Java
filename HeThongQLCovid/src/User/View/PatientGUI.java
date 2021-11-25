package User.View;

import User.CovidPatient.CovidPatient;
import User.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class PatientGUI extends JFrame {

    final int WIDTH =500;
    final int HEIGHT =500;

    // option button [3]:
    JButton _BinfoOption;
    JButton _BpackageOption;
    JButton _BpaymentOption;


    // screen one Info//////////////////////////////

    JPanel _Pinfo;
    // its functionality
    JButton _BbasicInfo;
    JButton _Bmanagement;
    JButton _BpackageConsume;
    JButton _Bbalance;
    JButton _Bpayment;


    // ---------------------------------------
    ArrayList<CovidPatient> listPatient;



    /////////////////////////////////////
    JPanel paneltop;
    JPanel panelleft;
    JButton button;



    public PatientGUI(String username) throws SQLException {
        super("Covid Patient");
        // set up frame
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        // init 3 option:
        _BinfoOption = new JButton("Info");
        _BpackageOption = new JButton("Package");
        _BpaymentOption = new JButton("Payment");
        _BinfoOption.setBounds(0,0,100,30);
        _BpackageOption.setBounds(0,30,100,30);
        _BpaymentOption.setBounds(0,60,100,30);



        //Screen 1: INFO
        //setup panel
        _Pinfo = new JPanel();
        _Pinfo.setLayout(null);
        _Pinfo.setBackground(new Color(0x94B3FD)); // for debug
        _Pinfo.setBounds(0,0,WIDTH,HEIGHT);
        _Pinfo.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // init and add element
        // add 3 option
        _Pinfo.add(_BinfoOption);
        _Pinfo.add(_BpackageOption);
        _Pinfo.add(_BpaymentOption);

        JPanel basicInfo = new JPanel();
        _Pinfo.setLayout(null);
        _Pinfo.setBackground(new Color(0x94B3FD)); // for debug
        _Pinfo.setBounds(0,0,200,100);

        JLabel ID = new JLabel();
        JLabel name = new JLabel();
        JLabel DOB = new JLabel();
        JLabel add = new JLabel();
        JLabel treatmentplace = new JLabel();

        Statement statement = DatabaseConnection.getJDBC().createStatement();
        String sql = "SELECT * FROM covid_patient\n"+
                "WHERE citizen_id="+username+";";
        ResultSet rs = statement.executeQuery(sql);

        listPatient = new ArrayList<CovidPatient>();
        while(rs.next()){
            CovidPatient temp = new CovidPatient();
            temp.set_citizen_id(rs.getString("citizen_id"));
            temp.set_name(rs.getString("full_name"));
            temp.set_dob(rs.getString("date_of_birth"));
            temp.set_address(rs.getString("citizen_address"));
            temp.set_status(rs.getString("_condition"));
            temp.set_treatmentArea(rs.getString("treatment_place_id"));
            temp.set_patientRelavent(rs.getString("related_to"));

            listPatient.add(temp);
        }

        //------------------------------------------------


        paneltop = new JPanel();
        paneltop.setLayout(null);
        paneltop.setBackground(Color.GREEN);
        paneltop.setBounds(0,0,WIDTH,HEIGHT);
        paneltop.setPreferredSize(new Dimension(WIDTH,HEIGHT));




        panelleft = new JPanel();
        panelleft.setLayout(null);
        panelleft.setBackground(Color.BLACK);




        this.add(_Pinfo);
        this.pack();

    }

    public void refresh(){
        this.getContentPane().removeAll();
        this.add(paneltop);
        this.pack();
    }


    public static void main(String[] args) throws InterruptedException, SQLException {
        PatientGUI screen = new PatientGUI("0323812311");
        screen.setVisible(true);
//        TimeUnit.SECONDS.sleep(3);
//        screen.refresh();


    }

}

