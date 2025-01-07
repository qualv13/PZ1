package lab11;

import java.time.LocalTime;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class ClockThread extends Thread{
    LocalTime time = LocalTime.now();
    @Override
    public void run() {
        ClockWithGUI clock = new ClockWithGUI();

        while(true){
            time = LocalTime.now();
            System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());
            ClockWithGUI.main();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        new ClockThread().start();


    }

}