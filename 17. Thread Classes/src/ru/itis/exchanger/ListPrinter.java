package ru.itis.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ListPrinter implements Runnable {

    private Exchanger<List<Integer>> exchanger;

    public ListPrinter(Exchanger<List<Integer>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            List<Integer> currentList = null;
            try {
                currentList = exchanger.exchange(null);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }
            // получить список из другого потока
            System.out.println(currentList);
        }
    }
}
