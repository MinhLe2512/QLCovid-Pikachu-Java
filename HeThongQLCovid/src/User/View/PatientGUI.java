package User.View;

import User.CovidPatient.CovidPatient;
import User.DatabaseConnection;
import User.View.Infoview.BasicInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class PatientGUI extends JFrame {

    final int WIDTH =500;
    final int HEIGHT =500;

    // option button [3]:
    JButton _BinfoOption;
    JButton _BpackageOption;
    JButton _BpaymentOption;

    // barPanel
    JPanel _Poption;
    JPanel _Pheader;
    JPanel _Pinfo;
    JPanel _Pswapping;
    // screen one Info//////////////////////////////





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
        this.setLayout(null);
        this.setSize(515,500);
        this.setResizable(false);
        // init 3 option:
        _BinfoOption = new JButton("Info");
        _BpackageOption = new JButton("Package");
        _BpaymentOption = new JButton("Payment");
        _BinfoOption.setFocusable(false);
        _BpackageOption.setFocusable(false);
        _BpaymentOption.setFocusable(false);
        _BinfoOption.setBackground(new Color(0x71DFE7));
        _BpackageOption.setBackground(new Color(0x71DFE7));
        _BpaymentOption.setBackground(new Color(0x71DFE7));
        _BinfoOption.setBounds(0,0,100,30);
        _BpackageOption.setBounds(0,30,100,30);
        _BpaymentOption.setBounds(0,60,100,30);



        // info panel: INFO
        // setup panel
        BasicInfo PbasicInfo = new BasicInfo();


        //bar 1: OPTION
        //setup panel
        _Poption = new JPanel();
        _Poption.setLayout(null);
        _Poption.setBackground(new Color(0x71DFE7)); // for debug
        _Poption.setBounds(0,30,100,470);
        //_Poption.setPreferredSize(new Dimension(120,HEIGHT));
        // init and add element
        // add 3 option
        _Poption.add(_BinfoOption);
        _Poption.add(_BpackageOption);
        _Poption.add(_BpaymentOption);

        //bar 2: HEADER
        //setup panel
        _Pheader = new JPanel();
        _Pheader.setLayout(null);
        _Pheader.setBackground(new Color(0x009DAE)); // for debug
        _Pheader.setBounds(0,0,500,30);

        //bar 3: SWAPPING
        _Pswapping = new JPanel();
        _Pswapping.setLayout(null);
        _Pswapping.setBounds(100,30,400,470);
        _Pswapping.add(PbasicInfo);





        JLabel LID = new JLabel();
        LID.setBounds(200,0,50,30);
        JLabel Lname = new JLabel();
        Lname.setBounds(250,0,50,30);
        JLabel LDOB = new JLabel();
        LDOB.setBounds(300,0,50,30);
        JLabel Ladd = new JLabel();
        Ladd.setBounds(350,0,50,30);
        JLabel Ltreatmentplace = new JLabel();
        Ltreatmentplace.setBounds(400,0,50,30);

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

        LID.setText(temp.get_citizen_id());
        Lname.setText(temp.get_name());
        //LDOB.setText(temp.get_dob());
        Ladd.setText(temp.get_citizen_id());
        Ltreatmentplace.setText(temp.get_citizen_id());

        //------------------------------------------------


        paneltop = new JPanel();
        paneltop.setLayout(null);
        paneltop.setBackground(Color.GREEN);
        paneltop.setBounds(0,0,WIDTH,HEIGHT);
        //paneltop.setPreferredSize(new Dimension(WIDTH,HEIGHT));




        panelleft = new JPanel();
        panelleft.setLayout(null);
        panelleft.setBackground(Color.BLACK);

        //this.setContentPane(_Pswapping);
        this.add(_Pswapping);
        this.add(_Poption);
        this.add(_Pheader);

        //this.pack();

    }

    public void refresh(){
        //this.getContentPane().removeAll();
        //this.add(paneltop);
        //this.pack();
        _Pswapping.setVisible(false);
    }

    public void re(){
        //this.getContentPane().removeAll();
        //this.add(paneltop);
        //this.pack();
        _Pswapping.setVisible(true);
    }


    public static void main(String[] args) throws InterruptedException, SQLException {
        PatientGUI screen = new PatientGUI("0323812311");
        screen.setVisible(true);
        TimeUnit.SECONDS.sleep(3);
        screen.refresh();
        //TimeUnit.SECONDS.sleep(3);
        //screen.re();

    }

}

