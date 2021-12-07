package com.example.qlcovid.jframe.User.Package;

import com.example.qlcovid.jframe.User.PtablePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PackageLookUpUI extends JPanel{

    PtablePackage Ptable;

    public PackageLookUpUI() throws SQLException {
        this.setLayout(null);/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("PackageLookUpUI"));
        this.setVisible(false);

        Ptable = new PtablePackage();

        JButton Border = new JButton("Order");
        Border.setBounds(0,100,60,30);
        Border.setHorizontalAlignment(SwingConstants.LEADING );

        JLabel Lid = new JLabel("ID: ");
        Lid.setBounds(10,110,50,30);
        JComboBox CBid = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBid.setBounds(85,100,70,20);
        CBid.setSelectedItem("--");

        JLabel Lname = new JLabel("Name: ");
        Lname.setBounds(210,110,50,30);
        JComboBox CBname = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBname.setBounds(310,100,70,20);
        CBname.setSelectedItem("--");

        JLabel Llimit = new JLabel("Limit");
        Llimit.setBounds(165,100,30,30);
        JComboBox CBlimit = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBlimit.setBounds(195,100,20,20);
        CBlimit.setSelectedItem("--");

        JLabel Lstart = new JLabel("Start");
        Lstart.setBounds(225,100,30,30);
        JComboBox CBstart = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBstart.setBounds(255,100,20,20);
        CBstart.setSelectedItem("--");

        JLabel Lend = new JLabel("End");
        Lend.setBounds(283,100,25,30);
        JComboBox CBend = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBend.setBounds(305,100,20,20);
        CBend.setSelectedItem("--");

        JLabel Lprice = new JLabel("Price");
        Lprice.setBounds(330,100,30,30);
        JComboBox CBprice = new JComboBox(new String[]{"\uD83D\uDD3A","\uD83D\uDD3B","--"});
        CBprice.setBounds(362,100,20,20);
        CBprice.setSelectedItem("--");

        // searching
        JButton Bsearch = new JButton("Search");
        Bsearch.setBounds(0,140,70,30);

        JTextField Tid = new JTextField();
        Tid.setBounds(30,110,50,30);

        JTextField Tname = new JTextField();
        Tname.setBounds(250,110,55,30);

        JTextField Tlimit = new JTextField("Limit");
        Tlimit.setBounds(165,140,50,30);

        JTextField Tstart = new JTextField("Start Date");
        Tstart.setBounds(225,140,50,30);

        JTextField Tend = new JTextField("End Date");
        Tend.setBounds(283,140,42,30);

        JTextField Tprice = new JTextField("Price");
        Tprice.setBounds(330,140,52,30);


        JButton Bfilter = new JButton("Filter");
        Bfilter.setBounds(0,180,70,30);

        JComboBox CBlimitFilter = new JComboBox(new String[]{">","<=","--"});
        CBlimitFilter.setBounds(195,180,20,20);
        CBlimitFilter.setSelectedItem("--");

        JComboBox CBstartFilter = new JComboBox(new String[]{">","<=","--"});
        CBstartFilter.setBounds(255,180,20,20);
        CBstartFilter.setSelectedItem("--");

        JComboBox CBendFilter = new JComboBox(new String[]{">","<=","--"});
        CBendFilter.setBounds(305,180,20,20);
        CBendFilter.setSelectedItem("--");

        JComboBox CBpriceFilter = new JComboBox(new String[]{">","<=","--"});
        CBpriceFilter.setBounds(362,180,20,20);
        CBpriceFilter.setSelectedItem("--");





        /*JCalendar calendar = new JCalendar();*/


        /*this.add(CBpriceFilter);
        this.add(CBendFilter);
        this.add(CBstartFilter);
        this.add(CBlimitFilter);
        this.add(Bfilter);

        this.add(Tprice);
        this.add(Tend);
        this.add(Tstart);
        this.add(Tlimit);


        this.add(Bsearch);


        this.add(CBprice);
        this.add(Lprice);
        this.add(CBend);
        this.add(Lend);
        this.add(CBstart);
        this.add(Lstart);
        this.add(CBlimit);
        this.add(Llimit);




        this.add(Border);*/

        /*filter*/


        /*combobox*/
        this.add(CBid);
        this.add(CBname);

        /*textfile*/
        this.add(Tid);
        this.add(Tname);

        /*label*/
        this.add(Lid);
        this.add(Lname);

        this.add(Ptable);


        System.out.println(CBid.getSelectedItem());
        System.out.println(CBname.getSelectedItem());
        System.out.println(CBlimit.getSelectedItem());

        Border.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ptable.sortTable(
                        (String)CBid.getSelectedItem(),
                        (String)CBname.getSelectedItem(),
                        (String) CBlimit.getSelectedItem(),
                        (String)CBstart.getSelectedItem(),
                        (String)CBend.getSelectedItem(),
                        (String) CBprice.getSelectedItem()
                );
            }
        });

        Bsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Ptable.searchingTable(
                            Tid.getText(),
                            Tname.getText(),
                            Tlimit.getText(),
                            Tstart.getText(),
                            Tend.getText(),
                            Tprice.getText()
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        Bfilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Ptable.filterTable(
                            Tid.getText(),
                            Tname.getText(),
                            Tlimit.getText(),
                            Tstart.getText(),
                            Tend.getText(),
                            Tprice.getText(),

                            (String)CBlimitFilter.getSelectedItem(),
                            (String)CBstartFilter.getSelectedItem(),
                            (String)CBendFilter.getSelectedItem(),
                            (String)CBpriceFilter.getSelectedItem()
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
