package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGUI extends JPanel {

    LocalTime time = LocalTime.now();

    public static void main() {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGUI());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }

    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        g2d.drawOval(-getWidth()/4,-getHeight()/4,getWidth()/2,getHeight()/2);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());

            //godzinowa
            AffineTransform saveAT = g2d.getTransform();
            g2d.rotate(time.getHour()%12*2*Math.PI/12 + time.getMinute()%60*2*Math.PI/12/60);
            g2d.drawLine(0,0,0,-80);
            g2d.setTransform(saveAT);

            //minutowa
            AffineTransform saveAT2 = g2d.getTransform();
            g2d.rotate(time.getMinute()%60*2*Math.PI/60);
            g2d.drawLine(0,0,0,-100);
            g2d.setTransform(saveAT2);

            //sekundowa
            AffineTransform saveAT3 = g2d.getTransform();
            g2d.rotate(time.getSecond()%60*2*Math.PI/60);
            g2d.drawLine(0,0,0,-120);
            g2d.setTransform(saveAT3);

            //repaint();
        }
    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                //sleep(1000);
                repaint();
            }
        }
    }

//    public void repaint(Graphics g){
//        Graphics2D g2d=(Graphics2D)g;
//        g2d.translate(getWidth()/2,getHeight()/2);
//
//        g2d.drawOval(-getWidth()/4,-getHeight()/4,getWidth()/2,getHeight()/2);
//
//        for(int i=1;i<13;i++){
//            AffineTransform at = new AffineTransform();
//            at.rotate(2*Math.PI/12*i);
//            Point2D src = new Point2D.Float(0,-120);
//            Point2D trg = new Point2D.Float();
//            at.transform(src,trg);
//            g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());
//
//            //godzinowa
//            AffineTransform saveAT = g2d.getTransform();
//            g2d.rotate(time.getHour()%12*2*Math.PI/12 + time.getMinute()%60*2*Math.PI/12/60);
//            g2d.drawLine(0,0,0,-80);
//            g2d.setTransform(saveAT);
//
//            //minutowa
//            AffineTransform saveAT2 = g2d.getTransform();
//            g2d.rotate(time.getMinute()%60*2*Math.PI/60);
//            g2d.drawLine(0,0,0,-100);
//            g2d.setTransform(saveAT2);
//
//            //sekundowa
//            AffineTransform saveAT3 = g2d.getTransform();
//            g2d.rotate(time.getSecond()%60*2*Math.PI/60);
//            g2d.drawLine(0,0,0,-120);
//            g2d.setTransform(saveAT3);
//        }
//    }
}
