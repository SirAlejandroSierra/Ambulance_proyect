/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BITalino;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author hecyebesdelpino
 */
public class GraphicsDemo extends JPanel {
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //this.setBackground(Color.BLUE);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLUE);
        int[] axis ={10,20,30,40,50,60,70,80};
        int[] ecgValues = {30,50,60,200,70,500,-20,40};
        g2D.drawPolyline(axis, ecgValues, 8);
        
    }
}
