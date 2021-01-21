package ru.itis.exchanger;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        ListPrinter printer = new ListPrinter(exchanger);
        PrimesThread primesThread = new PrimesThread(0, 2147000000, 50000, exchanger);

        new Thread(printer).start();
        new Thread(primesThread).start();
    }
}
