package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите запрос: ");
        Engine.execute(sc.nextLine());

    }
}