import java.util.*;
import java.io.*;

public class ClinicAppQueue {
    static Scanner in = new Scanner(System.in);
    static Scanner in1 = new Scanner(System.in);

    public static void main(String[] args) {
        Queue queue = new Queue();

        // Read data from file and add to queue
        try (BufferedReader br = new BufferedReader(new FileReader("inputFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split("; ");
                Patient patient = new Patient(details[0], details[1], details[2], details[3],
                        details[4], details[5], details[6]);
                queue.enqueue(patient);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Display menu
        clearScreen();
        int choice = 0;
        do {
            displayMenu();
            choice = in1.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    clearScreen();

                    Patient patient = addPatient();
                    queue.enqueue(patient);
                    break;

                case 2:
                    clearScreen();

                    try {
                    Patient removedPatient = (Patient) queue.dequeue();
                    if (removedPatient != null) {
                        System.out.println(removedPatient);
                    } 
                    } catch (EmptyListException e) {
                        System.out.println("\t\u001B[31m" + "Queue is empty. No patient removed." + "\u001B[0m");
                    }

                    break;

                case 3:
                    clearScreen();
                    System.out.println("Size of queue: " + queue.size());
                    break;

                case 4:
                    clearScreen();

                    if (queue.isEmpty())
                        System.out.println("Queue is empty.");
                    else
                        System.out.println("Queue is not empty.");

                    break;

                case 5:
                    clearScreen();
                    System.out.println("\t\u001B[32m" + "Exit the system..." + "\u001B[0m");
                    System.out.println();
                    return;

                default:
                    clearScreen();
                    System.out.println("\t\u001B[31m" + "Wrong choice!" + "\u001B[0m");
                    break;
            }
        } while (choice != 5);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the screen
        System.out.flush();
    }

    public static void displayMenu() {
        System.out.println("\n===== CLINIC SYSTEM MANAGEMENT =====");
        System.out.println("1. Add patient to queue");
        System.out.println("2. Remove patient from queue");
        System.out.println("3. Get size of queue");
        System.out.println("4. Check if queue is empty");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    static public Patient addPatient() {
        System.out.print("Enter patient's name: ");
        String name = in.nextLine();
        System.out.print("Enter patient's IC number: ");
        String ic = in.nextLine();
        System.out.print("Enter patient's date of birth: ");
        String dob = in.nextLine();
        System.out.print("Enter patient's phone number: ");
        String phone = in.nextLine();
        System.out.print("Enter patient's email: ");
        String email = in.nextLine();
        System.out.print("Enter patient's address: ");
        String address = in.nextLine();
        System.out.print("Enter patient's diagnosis: ");
        String diagnosis = in.nextLine();

        Patient patient = new Patient(name, ic, dob, phone, email, address, diagnosis);
        return patient;
    }
}
