package User.View.UserAbility.BuyPackage;

import javax.swing.*;
import java.awt.*;

public class PackageInfoUI extends JPanel{

    public PackageInfoUI(){
        this.setLayout(new FlowLayout());/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("PackageInfo"));
        this.setVisible(true);
    }
}
