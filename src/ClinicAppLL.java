import java.util.Scanner;

public class ClinicAppLL {
    static Scanner scan = new Scanner(System.in);
    static Scanner scan1 = new Scanner(System.in);
    static LinkedList patient = new LinkedList();

    public static void main(String[] args) {
        int choice = 0, size = 0;
        do {
            System.out.println("\n===== CLINIC SYSTEM MANAGEMENT =====");
            System.out.println("1. Insertion\n2. Removal\n3. Update\n4. Search\n5. Exit");
            System.out.print("Enter Option: ");
            choice = scan.nextInt();
            System.out.println("Enter size of list: ");
            size = scan.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    insertion(patient, size);
                    break;

                case 2:
                    deletion(patient);
                    break;

                case 3:
                    update(patient);
                    break;

                case 4:
                    ;
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

    public static void insertion(LinkedList list, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("===== INSERTION =====\n");

            System.out.println("----- PATIENT DETAILS -----");
            System.out.print("Name: ");
            String name = scan.nextLine().toUpperCase();
            System.out.print("IC Number [XXXXXX-XX-XXXX]: ");
            String ICnum = scan.nextLine();
            System.out.print("Date of Birth [dd/mm/yyyy]: ");
            String dateOfBirth = scan.nextLine();
            System.out.print("Phone Number [XXX-XXXXXXX]: ");
            String phoneNum = scan.nextLine();
            System.out.print("Email [x@mail.com]: ");
            String email = scan.nextLine();
            System.out.print("Address: ");
            String address = scan.nextLine().toUpperCase();
            System.out.print("Diagnosis: ");
            String diagnosis = scan.nextLine();

            System.out.println("\n===================\n1. Insert At Front\n2. Insert At Back");
            System.out.println("Enter choice: ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    patient.insertAtFront(new Patient(name, ICnum, dateOfBirth, phoneNum, email, address, diagnosis));
                    System.out.println("\u001B[42m" + "Data inserted successfully!" + "\u001B[0m");
                    break;
                case 2:
                    patient.insertAtBack(new Patient(name, ICnum, dateOfBirth, phoneNum, email, address, diagnosis));
                    System.out.println("\u001B[42m" + "Data inserted successfully!" + "\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31m" + "Wrong choice, please try again!" + "\u001B[0m");
                    break;
            }
        }
    }

    public static void deletion(LinkedList list) {
        System.out.println("===== DELETION =====\n");
        System.out.println("--------------------\n1. Remove From Front\n2. Remove From Back\n3. Delete by IC Number");
        System.out.println("Enter choice: ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                patient.removeFront();
                System.out.println("\u001B[42m" + "Data removed successfully!" + "\u001B[0m");
                break;
            case 2:
                patient.removeBack();
                System.out.println("\u001B[42m" + "Data removed successfully!" + "\u001B[0m");
                break;
            case 3:
                System.out.println("Enter IC Number to be deleted: ");
                String icNum = scan.nextLine();
                ((List) patient).removedPatient(icNum);
                break;
            default:
                System.out.println("\u001B[31m" + "Wrong choice, please try again!" + "\u001B[0m");
                break;
        }
    }

    public static void update(LinkedList list) {
        System.out.println("===== UPDATE =====\n");
        System.out.println("This method is used for updating phone number, email, address and diagnosis");
        System.out.println("Enter IC Number to be updated: ");
        String ICnum = scan.nextLine();

        Patient obj = (Patient) patient.getFirst();
        while (obj != null) {
            obj = (Patient) patient.getNext();
            if (ICnum.equals(obj.getICnum())) {
                // ask user new data
                System.out.println("Set the new phone number: ");
                String phoneNum = scan.nextLine();
                System.out.println("Set the new email: ");
                String email = scan.nextLine();
                System.out.println("Set the new address: ");
                String address = scan.nextLine();
                System.out.println("Set the new diagnosis: ");
                String diagnosis = scan.nextLine();
            }
        }
    }
}