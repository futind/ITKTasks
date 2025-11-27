package ru.itk;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<Integer> {

    /**
     * A number factorial of which we need to find
     */
    private final int numberToFactor;

    FactorialTask(int n) {
        this.numberToFactor = n;
    }

    @Override
    protected Integer compute() {
        if (numberToFactor == 0) {
            return 1;
        } else {
            FactorialTask task = new FactorialTask(numberToFactor - 1);
            task.fork();
            return task.join();
        }
    }
}

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}