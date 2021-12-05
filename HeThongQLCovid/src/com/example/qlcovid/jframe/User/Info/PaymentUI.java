package com.example.qlcovid.jframe.User.Info;

import javax.swing.*;
import java.awt.*;

public class PaymentUI extends JPanel{

    public PaymentUI(){
        this.setLayout(new FlowLayout());/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("Payment"));
        this.setVisible(false);
    }
}
