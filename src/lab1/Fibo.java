package lab1;

import java.util.Scanner;

public class Fibo {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        if(i>0 && i<46){
            int[] tab = new int[i];
            tab[0] = 1;
            tab[1] = 1;
            for(int j = 2; j<i; j++){
                tab[j] = tab[j-1] + tab[j-2];
            }
            for(int j = 0; j<i; j++){
                System.out.println(tab[j]);
            }
        }

    }

}
