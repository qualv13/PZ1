package lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Background implements XmasShape {
    List<XmasShape> shapes = new ArrayList<>();

    Background(){
        for(int i = 0; i<50; i++){
            for(int j = 0; j<50; j++){
                if(j%2==0){
                    shapes.add(new Snowflakes(j*8, i*8, 1));
                }else{
                    shapes.add(new Snowflakes(j*8, i*8-6, 1));
                }
                //shapes.add(new Snowflakes(i*15, j*15, 1));
            }
        }
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(0,0);
        g2d.scale(2,2);
    }

    @Override
    public void render(Graphics2D g2d) {
        for(var b:shapes)b.draw(g2d);
    }
}
