package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainLab5 {
    public static void main(String[] args) {
        // Funkcja wypisująca 100 punktów z koła
        defineCircle();
        // test 1 ze strony
        buildAndPrint();
        // test 2 ze strony
        buildAndEvaluate();
        // gradient prosty
        testOptimization();
    }

    public static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

        List<Double> x_cord = new ArrayList<Double>();
        List<Double> y_cord = new ArrayList<Double>();
        double xv; double yv;
        while (x_cord.size() < 100){
            xv = 100*(Math.random()-.5);
            yv = 100*(Math.random()-.5);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            if(fv<0){x_cord.add(xv); y_cord.add(yv);}
        }

        while (!y_cord.isEmpty()){
            System.out.printf(String.format("(%f,%f)%n", x_cord.getFirst(), y_cord.getFirst()));
            x_cord.removeFirst(); y_cord.removeFirst();
        }
        //System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));
    }

    public static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
        // 2.1*x^3 + x^2 + (-2)*x + 7
    }

    static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            System.out.printf(Locale.US, "f(%f)=%f\n", v, exp.evaluate());
        }
    }

    static void testOptimization(){
        // rozmiar problemu
        int n=10;
        // hiperparametry
        int maxIter=1000;
        double learningRate=0.1;

        Random rand = new Random();
        // Utwórz zmienne
        List<Variable> vars= new ArrayList<>();
        for(int i=0;i<n;i++){
            vars.add(new Variable("var"+i));
        }

        // Zdefiniuj funkcję celu jako sum(a_i*x_i^2)
        Sum goal = new Sum();
        for(int i=0;i<n;i++){
            double a = rand.nextDouble();
            Node b = new Power(vars.get(i),2);
            goal.add(new Prod(new Constant(a),b));
        }

        // Wyznacz symbolicznie gradienty funkcji celu względem zmiennych x_i
        ArrayList<Node> grads = new ArrayList<>();
        for(int i=0;i<vars.size();i++){
            grads.add(goal.diff(vars.get(i)));
        }

        // Wylosuj poczatkowe wartosci zmiennych z przedziału [-500,500]
        for(int i=0;i<vars.size();i++){
            vars.get(i).setValue(rand.nextDouble()*1000-500);
        }

        // iteracja
        double[]grad_values = new double[n];
        for(int iter=0;iter<maxIter;iter++){

            // oblicz wartosc funkcji celu
            double goal_value = goal.evaluate();
            System.out.printf(Locale.US,"[%d] goal:%f\n",iter,goal_value);

            // oblicz wartosci gradientów
            // symboliczne gradienty korzystają z tych samych zmiennych, co funkcja celu
            for(int i=0;i<n;i++){
                grad_values[i] = grads.get(i).evaluate();
            }

            // uaktualnij wartosci zmiennych wg. wzoru x_i = x_i-lr*gradient_i
            for(int i=0;i<n;i++){
                Variable v = vars.get(i);
                v.setValue(v.getValue()-learningRate*grad_values[i]);
            }
        }
        double goal_value = goal.evaluate();
        System.out.printf(Locale.US,"[at end] goal:%f",goal_value);
    }
}
