package lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree implements XmasShape{
    List<XmasShape> shapes = new ArrayList<>();
    int wx; int wy; double scale;
    Tree(int x, int y, double wscale){
        wx = x; wy = y;
        scale = wscale;


        shapes.add(new Wood(430, 600, 60));
        for(int i = 0; i<7; i++){
            shapes.add(new Branch(160 + (i-1)*40,340 - (i-1)*20,3- (i-1)*0.4,1,1));

            if(i==6){
                shapes.add(new Branch(385, 190, 1, 0.8, 1.25));
            }
        }
        for(int i = 0; i<6; i++){
            shapes.add(new Bucket(180 + (i-1)*50,480 - (i-1)*50,1 ,1,600 - (i*90), 10-i));
        }
        shapes.add(new Star(23, 13, 9));
//        shapes.add(new Branch(160, 340, 3,1,1));
//        shapes.add(new Branch(210, 320, 2.5,1,1));
//        shapes.add(new Branch(260, 300, 2,1,1));
//        shapes.add(new Branch(285, 280, 1.75,1,1));
//        shapes.add(new Branch(310, 260, 1.5,1,1));
//        shapes.add(new Branch(335, 230, 1.25,1,1));
//        shapes.add(new Branch(360, 210, 1,1,1));
//        shapes.add(new Branch(385, 160, 1, 0.75, 1.25));


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(wx,wy);
        g2d.scale(scale, scale);

    }

    @Override
    public void render(Graphics2D g2d) {
        for(var b:shapes){
            b.draw(g2d);
        }
    }
}
