import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class ClinicAppLL {
    static Scanner scan = new Scanner(System.in);
    static Scanner scan1 = new Scanner(System.in);
    static LinkedList patient = new LinkedList();

    public static void main(String[] args) {
        System.out.println();
        retrieveRecord(patient);
        System.out.println("\n\u001B[33mThis system automatically retrieve data from txt file (if any) and automatically save when changes happen such as insertion, removal and update\u001B[0m");
        int choice = 0;
        do {
            System.out.println("\n===== CLINIC SYSTEM MANAGEMENT =====");
            System.out.println("1. Insertion\n2. Removal\n3. Update\n4. Search\n5. Display\n6. Exit");
            System.out.print("Enter Option: ");
            choice = scan1.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    insertion(patient);
                    break;

                case 2:
                    deletion(patient);
                    break;

                case 3:
                    update(patient);
                    break;

                case 4:
                    search(patient);
                    break;

                case 5:
                    display(patient);
                    break;

                case 6:
                    System.out.println("\u001B[32m" + "Exit the system..." + "\u001B[0m");
                    System.out.println();
                    break;

                default:
                    System.out.println("\u001B[31m" + "Wrong choice!" + "\u001B[0m");
                    break;
            }
        } while (choice != 6);
    }

    public static void insertion(LinkedList list) {
        System.out.print("Enter size of list: ");
        int size = scan1.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.println("\n===== INSERTION =====\n");

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
            System.out.print("Enter choice: ");
            int choice = scan1.nextInt();
            switch (choice) {
                case 1:
                    list.insertAtFront(new Patient(name, ICnum, dateOfBirth, phoneNum, email, address, diagnosis));
                    System.out.println("\u001B[32m" + "Data inserted successfully!" + "\u001B[0m");
                    saveRecord(list);
                    break;
                case 2:
                    list.insertAtBack(new Patient(name, ICnum, dateOfBirth, phoneNum, email, address, diagnosis));
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
        int choice = scan1.nextInt();
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
                String icNum = scan.nextLine();
                list.removedPatient(icNum);
                saveRecord(list);
                break;
            default:
                System.out.println("\u001B[31m" + "Wrong choice, please try again!" + "\u001B[0m");
                break;
        }
    }

    public static void update(LinkedList list) {
        System.out.println("===== UPDATE =====\n");
        System.out.println("\u001B[33m" + "This method is used for updating phone number, email, address, and diagnosis" + "\u001B[0m");
        System.out.print("\nEnter IC Number to be updated: ");
        String ICnum = scan.nextLine();

        for (int i = 0; i < list.size(); i++) {
            Patient patient = (Patient) list.get(i);
            if (ICnum.equals(patient.getICnum())) {
                // ask user new data
                System.out.print("Set the new phone number: ");
                String phoneNum = scan.nextLine();
                System.out.print("Set the new email: ");
                String email = scan.nextLine();
                System.out.print("Set the new address: ");
                String address = scan.nextLine();
                System.out.print("Set the new diagnosis: ");
                String diagnosis = scan.nextLine();
    
                patient.setPatient(phoneNum, email, address, diagnosis);
                System.out.println("\u001B[32m" + "Data updated successfully!" + "\u001B[0m");
                saveRecord(list);
                break; // Exit the loop after updating the data
            } else {
                System.out.println("\u001B[31m" + "Sorry, Data Not Found!" + "\u001B[0m");
                break; //so it will not repeat upon testing
            }
        }
    }

    public static void search(LinkedList list) {
        System.out.println("===== SEARCH =====\n");
        System.out.print("Enter IC Number to be searched: ");
        String ICnum = scan.nextLine();
        //since while obj != null is quite hard, change to fori instead
        for (int i = 0; i < list.size(); i++) {
            Patient patient = (Patient) list.get(i);
            if(ICnum.equals(patient.getICnum())) {
                System.out.println("\u001B[32m" + "Data found!" + "\u001B[0m");
                System.out.println(patient.toString());
                break;
            }
            else {
                System.out.println("\u001B[31m" + "Sorry, Data Not Found!" + "\u001B[0m");
                break;
            }
        }
    }

    public static void display(LinkedList list) {
        System.out.println("===== DISPLAY =====");
        if(list.isEmpty())
            System.out.println("\n\u001B[31mList is empty\u001B[0m");
        else {
            for (int i = 0; i < list.size(); i++) {
                Patient patient = (Patient) list.get(i);
                System.out.println("\n\u001B[32mPatient [" + (i + 1) +"]\u001B[0m");
                System.out.println("Name: " + patient.getName() + "\nIC Number: " + patient.getICnum() + "\nDate of Birth: " + patient.getDateOfBirth() + "\nPhone Number: " + patient.getPhoneNum() + "\nEmail: " + patient.getEmail() + "\nAddress: " + patient.getAddress() + "\nDiagnosis: " + patient.getDiagnosis());
            }
        }
        
    }

    public static void saveRecord(LinkedList list) {
        try (BufferedWriter writer= new BufferedWriter(new FileWriter("patient_data.txt"))) {
            for (int i = 0; i < list.size(); i++) {
                Patient patient = (Patient) list.get(i);
                writer.write(patient.getName() + ";" + patient.getICnum() + ";" + patient.getDateOfBirth() + ";" + patient.getPhoneNum()
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
            while((inData = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inData, ";");
                String name = st.nextToken();
                String ICnum = st.nextToken();
                String dateOfBirth = st.nextToken();
                String phoneNum = st.nextToken();
                String email = st.nextToken();
                String address  = st.nextToken();
                String diagnosis = st.nextToken();

                list.insertAtFront(new Patient(name, ICnum, dateOfBirth, phoneNum, email, address, diagnosis));
            }
            in.close();
            System.out.println("\u001B[32m" + "Patient records retrieved successfully!" + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31mAn error occurred while retrieving patient records.\u001B[0m");
            e.printStackTrace();
        }
    }
}
