package lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        //wywołaj konstruktor jednoargumentowy przekazując new Constant(c)
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        //wywołaj konstruktor dwuargumentowy
    }



    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        //???
        return this;
    }


    @Override
    double evaluate() {
        double result =1;
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        // ...
        //zaimplementuj
        return b.toString();
    }


}
