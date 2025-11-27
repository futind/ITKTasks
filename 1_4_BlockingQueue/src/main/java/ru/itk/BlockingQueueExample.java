package ru.itk;

public class BlockingQueueExample {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new BlockingQueue<>(5);

        Thread producer = new Thread(
                () -> {
                    try {
                        for(int i = 0; i < 10; i++) {
                            queue.enqueue("item" + i);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread consumer = new Thread(
                () -> {
                    try {
                        for(int i = 0; i < 11; i++) {
                            Thread.sleep(i * 500);
                            queue.dequeue();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        producer.start();
        consumer.start();

        try {
            producer.join(100);
            consumer.join(100);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
