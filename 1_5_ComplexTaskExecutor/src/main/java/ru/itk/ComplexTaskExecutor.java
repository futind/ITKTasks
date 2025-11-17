package ru.itk;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {

    int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks(int numberOfTasks) {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        List<Future<String>> futureResults = new ArrayList<Future<String>>(numberOfTasks);

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> System.out.println(Thread.currentThread().getName() + "- Reached barrier"));

        for(int i = 0; i < numberOfTasks; i++) {
            int taskNumber = i;
            Callable<String> task = () -> {
                String result = new ComplexTask(taskNumber).execute();

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }

                return result;
            };

            futureResults.add(executor.submit(task));
        }

        StringBuilder completeResult = new StringBuilder("Complete result: ");
        for(Future<String> future : futureResults) {
            try {
                completeResult.append(future.get()).append(" ");
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(Thread.currentThread().getName() + " " + completeResult);

        executor.shutdown();
        try {
            if (!executor.awaitTermination(5,TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


}
