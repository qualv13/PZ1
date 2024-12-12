package lab10;

import java.awt.*;

public class Wood implements XmasShape{
    int lx,rx, y; int size;
    Wood(int rx, int wy, int size){
        this.rx=rx; this.lx=lx; this.y=wy; this.size=size;

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(rx,y);
        g2d.scale(1,1);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(119, 71, 36),0,150, new Color(81, 45, 9));
        g2d.setPaint(grad);
        g2d.fillRect(0,0,size,150);
        g2d.setColor(Color.black);
        g2d.drawRect(0,0,size,150);
    }
}
