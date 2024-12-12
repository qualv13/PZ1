package lab10;

import java.awt.*;

public class Star implements XmasShape{
    int x,y; int scale;
    Star(int x, int y, int scale){
        this.x = x; this.y = y; this.scale = scale;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(scale,scale);
        g2d.translate(x,y);
    }

    @Override
    public void render(Graphics2D g2d) { //2738
        String myString = "\u2605";

        GradientPaint grad = new GradientPaint(0,0,new Color(255, 227, 106),20,20, new Color(241, 231, 20));
        g2d.setPaint(grad);
//        Font font = new Font("Segoe UI Emoji", Font.PLAIN, 96); //Serif
//        g2d.setFont(font);
//        String star = "?";

        g2d.drawString(myString,x,y);
    }
}
