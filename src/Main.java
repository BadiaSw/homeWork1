import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.
    static final int MAX_STUDENTS = 100;
    static Map<String, ArrayList<Double>> students;

    public static void manageGrades() {
        students = new HashMap<>();


        System.out.println("Welcome to the Students Grade Management System!");
        while (true) {
            PrintMenu();
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            if (scanner.hasNext()) {
                scanner.nextLine();}
            switch (choice) {
                case 1:
                    AddStudent();
                    break;
                case 2:
                    DisplayAllStudents();
                    break;
                case 3:
                    CalculateStudentAverage();
                    break;
                case 4:
                    FindTopStudent();
                    break;
                case 5:
                    System.out.println("Exiting the program Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
        // Printing Thr Menu func
        private static void PrintMenu() {
            System.out.println("1. Add a new student");
            System.out.println("2. Display all students");
            System.out.println("3. Calculate a student's average grade");
            System.out.println("4. Find top performing student");
            System.out.println("5. Exit");
        }

        private static void AddStudent() {
            if (students.size() >= MAX_STUDENTS) {
                System.out.println("Student limit reached");
                return;
            }

            System.out.println("Enter student name:");
            String studentName = scanner.nextLine().trim();
            System.out.println("Enter student grades:");
            String[] studentGrades = scanner.nextLine().trim().split(" ");
            ArrayList<Double> grades = new ArrayList<>();
            for (String grade : studentGrades) {
                double gradeValue = Double.parseDouble(grade);
                if (gradeValue < 0 || gradeValue > 100) {
                    System.out.println("Invalid grades");
                    return;
                }
                    grades.add(gradeValue);

            }
                students.put(studentName, grades);
                System.out.println("Student " + studentName + " added successfully");

        }

        //-------------------------------------------------------------------------
        private static void DisplayAllStudents () {
            if (students.isEmpty()) {
                System.out.println("No students records available");
                return;
            }
            for (Map.Entry<String, ArrayList<Double>> entry : students.entrySet()) {


                System.out.print("Name: " + entry.getKey() + ", Grades: ");
                for (int i = 0; i < entry.getValue().size(); i++) {
                    System.out.print(entry.getValue().get(i) + " ");
                    if (i < entry.getValue().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }

        //-------------------------------------------------------------------------
        private static void CalculateStudentAverage () {
            System.out.println("Enter student name: ");
            String studentName = scanner.nextLine();
            if (!students.containsKey(studentName)) {
                System.out.println("No student found with name " + studentName);
                return;
            }
            ArrayList<Double> averageGrades = students.get(studentName);
            if (averageGrades.isEmpty()) {
                System.out.printf("Average grade for %s:0.00%n", studentName);
            } else {
                double sum = 0.0;
                for (Double grade : averageGrades) {
                    sum += grade;
                }
                double average = sum / averageGrades.size();
                System.out.printf("Average grade for %s:%.2f%n", studentName, average);

            }
        }

        //-------------------------------------------------------------------------
        // Building a func just like the calculateStudentAverage just making it return the value
        private static double StudentAverage (String Name){
            List<Double> grades = students.get(Name);
            double sum = 0.0;
            for (Double grade : grades) {
                sum += grade;
            }
            return sum / grades.size();
        }

        private static void FindTopStudent () {
            if (students.isEmpty()) {
                System.out.println("No students records available");
                return;
            }
            String TopStudent = null;
            double highestAverage = 0.0;
            for (String Name : students.keySet()) {
                double average = StudentAverage(Name);
                if (average > highestAverage) {
                    TopStudent = Name;
                    highestAverage = average;
                }
            }
            System.out.printf("Top performing student: %s with an average grade of:%.2f%n", TopStudent, highestAverage);


        }

        public static void main (String[]args) throws IOException {
            String path = "C:\\Users\\badea\\Downloads\\hw1 (3)\\HW1_input.txt";
            scanner = new Scanner(new File(path));
            int numberOfTests = scanner.nextInt();
            scanner.nextLine();
            for (int i = 1; i <= numberOfTests; i++) {
                System.out.println("Test number " + i + " starts.");
                try {
                    manageGrades();
                } catch (Exception e) {
                    System.out.println("Exception " + e);
                }
                System.out.println("Test number " + i + " ended.");
                System.out.println("-----------------------------------------------");
            }
            System.out.println("All tests have ended.");
        }
    }
