package User.View.UserAbility.showInfo;

import javax.swing.*;
import java.awt.*;

public class BalanceUI extends JPanel{

    public BalanceUI(){
        this.setLayout(new FlowLayout());/////////////// REMEMBER REPLACE THIS LAYOUT!!!!!!!!!!!
        this.setBackground(new Color(0xC2FFF9)); // for debug
        this.setBounds(0,80,400,390);
        this.add(new JButton("Balance"));
        this.setVisible(false);
    }
}
