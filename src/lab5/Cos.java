package lab5;

public class Cos extends Node{
    double a;

    public Cos(double x) {
        this.a = x;
    }

    @Override
    double evaluate() {
        double b = Math.toRadians(a);
        return Math.cos(b);
    }

    @Override
    public String toString() {
        return "cos(" + a + ")";
    }

    @Override
    Node diff(Variable var) {
        Node n = new Sin(var.evaluate() - this.a); n.minus();
        return n;
    }

    @Override
    boolean isZero(){
        return (a+90)%180 == 0;
    }
}
