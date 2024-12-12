package lab10;

import java.awt.*;

public class Snowflakes implements XmasShape {
    int x, y; int scale;

    Snowflakes(int x, int y, int scale) {
        this.x = x; this.y = y; this.scale = scale;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.scale(scale,scale);
        g2d.translate(x,y);
    }

    @Override
    public void render(Graphics2D g2d) {
        String myString = "\u2744";
        g2d.setColor(new Color(104, 232, 253));
        g2d.drawString(myString,x,y);
    }
}
