package ru.itk;

public class BlockingQueue<T> {

    private final Object[] queue;

    private int rear = 0;
    private int front = 0;
    private int size = 0;

    public BlockingQueue() {
        this.queue = new Object[Integer.MAX_VALUE];
    }

    public BlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }

        this.queue = new Object[capacity];
    }

    public synchronized void enqueue(T item) throws InterruptedException {

        while (size == queue.length) {
            System.out.println(Thread.currentThread().getName() + ": queue is full");
            wait();
        }

        queue[rear] = item;

        rear = (rear + 1) % queue.length;
        size++;

        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {

        while (size == 0) {
            System.out.println(Thread.currentThread().getName() + ": queue is empty");
            wait();
        }

        T result = (T) queue[front];
        queue[front] = null;

        front = (front + 1) % queue.length;
        size--;

        notifyAll();

        return result;
    }

    public synchronized int size() {
        return size;
    }
}
