# Number generation
## Task
The goal was to use parallel stream in order to process the data and creating
a map where key is a class and value is an average grade by all students
attending that class.

## Code
```java
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
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );
    }
}
```

## Launch
You would need to have **JDK 17** and **Apache Maven**.

Use the following commands:
```shell
mvn compile
mvn exec:java
```