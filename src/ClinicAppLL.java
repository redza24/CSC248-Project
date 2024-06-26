import java.util.*;
import java.io.*;

public class ClinicAppLL {
    static Scanner in = new Scanner(System.in);
    static Scanner in1 = new Scanner(System.in);
    static LinkedList patient = new LinkedList();

    public static void main(String[] args) {
        ClinicAppQueue.clearScreen();
        System.out.println(
                "\n\u001B[33mSystem automatically retrieves and saves data on changes to patient_data.txt.\u001B[0m");
        System.out.println();
        retrieveRecord(patient);

        int choice = 0;
        do {
            try {
                displayMenu();
                choice = in1.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        ClinicAppQueue.clearScreen();
                        insertion(patient);
                        break;

                    case 2:
                        ClinicAppQueue.clearScreen();
                        deletion(patient);
                        break;

                    case 3:
                        ClinicAppQueue.clearScreen();
                        update(patient);
                        break;

                    case 4:
                        ClinicAppQueue.clearScreen();
                        search(patient);
                        break;

                    case 5:
                        ClinicAppQueue.clearScreen();
                        display(patient);
                        break;

                    case 6:
                        ClinicAppQueue.clearScreen();
                        System.out.println("\t\u001B[32m" + "Exit the system..." + "\u001B[0m");
                        System.out.println();
                        return;

                    default:
                        ClinicAppQueue.clearScreen();
                        System.out.println("\t\u001B[31m" + "Wrong choice!" + "\u001B[0m");
                        break;
                }
            } catch (InputMismatchException e) {
                ClinicAppQueue.clearScreen();
                System.out.println("\t\u001B[31m" + "Invalid input. Please enter an integer." + "\u001B[0m");
                in1.nextLine(); // Clear the buffer
            }
        } while (choice != 6);
    } // end main

    public static void displayMenu() {
        System.out.println("\n===== CLINIC SYSTEM MANAGEMENT =====");
        System.out.println("1. Insertion");
        System.out.println("2. Removal");
        System.out.println("3. Update");
        System.out.println("4. Search");
        System.out.println("5. Display");
        System.out.println("6. Exit");
        System.out.print("Enter Option: ");
    }

    public static void insertion(LinkedList list) {
        System.out.print("Enter size of list: ");
        int size = in1.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.println("\n===== INSERTION =====\n");

            System.out.println("----- PATIENT DETAILS -----");
            System.out.print("Name: ");
            String name = in.nextLine().toUpperCase();
            System.out.print("IC Number [XXXXXX-XX-XXXX]: ");
            String ICnum = in.nextLine();
            System.out.print("Date of Birth [dd/mm/yyyy]: ");
            String dob = in.nextLine();
            System.out.print("Phone Number [XXX-XXXXXXX]: ");
            String contact = in.nextLine();
            System.out.print("Email [x@mail.com]: ");
            String email = in.nextLine();
            System.out.print("Address: ");
            String address = in.nextLine().toUpperCase();
            System.out.print("Diagnosis: ");
            String diagnosis = in.nextLine().toUpperCase();

            System.out.println("\n===================\n1. Insert At Front\n2. Insert At Back");
            System.out.print("Enter choice: ");
            int choice = in1.nextInt();
            switch (choice) {
                case 1:
                    list.insertAtFront(new Patient(name, ICnum, dob, contact, email, address, diagnosis));
                    System.out.println("\u001B[32m" + "Data inserted successfully!" + "\u001B[0m");
                    saveRecord(list);
                    break;
                case 2:
                    list.insertAtBack(new Patient(name, ICnum, dob, contact, email, address, diagnosis));
                    System.out.println("\u001B[32m" + "Data inserted successfully!" + "\u001B[0m");
                    saveRecord(list);
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
        System.out.print("Enter choice: ");
        int choice = in1.nextInt();
        switch (choice) {
            case 1:
                patient.removeFront();
                System.out.println("\u001B[32m" + "Data removed successfully!" + "\u001B[0m");
                saveRecord(list);
                break;
            case 2:
                patient.removeBack();
                System.out.println("\u001B[32m" + "Data removed successfully!" + "\u001B[0m");
                saveRecord(list);
                break;
            case 3:
                System.out.print("Enter IC Number to be deleted: ");
                String ICnum = in.nextLine();
                list.removedPatient(ICnum);
                saveRecord(list);
                break;
            default:
                System.out.println("\u001B[31m" + "Wrong choice, please try again!" + "\u001B[0m");
                break;
        }
    }

    public static void update(LinkedList list) {
        System.out.println("===== UPDATE =====\n");
        System.out.println("\u001B[33m" + "This method is used for updating phone number, email, address, and diagnosis"
                + "\u001B[0m");
        System.out.print("\nEnter IC Number to be updated: ");
        String ICnum = in.nextLine();

        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            Patient patient = (Patient) list.get(i);
            if (ICnum.equals(patient.getICnum())) {
                found = true;
                // ask user new data
                System.out.print("Set the new phone number: ");
                String contact = in.nextLine();
                System.out.print("Set the new email: ");
                String email = in.nextLine();
                System.out.print("Set the new address: ");
                String address = in.nextLine().toUpperCase();
                System.out.print("Set the new diagnosis: ");
                String diagnosis = in.nextLine().toUpperCase();

                patient.setContactDetails(contact, email, address, diagnosis);
                System.out.println("\u001B[32m" + "Data updated successfully!" + "\u001B[0m");
                saveRecord(list);
                break; // Exit the loop after updating the data
            }
        }
        if (!found) {
            System.out.println("\u001B[31m" + "Sorry, Data Not Found!" + "\u001B[0m");
        }
    }

    public static void search(LinkedList list) {
        System.out.println("===== SEARCH =====\n");
        System.out.print("Enter IC Number to be searched: ");
        String ICnum = in.nextLine();
        // since while obj != null is quite hard, change to fori instead
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            Patient patient = (Patient) list.get(i);
            if (ICnum.equals(patient.getICnum())) {
                found = true;
                System.out.println("\u001B[32m" + "Data found!" + "\u001B[0m");
                System.out.println(patient.toString());
                break; // Exit the loop after finding the data
            }
        }
        if (!found) {
            System.out.println("\u001B[31m" + "Sorry, Data Not Found!" + "\u001B[0m");
        }
    }

    public static void display(LinkedList list) {
        System.out.println("===== DISPLAY =====");
        if (list.isEmpty())
            System.out.println("\n\u001B[31mList is empty\u001B[0m");
        else {
            for (int i = 0; i < list.size(); i++) {
                Patient patient = (Patient) list.get(i);
                System.out.println("\n\u001B[32mPatient [" + (i + 1) + "]\u001B[0m");
                System.out.println("Name\t : " + patient.getName() + "\nIC No\t : " + patient.getICnum()
                        + "\nDoB\t : " + patient.getDob() + "\nPhone No : " + patient.getContact()
                        + "\nEmail\t : " + patient.getEmail() + "\nAddress\t : " + patient.getAddress()
                        + "\nDiagnosis: "
                        + patient.getDiagnosis());
            }
        }

    }

    public static void saveRecord(LinkedList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patient_data.txt"))) {
            for (int i = 0; i < list.size(); i++) {
                Patient patient = (Patient) list.get(i);
                writer.write(patient.getName() + ";" + patient.getICnum() + ";" + patient.getDob() + ";"
                        + patient.getContact()
                        + ";" + patient.getEmail() + ";" + patient.getAddress() + ";" + patient.getDiagnosis() + "\n");
            }
            System.out.println("\u001B[32m" + "File Saved!" + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31mAn error occurred while saving the data to patient_data.txt.\u001B[0m");
            e.printStackTrace();
        }
    }

    public static void retrieveRecord(LinkedList list) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("patient_data.txt"));
            String inData = null;
            while ((inData = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inData, ";");
                String name = st.nextToken();
                String ICnum = st.nextToken();
                String dob = st.nextToken();
                String contact = st.nextToken();
                String email = st.nextToken();
                String address = st.nextToken();
                String diagnosis = st.nextToken();

                list.insertAtBack(new Patient(name, ICnum, dob, contact, email, address, diagnosis));
            }
            in.close();
            System.out.println("\u001B[32m" + "Patient records retrieved successfully!" + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31mAn error occurred while retrieving patient records.\u001B[0m");
            e.printStackTrace();
        }
    }
} // end class ClinicAppLL
