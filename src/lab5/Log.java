package lab5;

public class Log extends Node{
    Variable x;

    Log(Variable x){
        this.x = x;
    }

    @Override
    double evaluate() {
        return Math.log(x.value);
    }

    @Override
    public String toString() {
        return "log(" + x.toString() + ")";
    }

    @Override
    Node diff(Variable var) {
        return new Prod(1,new Power(var,-1));
    }

    @Override
    boolean isZero(){
        return x.value == 1;
    }
}
