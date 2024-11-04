package lab5;

public class Exp extends Node{
    double value;
    Exp(double value){
        this.value = value<0?-value:value;
    }

    @Override
    double evaluate() {
        return Math.exp(value);
    }

    @Override
    public String toString() {
        return "e";
    }

    @Override
    Node diff(Variable var) {
        return new Exp(value);
    }

    @Override
    boolean isZero(){
        return false;
    }
}
