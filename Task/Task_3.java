package Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task_3 {

    public static void main(String[] args) {
        String jsonString = loadJsonFromFile("data/Task_3.json");

        List<Student> students = parseJson(jsonString);

        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append("Student ")
                    .append(student.getLastName())
                    .append(" received ")
                    .append(student.getGrade())
                    .append(" by subject ")
                    .append(student.getSubject())
                    .append(".\n");
        }

        System.out.println(sb.toString());
    }

    private static String loadJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static List<Student> parseJson(String jsonString) {
        List<Student> students = new ArrayList<>();

        jsonString = jsonString.substring(1, jsonString.length() - 1); // Удаляем квадратные скобки в начале и конце строки
        String[] entries = jsonString.split("},"); // Разделяем записи студентов

        for (String entry : entries) {
            String[] fields = entry.split(","); // Разделяем поля в каждой записи

            String lastName = fields[0].split(":")[1].replace("\"", "").trim();
            String grade = fields[1].split(":")[1].replace("\"", "").trim();
            String subject = fields[2].split(":")[1].replace("\"", "").trim();

            students.add(new Student(lastName, grade, subject));
        }

        return students;
    }

    static class Student {
        private String lastName;
        private String grade;
        private String subject;

        public Student(String lastName, String grade, String subject) {
            this.lastName = lastName;
            this.grade = grade;
            this.subject = subject;
        }

        public String getLastName() {
            return lastName;
        }

        public String getGrade() {
            return grade;
        }

        public String getSubject() {
            return subject;
        }
    }
}