package com.example.qlcovid.jframe.User.Package;

import com.example.qlcovid.jframe.User.PtablePackage;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

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
