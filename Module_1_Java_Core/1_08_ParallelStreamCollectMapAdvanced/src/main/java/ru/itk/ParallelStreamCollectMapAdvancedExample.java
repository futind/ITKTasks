package ru.itk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private String name;
    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}

public class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        // 1. Create a list of students which contains information about classes they're attending and their grade
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        // 2. Use parallel stream in order to process the data and creating a map where key is a class
        // and value is an average grade by all students attending that class
        System.out.println("2. Average grade by the class: " +
                students.parallelStream()
                        .flatMap(student -> student.getGrades().entrySet().stream())
                        .collect(Collectors.groupingByConcurrent(Map.Entry::getKey, Collectors.averagingInt(Map.Entry::getValue)))
        );
    }
}