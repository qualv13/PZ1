package lab1;// Tylko jedna publiczna klasa i musi być o takiej samej nazwie jak plik

import java.util.Scanner;


public class HelloWorld {
    public static void main(String [] args){
//        System.out.println("Hello world!!");
//        System.out.printf("String %s int %d double %f", "dsadasd",2,2.5);
//        System.out.print("...");

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s, %d, %f",s,i,d);
    }
}

