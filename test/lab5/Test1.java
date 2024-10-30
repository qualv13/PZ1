package lab5;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class Test1 {

    @org.junit.jupiter.api.Test
    void buildAndPrintTest(){
        Variable x = new Variable("x");
        String real = "2.1*x^3 + x^2 + (-2)*x + 7";
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
        assertEquals(exp.toString(),real, "It should be {real}");
        // 2.1*x^3 + x^2 + (-2)*x + 7
    }

    @org.junit.jupiter.api.Test
    void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        //System.out.println(exp.toString());
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    @org.junit.jupiter.api.Test
    void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());


        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }
}
