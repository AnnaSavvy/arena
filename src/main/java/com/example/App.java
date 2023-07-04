package com.example;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("press 1");
        System.out.println("press 2");
        System.out.println("press 3");
        Scanner input = new Scanner(System.in);
        int opt1 = input.nextInt();
        if (opt1 == 1) {
            System.out.println("Game started");
        } else if (opt1 == 2) {
            System.out.println("Exit");
        } else if (opt1 == 3) {
            System.out.println("Credits");
        }
    }

}
