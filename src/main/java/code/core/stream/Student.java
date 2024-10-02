package code.core.stream;

import lombok.Data;

import java.util.Map;

@Data
class Student {
    private String name;

    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }
}

