public class Patient {
    private String name;
    private String ICnum;
    private String dateOfBirth;
    private String phoneNum;
    private String email;
    private String address;
    private String diagnosis;

    public Patient(String name, String ICnum, String dateOfBirth, String phoneNum, String email, String address,
            String diagnosis) {
        this.name = name;
        this.ICnum = ICnum;
        this.dateOfBirth = dateOfBirth;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.diagnosis = diagnosis;
    }

    public void setPatient(String name, String ICnum, String dateOfBirth, String phoneNum, String email, String address,
            String diagnosis) {
        this.name = name;
        this.ICnum = ICnum;
        this.dateOfBirth = dateOfBirth;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.diagnosis = diagnosis;
    }

    public void setContactDetails(String phoneNum, String email, String address, String diagnosis) {
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.diagnosis = diagnosis;
    }

    public String getName() {
        return name;
    }

    public String getICnum() {
        return ICnum;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String toString() {
        return "Name: " + name + "\nIC Number: " + ICnum + "\nDate of Birth: " + dateOfBirth + "\nPhone Number: "
                + phoneNum + "\nEmail: " + email + "\nAddress: " + address + "\nDiagnosis: " + diagnosis;
    }
}

class ListNode {
    private Object obj;
    private ListNode next;

    // constructor
    public ListNode(Object obj) {
        this(obj, null);
    }

    public ListNode(Object obj, ListNode next) {
        this.obj = obj;
        this.next = next;
    }

    // getter
    public Object getObject() {
        return obj;
    }

    public ListNode getNext() {
        return next;
    }

    // setter (just in case)
    public void setObject(Object obj) {
        this.obj = obj;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

}

class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super();
    }
}

class List {
    private ListNode firstNode;
    private ListNode lastNode;
    private ListNode currNode;

    public boolean isEmpty() {
        return firstNode == null;
    }

    public int size() {
        int size = 0;
        currNode = firstNode;
        while (currNode != null) {
            size++;
            currNode = currNode.getNext();
        }
        return size;
    }

    public void insertAtFront(Object obj) {
        if (isEmpty())
            firstNode = lastNode = new ListNode(obj);
        else
            firstNode = new ListNode(obj, firstNode);
    }

    public void insertAtBack(Object obj) {
        if (isEmpty()) {
            firstNode = lastNode = new ListNode(obj);
        } else {
            lastNode.setNext(new ListNode(obj));
            lastNode = lastNode.getNext();
        }
    }

    public Object removeFront() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        Object removedObject = firstNode.getObject();
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
            firstNode = firstNode.getNext();
        return removedObject;
    }

    public Object removeBack() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();

        Object removedObject = lastNode.getObject();
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else {
            currNode = firstNode;
            while (currNode.getNext() != lastNode) {
                currNode = currNode.getNext();
            }
            lastNode = currNode;
            currNode.setNext(null);
        }
        return removedObject;
    }

    public Object getFirst() {
        if (isEmpty())
            return null;
        else {
            currNode = firstNode;
            return currNode.getObject();
        }
    }

    public Object getLast() {
        if (isEmpty())
            return null;
        else {
            currNode = firstNode;
            while (currNode.getNext() != lastNode) {
                currNode = currNode.getNext();
            }
            lastNode = currNode;
            return currNode.getObject();
        }
    }

    public Object getNext() {
        currNode = firstNode;
        return currNode.getNext();
    }

    public String toString(Object obj) {
        currNode = firstNode;
        while (currNode != null) {
            obj.toString();
            currNode = currNode.getNext();
        }
        return obj.toString();
    }

    // exclusive for Queue

    public Patient removedPatient(String ICnum) throws EmptyListException {
        Patient removedPatient = null;

        if (isEmpty()) {
            throw new EmptyListException();
        } else {
            if (firstNode == lastNode && ((Patient) firstNode.getObject()).getICnum().equals(ICnum)) {
                removedPatient = (Patient) firstNode.getObject();
                firstNode = lastNode = null;
                System.out.println("\u001B[42m" + "Data removed successfully!" + "\u001B[0m");
                return removedPatient;
            } else {
                currNode = firstNode;
                ListNode prevNode = null;

                while (currNode != null) {
                    Patient patient = (Patient) currNode.getObject();

                    if (patient.getICnum().equals(ICnum)) {
                        removedPatient = (Patient) currNode.getObject();

                        if (currNode == firstNode) {
                            firstNode = currNode.getNext();
                        } else if (currNode == lastNode) {
                            lastNode = prevNode;
                            lastNode.setNext(null);
                        } else {
                            prevNode.setNext(currNode.getNext());
                        }

                        System.out.println("\u001B[42m" + "Data removed successfully!" + "\u001B[0m");
                        break; // Data found and removed, exit the loop
                    }

                    prevNode = currNode;
                    currNode = currNode.getNext();
                }

                if (removedPatient == null) {
                    System.out.println("Data not found!");
                }

                return removedPatient;
            }
        }
    }

    public Object searchPatient(String icNum) throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();
        else {
            currNode = firstNode;
            Patient patient = (Patient) currNode.getObject();
            while (!icNum.equals(patient.getICnum())) {
                currNode = currNode.getNext();
            }
            // return "Name: " + patient.getName() +
            // "\nIC Number: " + patient.getICnum() +
            // "\nDate of Birth: " + patient.getDateOfBirth() +
            // "\nPhone Number: " + patient.getPhoneNum() +
            // "\nEmail: " + patient.getEmail() + "\nAddress: " + patient.getAddress() +
            // "\nDiagnosis: " + patient.getDiagnosis();
            return patient.toString();
        }
    }
}