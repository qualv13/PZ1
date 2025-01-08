package lab12;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncMean {
    static double[] array;

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //initArray(100000000);
        //asyncMeanv1();
        asyncMeanv2();
    }

    public static void asyncMeanv1() {
        int size = 100_000_000;
        initArray(size);
        ExecutorService executor = Executors.newFixedThreadPool(16);
        int n=16;
        // Utwórz listę future
        List<CompletableFuture<Double>> partialResults = new ArrayList<>();
        for(int i=0;i<n;i++){
            CompletableFuture<Double> partialMean = CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(i*(size/n),i*(size/n)+size/n-1),executor);
            partialResults.add(partialMean);
        }
        // zagreguj wyniki
        double mean=0;
        for(var pr:partialResults){
            mean += pr.join();
            // wywołaj pr.join() aby odczytać wartość future;
            // join() zawiesza wątek wołający
        }
        mean /= partialResults.size();
        System.out.printf(Locale.US,"mean=%f\n",mean);

        executor.shutdown();
    }

    static void asyncMeanv2() throws InterruptedException {
        int size = 100_000_000;
        initArray(size);
        ExecutorService executor = Executors.newFixedThreadPool(16);
        int n=10;

        BlockingQueue<Double> queue = new ArrayBlockingQueue<>(n);
        double t1 = System.nanoTime()/1e6;
        for (int i = 0; i < n; i++) {
            CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(i*(size/n),i*(size/n)+size/n-1), executor)
            .thenApply(d -> queue.offer(d));
        }

        double mean=0;
        // w pętli obejmującej n iteracji wywołaj queue.take() i oblicz średnią
        for(int i = 0; i < n; i++){
            mean += queue.take();
        }
        mean /= n;

        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"mean=%f, t2-t1=%f\n", mean, t2-t1);

        executor.shutdown();
    }

    static class MeanCalcSupplier implements Supplier<Double> {
        //...
        int start; int end;
        MeanCalcSupplier(int start, int end){
            this.start = start; this.end = end;
        }

        @Override
        public Double get() {
            double mean=0;
            // oblicz średnią
            for (int i = start; i <= end; i++) {
                mean += array[i];
            }
            mean /= (end-start);
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
            return mean;
        }
    }
}
