package lab11;

import java.time.LocalTime;

class ClockThread extends Thread{
    LocalTime time = LocalTime.now();
    @Override
    public void run() {
        while(true){
            time = LocalTime.now();
            System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        new ClockThread().start();
    }

//    @Override
//    public static

    private void repaint() {
        ClockWithGUI clock = new ClockWithGUI();
        ClockWithGUI.main();
    }


}