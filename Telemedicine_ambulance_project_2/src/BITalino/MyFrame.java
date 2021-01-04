/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BITalino;

import javax.swing.*;

/**
 *
 * @author hecyebesdelpino
 */
public class MyFrame extends JFrame {
    
    GraphicsDemo graphicsDemo = new GraphicsDemo();
    
    public MyFrame (){
        this.setSize(220,220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphicsDemo);
        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }




}