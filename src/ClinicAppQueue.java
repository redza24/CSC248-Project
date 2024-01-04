import java.util.*;
import java.io.*;

public class ClinicAppQueue {
    static Scanner in = new Scanner(System.in);
    static Scanner in1 = new Scanner(System.in);

    public static void main(String[] args) {
        Queue queue = new Queue();

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

        int choice = 0;
        do {
            System.out.println("\n===== CLINIC QUEUE =====");
            System.out.println("1. Add to queue\n2. Remove from queue\n3. Display size of queue\n4. Check if queue is empty\n5. Exit");
            System.out.print("Enter Option: ");
            choice = in1.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter patient's name: ");
                    String name = in.nextLine();
                    System.out.print("Enter patient's IC number: ");
                    String ic = in.nextLine();
                    System.out.println("Enter patient's date of birth: ");
                    String dob = in.nextLine();
                    System.out.print("Enter patient's phone number: ");
                    String phone = in.nextLine();
                    System.out.print("Enter patient's email: ");
                    String email = in.nextLine();
                    System.out.print("Enter patient's address: ");
                    String address = in.nextLine();
                    System.out.println("Enter patient's diagnosis: ");
                    String diagnosis = in.nextLine();
                    Patient patient = new Patient(name, ic, dob, phone, email, address, diagnosis);
                    queue.enqueue(patient);
                    break;

                case 2:
                    Patient removedPatient = (Patient) queue.dequeue();
                    if (removedPatient != null) {
                        System.out.println("Removed patient: " + removedPatient.getName());
                    } else {
                        System.out.println("Queue is empty. No patient removed.");
                    }
                    break;

                case 3:
                    System.out.println("Size of queue: " + queue.size());
                    break;

                case 4:
                    if (queue.isEmpty())
                        System.out.println("Queue is empty.");
                    else
                        System.out.println("Queue is not empty.");
                    break;

                case 5:
                    System.out.println("\u001B[32m" + "Exit the system..." + "\u001B[0m");
                    System.out.println();
                    return;

                default:
                    System.out.println("\u001B[31m" + "Wrong choice!" + "\u001B[0m");
                    break;
            }
        } while (choice != 5);
    }
}
