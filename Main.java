class PatientRecord {
    int ssn;
    int age;
    String fullName;
    PatientRecord next;

    public PatientRecord(int ssn, int age, String fullName) {
        this.ssn = ssn;
        this.age = age;
        this.fullName = fullName;
        this.next = null;
    }
}

class MergeLists {
    // Method to merge two sorted linked lists into one sorted list
    public PatientRecord merge(PatientRecord list1, PatientRecord list2) {
        PatientRecord dummyHead = new PatientRecord(0, 0, "");
        PatientRecord current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.ssn <= list2.ssn) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach the remaining elements of whichever list is not exhausted
        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        return dummyHead.next;
    }

    public void printList(PatientRecord head) {
        while (head != null) {
            System.out.println("SSN: " + head.ssn + ", Age: " + head.age + ", Full Name: " + head.fullName);
            head = head.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // example lists
        PatientRecord list1 = new PatientRecord(123456789, 30, "Alice Johnson");
        list1.next = new PatientRecord(223456789, 40, "Bob Smith");

        PatientRecord list2 = new PatientRecord(123456789, 30, "Alice Johnson"); // Duplicate record
        list2.next = new PatientRecord(323456789, 35, "Charlie Brown");

        MergeLists merger = new MergeLists();
        PatientRecord mergedList = merger.merge(list1, list2);

        merger.printList(mergedList);
    }
}
// Time Complexity:
//O(m+n)
//Space Complexity:
//O(1) (auxiliary space)
//This efficiency makes the merge operation highly suitable for scenarios where large
// datasets are involved, and space optimization is crucial, such as in handling large
// databases or streams of data in a resource-constrained environment like embedded systems
// or real-time processing applications.
