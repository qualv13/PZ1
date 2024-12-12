package lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Branch implements XmasShape{
    int bx; int by; double scale; double xscale; double yscale;
    Color fillColor = new Color(57, 144, 38);
    Color borderColor = new Color(51, 106, 25);
    List<XmasShape> shapes = new ArrayList<>();

    Branch(int wx, int wy, double wscale, double xs, double ys){
        bx = wx; by = wy; scale = wscale; xscale=xs; yscale=ys;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(bx,by);
        g2d.scale(scale*xscale,scale*1.7*yscale);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,fillColor,0,50, borderColor);
        g2d.setPaint(grad);
        //g2d.setColor(fillColor);
        int x[]={100,-10,20,40,60,80,100,120,140,160,180,210};
        int y[]={0,40,50,40,50,40,50,40,50,40,50,40,50,40};
        g2d.fillPolygon(x,y,x.length);
//        for(int i=1; i<x.length-1; i++){
//            Random r = new Random();
//            Bubble b = new Bubble();
//            b.x=x[i];
//            b.y=y[i];
//            b.scale=r.nextDouble()*0.2;
//            b.fillColor=new Color(144, 38, 38);
//            b.lineColor=new Color(130, 38, 144);
//            shapes.add(b);
//        }
//        for(var b:shapes)b.draw(g2d);



//        g2d.setColor(new Color(57, 144, 38));
//        g2d.fillRect(0,0,100,100);
    }
}
