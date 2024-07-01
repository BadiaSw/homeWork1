import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.
    static final int MAX_STUDENTS = 100;//defining the max number of students held in the system
    static Map<String, ArrayList<Double>> students; // declaring the hash map so it's a global

    public static void manageGrades() {
        students = new HashMap<>(); // Restarting the hash map every time the func is called


        System.out.println("Welcome to the Student Grade Management System!");
        while (true) { // This runs every time until closing the system
            PrintMenu(); // calling the func to print the menu
            System.out.println("Please enter your choice:");
            int choice = scanner.nextInt();
            if (scanner.hasNext()) { // checking if there is a command

                scanner.nextLine();
            }
            switch (choice) { // Using a switch for every option available every option calls a func or printing
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
                    System.out.println("Exiting the program. Goodbye!");
                    return; // returning from the func
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
            System.out.println("4. Find the top performing student");
            System.out.println("5. Exit");
        }

        private static void AddStudent() {
            if (students.size() >= MAX_STUDENTS) { // checking if the limit is reached
                System.out.println("Student limit reached");
                return;
            }

            System.out.println("Enter student name:");
            String studentName = scanner.nextLine(); // reading the name
            System.out.println("Enter grades:");
            String[] studentGrades = scanner.nextLine().split(" ");// reading the grades and putting them in a String List
            ArrayList<Double> grades = new ArrayList<>();// A new ArrayList so we can store it in the hashMap
            for (String grade : studentGrades) { // a Loop on every element in the list
                double gradeValue = Double.parseDouble(grade);// We Change the element type to double, so we can compare it and check if it's Valid
                if (gradeValue < 0 || gradeValue > 100) {
                    System.out.println("Invalid grades.");
                    return; // an invalid grade so we close the func and return
                }
                    grades.add(gradeValue); // Now storing the element in the ArrayList

            }
                students.put(studentName, grades);//now we store the name and the grades in the hashMap
                // if the name already in the HashMap it @Override because that's how the func Put() build
                System.out.println("Student " + studentName + " added successfully!");

        }

        //-------------------------------------------------------------------------
        private static void DisplayAllStudents () {
            if (students.isEmpty()) {  // checking if system is empty
                System.out.println("No students records available.");
                return;
            }
            for (Map.Entry<String, ArrayList<Double>> entry : students.entrySet()) { // A loop on every element in the HashMap


                System.out.print("Name: " + entry.getKey() + ", Grades: "); // Printing every Name
                for (int i = 0; i < entry.getValue().size(); i++) {// another loop for the arraylist to print every grade
                    System.out.print(entry.getValue().get(i) + " ");
                    if (i < entry.getValue().size() - 1) {// for every two elements we put "," between them except the last one
                        System.out.print(", ");// using a print instead of println so the grades on the same line
                    }
                }
                System.out.println(); // getting to the next line for future prints
            }
        }

        //-------------------------------------------------------------------------
        private static void CalculateStudentAverage () {
            System.out.println("Enter student name: ");
            String studentName = scanner.nextLine();
            if (!students.containsKey(studentName)) { // checking if the student is in the system
                System.out.println("No student found with name " + studentName);
                return;
            }
            ArrayList<Double> averageGrades = students.get(studentName); // A new ArrayList so we can put in it all the grades
            if (averageGrades.isEmpty()) {
                System.out.printf("Average grade for %s: 0.00%n.", studentName);//if there is no grades the default average is 0.00
            } else {
                double sum = 0.0; // Declaring a sum variable
                for (Double grade : averageGrades) {// Loop on every grade
                    sum += grade; // add the grade to the sum
                }
                double average = sum / averageGrades.size(); // calculating average
                System.out.printf("Average grade for %s:%.2f%n", studentName, average);

            }
        }

        //-------------------------------------------------------------------------
        // Building a Help func for yhe next func just like the calculateStudentAverage just making it return the value
        private static double StudentAverage (String Name){
            List<Double> grades = students.get(Name);
            double sum = 0.0;
            for (Double grade : grades) {
                sum += grade;
            }
            return sum / grades.size();
        }

        private static void FindTopStudent () {
            if (students.isEmpty()) { // checking ife there is students in the system
                System.out.println("No students records available");
                return;
            }
            String TopStudent = null; // defining a top student
            double highestAverage = 0.0; // declaring his average
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
