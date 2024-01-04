public class Queue extends List{
    public Queue() {
        super();
    }

    public int size() {
        return super.size();
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public void enqueue(Object obj) {
        super.insertAtBack(obj);
    }

    public Object dequeue() {
        return removeFront();
    }

    public Object front() {
        return getFirst();
    }

    public Object back() {
        return getLast();
    }

    public Patient removedPatient(String icNum) {
        return super.removedPatient(icNum);
    }

    public Object searchPatient(String icNum) {
        return super.searchPatient(icNum);
    }
}