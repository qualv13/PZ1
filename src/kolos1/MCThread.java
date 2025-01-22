package kolos1;

import lab12.Mean;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class MCThread{
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static double[] array;

    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        initArray(100000000);
        for(int cnt:new int[]{1,2,4}){
            integrateParallel(cnt);
        }
    }

    static void integrateParallel(int cnt) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        MCThread.MCThreatCalc threads[]=new MCThread.MCThreatCalc[cnt];
        int count = array.length/cnt;
        for(int i=0;i<cnt;i++){
            // nie przekazują funkcji bo nie zdążyłem przeczytać dokumentacji
            //threads[i]=new MCThread.MCThreatCalc(i*count,i*count+count, i);
        }
        double t1 = System.nanoTime()/1e6;

        for(int i=0;i<cnt;i++){
            executor.execute(threads[i]);
        }
        double t2 = System.nanoTime()/1e6;

        double sum = 0;
        for(int i=0;i<cnt;i++){
            sum += results.take();
        }
        sum = sum/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f sum=%f\n",
                array.length,
                cnt, t2-t1, t3-t1, sum);
        executor.shutdown();
    }
//    public interface Function<T,R>{
//        for(int i = 0; i<c; i++){
//            array[i]= Math.random()*n/(i+1);
//
//        }
//    }

    static class MCThreatCalc extends Thread {
        double sum=0;
        private double a;
        private double b;
        private int c;

        // Nie wiedziałem jak dać przełożyć funkcję według dokumentacji, nie zdążyłem się wczytać
//        MCThreadCalc(double a, double b, int c){
//            this.a=a;
//            this.b=b;
//            this.c=c;
//        }

        double f(double x){
            return a*x + b;
        }

        public void run(){
            double sum = 0;
            for(int i= (int)a;i<b;i++){
                sum+=f(i)*(b-a)/c;
            }

            try {
                results.put(sum);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.sum = sum;
        }
    }
}
