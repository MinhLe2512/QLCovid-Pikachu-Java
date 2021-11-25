/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlcovid;

/**
 *
 * @author nhonnhon
 */
public class Manager {
    ManagerUI mgUI;
    Manager(){
        mgUI = new ManagerUI();
        mgUI.setLocationRelativeTo(null);
        mgUI.setVisible(true);
        mgUI.setSize(1600, 900);
    }
}
