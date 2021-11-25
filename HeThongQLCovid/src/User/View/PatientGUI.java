package User.View;

import User.CovidPatient.CovidPatient;
import User.DatabaseConnection;
import User.View.Infoview.InfoAbility;
import User.View.Infoview.UserAbility.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PatientGUI extends JFrame {

    // attributes
    // default width and height
    final int WIDTH =500;
    final int HEIGHT =500;

    // option button [3]:
    JButton _BinfoOption;
    JButton _BpackageOption;
    JButton _BpaymentOption;

    // barPanel
    JPanel _Poption;
    JPanel _Pheader;
    JPanel _Pswapping;

    // // info panel: INFO//////////////////////////////
    InfoAbility PinfoAbility ;
    static InfoUI PbasicInfo ;
    static ManagementUI PmanagementInfo ;
    static PackageUI PpackageInfo;
    static BalanceUI PbalanceInfo;
    static PaymentUI PpaymentInfo;

    // ---------------------------------------




    /////////////////////////////////////
    JPanel paneltop;
    JPanel panelleft;
    JButton button;


    // constructor
    public PatientGUI(String username) throws SQLException {

        // title
        super("Covid Patient");

        // set up frame
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(515,500);
        this.setResizable(false);
        //--

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
        //--


        // info panel: INFO ability
        // setup panel
        PinfoAbility = new InfoAbility();
        PbasicInfo = new InfoUI(username);
        PmanagementInfo = new ManagementUI();
        PpackageInfo = new PackageUI();
        PbalanceInfo = new BalanceUI();
        PpaymentInfo= new PaymentUI();
        //--


        //bar 1: OPTION
        //setup panel
        _Poption = new JPanel();
        _Poption.setLayout(null);
        _Poption.setBackground(new Color(0x71DFE7)); // for debug
        _Poption.setBounds(0,30,100,470);

        // init and add element
        // add 3 option
        _Poption.add(_BinfoOption);
        _Poption.add(_BpackageOption);
        _Poption.add(_BpaymentOption);
        //--

        //bar 2: HEADER
        //setup panel
        _Pheader = new JPanel();
        _Pheader.setLayout(null);
        _Pheader.setBackground(new Color(0x009DAE)); // for debug
        _Pheader.setBounds(0,0,500,30);
        //--

        //bar 3: SWAPPING
        _Pswapping = new JPanel();
        _Pswapping.setLayout(null);
        _Pswapping.setBounds(100,30,400,470);
        //--

        // info scene
        // add panel
        _Pswapping.add(PinfoAbility);
        _Pswapping.add(PbasicInfo);
        _Pswapping.add(PmanagementInfo);
        _Pswapping.add(PpackageInfo);
        _Pswapping.add(PbalanceInfo);
        _Pswapping.add(PpaymentInfo);


//-----------------------------------------



        // frame adding panel
        this.add(_Pswapping);
        this.add(_Poption);
        this.add(_Pheader);


    }
    //-- end constructor


    //method

    // Group of function for switch screen
    public static void setInfoInvisible() {
        PbasicInfo.setVisible(false);
        PmanagementInfo.setVisible(false);
        PpackageInfo.setVisible(false);
        PbalanceInfo.setVisible(false);
        PpaymentInfo.setVisible(false);

    }
    public static void showBasicInfo() {
        setInfoInvisible();
        PbasicInfo.setVisible(true);
    }
    public static void showManagementInfo() {
        setInfoInvisible();
        PmanagementInfo.setVisible(true);
    }
    public static void showPackageInfo() {
        setInfoInvisible();
        PpackageInfo.setVisible(true);
    }
    public static void showBalanceInfo() {
        setInfoInvisible();
        PbalanceInfo.setVisible(true);
    }
    public static void showPaymentInfo() {
        setInfoInvisible();
        PpaymentInfo.setVisible(true);
    }
//-------------------------------------------------------

    public static void main(String[] args) throws InterruptedException, SQLException {
        PatientGUI screen = new PatientGUI("0323812311");
        screen.setVisible(true);
//        TimeUnit.SECONDS.sleep(3);
//        screen.refresh();
        //TimeUnit.SECONDS.sleep(3);
        //screen.re();

    }

}

