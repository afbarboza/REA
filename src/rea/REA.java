/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import View.*;

import Model.Buffer;

/**
 *
 * @author alex
 */
public class REA {

    public static void main(String[] args) {
       MainFrame mf = new MainFrame();       
       mf.setVisible(true);
       
       Buffer b = Buffer.getInstance();
       Buffer b1 = Buffer.getInstance();
    }
    
}
