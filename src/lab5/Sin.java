package lab5;

public class Sin extends Node{
    double a;

    public Sin(double x) {
        this.a = x;
    }

    @Override
    double evaluate() {
        double b = Math.toRadians(a);
        return Math.sin(b);
    }

    @Override
    public String toString() {
        return "sin(" + a + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Cos(var.evaluate() - this.a);
    }

    @Override
    boolean isZero(){
        return a%180 == 0;
    }
}
