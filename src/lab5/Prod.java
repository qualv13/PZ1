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
        Constant constant = new Constant(c);
        args.add(constant);
        //wywołaj konstruktor jednoargumentowy przekazując new Constant(c)
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        Constant constant = new Constant(c);
        args.add(constant);
        args.add(n);
        //wywołaj konstruktor dwuargumentowy
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        //???
        Constant constant = new Constant(c);
        args.add(constant);
        return this;
    }

    @Override
    double evaluate() {
        double result =1;
        for (Node arg : args) {
            result *= arg.evaluate();
        }
        // oblicz iloczyn czynników wołąjąc ich metodę evaluate
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        for(int i = 0; i < args.size(); i++){
            if(args.get(i).sign<0)b.append("(");
            b.append(args.get(i).toString());
            if(args.get(i).sign<0)b.append(")");
            if(i<args.size()-1) b.append("*");
        }
        return b.toString();
    }

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i=0;i<args.size();i++){
            Prod m = new Prod();
            for(int j=0;j<args.size();j++){
                Node f = args.get(j);
                if(j==i) m.mul(f.diff(var));
                else m.mul(f);
            }
            if(!m.isZero()){
                r.add(m);
            }
        }
        return r;
    }

    @Override
    boolean isZero(){
        if(args.isEmpty()) return true;
        for (Node arg : args) {
            if(arg.isZero()) return true;
        }
        return false;
    }
}
