package User.View.UserAbility.BuyPackage;

import javax.swing.*;
import java.awt.*;

public class PackageLookUpUI extends JPanel{

    public PackageLookUpUI(){
        this.setLayout(new FlowLayout());/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("PackageLookUpUI"));
        this.setVisible(false);
    }
}
