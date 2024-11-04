package lab5;

import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

public class Test2 {

    @org.junit.jupiter.api.Test
    void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());
        assertEquals("2*x^3 + x^2 + (-2)*x + 7", exp.toString(), "Aren't same");

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());
        //assertEquals("0*x^3 + 2*3*x^2*1 + 2*x^1*1 + 0*x + (-2)*1 + 0", d.toString(), "Aren't same");
        assertEquals("2*3*x^2*1 + 2*x^1*1 + (-2)*1", d.toString(), "Aren't same");
        // exp=2*x^3 + x^2 + (-2)*x + 7
        // d(exp)/dx=0*x^3 + 2*3*x^2*1 + 2*x^1*1 + 0*x + (-2)*1 + 0
    }

    @org.junit.jupiter.api.Test
    void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());
        // f(x,y)=x^2 + y^2 + 8*x + 4*y + 16
        assertEquals("x^2 + y^2 + 8*x + 4*y + 16", circle.toString(), "Circle f isn't good");
        // d f(x,y)/dx=2*x^1*1 + 2*y^1*0 + 0*x + 8*1 + 0*y + 4*0 + 0
        //assertArrayEquals("2*x^1*1 + 2*y^1*0 + 0*x + 8*1 + 0*y + 4*0 + 0".toCharArray(), dx.toString().toCharArray(), "df dx isn't good");
        assertEquals("2*x^1*1 + 8*1", dx.toString(), "df dx isn't good");
        // d f(x,y)/dy=2*x^1*0 + 2*y^1*1 + 0*x + 8*0 + 0*y + 4*1 + 0
        //assertArrayEquals("2*x^1*0 + 2*y^1*1 + 0*x + 8*0 + 0*y + 4*1 + 0".toCharArray(), dy.toString().toCharArray(), "df dy isn't good");
        assertEquals("2*y^1*1 + 4*1", dy.toString(), "df dy isn't good");

    }
}
