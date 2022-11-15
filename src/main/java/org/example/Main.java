package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Введите запрос: ");
        Scanner sc = new Scanner(System.in);
        Engine.execute(sc.nextLine());
    }
}