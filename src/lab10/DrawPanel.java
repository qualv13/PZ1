package lab10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel(){
//        shapes.add(new Branch(160, 340, 3,1,1));
//        shapes.add(new Branch(210, 320, 2.5,1,1));
//        shapes.add(new Branch(260, 300, 2,1,1));
//        shapes.add(new Branch(285, 280, 1.75,1,1));
//        shapes.add(new Branch(310, 260, 1.5,1,1));
//        shapes.add(new Branch(335, 230, 1.25,1,1));
//        shapes.add(new Branch(360, 210, 1,1,1));
//        shapes.add(new Branch(385, 160, 1, 0.75, 1.25));
        shapes.add(new Background());
        shapes.add(new Tree(20, -50, 1));
        setBackground(new Color(22, 36, 115));
//        shapes.add(new Bucket(250,400));
//        shapes.add(new Bucket(400,10));
//        shapes.add(new Bucket(400,400));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

}