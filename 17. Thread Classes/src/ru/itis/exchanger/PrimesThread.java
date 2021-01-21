package ru.itis.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class PrimesThread implements Runnable {
    private int from;
    private int to;
    private int partSize;
    private Exchanger<List<Integer>> exchanger;

    public PrimesThread(int from, int to, int partSize, Exchanger<List<Integer>> exchanger) {
        this.from = from;
        this.to = to;
        this.partSize = partSize;
        this.exchanger = exchanger;
    }

    private boolean isPrimeSum(int number) {

        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }

        if (sum == 2 || sum == 3) {
            return true;
        }

        for (int i = 2; i * i <= sum; i++) {
            if (sum % i == 0) {
                return false;
            }
        }


        return true;
    }

    @Override
    public void run() {
        int currentIndex = 0;
        List<Integer> currentPart = new ArrayList<>();

        for (int i = from; i <= to; i++) {
            if (isPrimeSum(i)) {
                currentPart.add(i);
            }

            if (currentIndex == partSize) {
                // передать данные в другой поток
                try {
                    exchanger.exchange(currentPart);
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException(e);
                }
                currentPart = new ArrayList<>();
                currentIndex = 0;
            }
            currentIndex++;
        }
    }
}
