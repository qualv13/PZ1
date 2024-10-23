package lab1;

import java.util.Scanner;

public class Problem610A {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if(n%2!=0){
            System.out.print(0);
            return;
        }
        int count = 0;
        n = n/2;
        for(int i = 1; i<n; i++){
            if(i < n-i){count++;}
        }
        System.out.print(count);
    }
}
