package User.View.Infoview.UserAbility;

import javax.swing.*;
import java.awt.*;


public class ManagementUI extends JPanel {
    public ManagementUI(){
        this.setLayout(new FlowLayout());/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("Mana"));
        this.setVisible(false);
    }
}