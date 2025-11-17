package ru.itk;

public class ComplexTask {

    private final int taskNumber;

    public ComplexTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String execute() {
        try {
            Thread.sleep(taskNumber * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + "-task-" + taskNumber + " executed");

        return Thread.currentThread().getName() + "-task-" + taskNumber;
    }
}
