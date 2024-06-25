import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.
    static final int MAX_STUDENTS=100;
    static Map<String, List<Double>> students=new HashMap<>();
    public static void manageGrades() {
        System.out.println("Welcome to the Students Grade Management System!");
        while (true){
            PrintMenu();
            System.out.println("Please enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    AddStudent();
                 break;
                case 2:
                    DisplayAllStudents();
                    break;
                case 3 :
                    CalculateStudentAverage();
                    break;
                case 4 :
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
    //menu func
    private static void PrintMenu(){
        System.out.println("1. Add a new student");
        System.out.println("2. Display all students");
        System.out.println("3. Calculate a student's average grade");
        System.out.println("4. Find top performing student");
        System.out.println("5. Exit");
    }
    private static void AddStudent() {
        if(students.size()>=MAX_STUDENTS){
            System.out.println("Student limit reached");
            return;
        }
        System.out.println("Enter student name: ");
        String studentName=scanner.nextLine();
        System.out.println("Enter student grades: ");
        String[] studentGrades=scanner.nextLine().split(" ");
        List<Double> grades=new ArrayList<>();





    }
    //-------------------------------------------------------------------------
    private static void DisplayAllStudents(){}
    //-------------------------------------------------------------------------
    private static void CalculateStudentAverage(){}
    //-------------------------------------------------------------------------
    private static void FindTopStudent(){}
    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfTests = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfTests; i++) {
            System.out.println("Test number " + i + " starts.");
            try {
                manageGrades();
            } catch(Exception e){
                System.out.println("Exception " + e);
            }
            System.out.println("Test number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All tests have ended.");
    }
}