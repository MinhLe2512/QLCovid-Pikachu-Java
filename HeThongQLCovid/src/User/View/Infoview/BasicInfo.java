package User.View.Infoview;

import javax.swing.*;
import java.awt.*;

public class BasicInfo extends JPanel{
    // its functionality

    JButton _Bmanagement;
    JButton _BpackageConsume;
    JButton _Bbalance;
    JButton _Bpayment;

    public BasicInfo(){
        //setup panel
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
        this.setBackground(new Color(0xFFE652)); // for debug
        this.setBounds(0,0,400,50);

        // init button

        _Bmanagement = new JButton("Management");
        _BpackageConsume = new JButton("Package");
        _Bbalance = new JButton("Balance");
        _Bpayment = new JButton("Payment");


        // add button

        this.add(_Bmanagement);
        this.add(_BpackageConsume);
        this.add(_Bbalance);
        this.add(_Bpayment);
    }

}
