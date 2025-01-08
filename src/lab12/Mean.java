package lab12;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mean {
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static double[] array;
    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128,256,512,1024,2048}){
            //parallelMeanV1(cnt);
            //parallelMeanV2(cnt);
            parallelMeanV3(cnt);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanV1(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        int count = array.length/cnt;
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        for(int i=0;i<cnt;i++){
            threads[i]=new MeanCalc(i*count,i*count+count);
        }
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(int i=0;i<cnt;i++){
            threads[i].start();
        }
        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''
        for(MeanCalc mc:threads) {
            mc.join();
        }
        // oblicz średnią ze średnich
        double mean = 0;
        for(int i=0;i<cnt;i++){
            mean += threads[i].mean;
        }
        mean = mean/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt, t2-t1, t3-t1, mean);
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanV2(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        int count = array.length/cnt;
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        for(int i=0;i<cnt;i++){
            threads[i]=new MeanCalc(i*count,i*count+count);
        }
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(int i=0;i<cnt;i++){
            threads[i].start();
        }
        double t2 = System.nanoTime()/1e6;
        // oblicz średnią ze średnich
        double mean = 0;
        for(int i=0;i<cnt;i++){
            mean += results.take();
        }
        mean = mean/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt, t2-t1, t3-t1, mean);
    }

    static void parallelMeanV3(int cnt) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        int count = array.length/cnt;
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        for(int i=0;i<cnt;i++){
            threads[i]=new MeanCalc(i*count,i*count+count);
        }
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for(int i=0;i<cnt;i++){
            executor.execute(threads[i]);
        }
        double t2 = System.nanoTime()/1e6;
        // oblicz średnią ze średnich
        double mean = 0;
        for(int i=0;i<cnt;i++){
            mean += results.take();
        }
        mean = mean/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt, t2-t1, t3-t1, mean);
        executor.shutdown();
    }


    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;

        }
        public void run(){
            // ??? liczymy średnią
            double sum = 0;
            for(int i=start;i<end;i++){
                sum+=array[i];
            }
            mean = sum/(end-start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }

    }

}

