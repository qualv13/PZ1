package lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bucket implements XmasShape {
    List<XmasShape> shapes = new ArrayList<>(); double scale; double xscale; double yscale; int num;
    int x;
    int y;
    Bucket(int x,int y, double wscale, double xs, double ys, int num){
        this.scale=wscale; this.num=num;
        this.x=x; this.xscale=xs;
        this.y=y; this.yscale=ys;
        Random r = new Random();
//        for(int i=0;i<10;i++){
//            Bubble b = new Bubble();
//            b.x=r.nextInt(200);
//            b.y=r.nextInt(30);
//            b.scale=r.nextDouble()*0.2;
//            b.fillColor=new Color(182, 30, 70);
//            b.lineColor=new Color(37, 18, 209);
//            shapes.add(b);
//        }
        for(int i=0;i<num;i++){
            Bubble b = new Bubble();
            b.x=r.nextInt((int) (yscale-xscale));
            b.y=r.nextInt(60);
            b.scale=r.nextDouble()*0.2;
            b.fillColor=new Color(r.nextFloat(),r.nextFloat(),r.nextFloat());
            b.lineColor=new Color(r.nextFloat(),r.nextFloat(),r.nextFloat());
            shapes.add(b);
        }
    }
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(230, 11, 11));
        //g2d.fillRect(0,0,100,100);
        for(var b:shapes)b.draw(g2d);
    }
}
