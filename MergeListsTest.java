import org.junit.Test;
import static org.junit.Assert.*;

public class MergeListsTest {

    @Test
    public void testBothListsEmpty() {
        MergeLists merger = new MergeLists();
        PatientRecord result = merger.merge(null, null);
        assertNull("Merging two empty lists should result in null", result);
    }

    @Test
    public void testFirstListEmpty() {
        MergeLists merger = new MergeLists();
        PatientRecord list2 = new PatientRecord(123456789, 30, "Charlie Brown");
        PatientRecord result = merger.merge(null, list2);
        assertNotNull("Result should not be null when second list is not empty", result);
        assertEquals("Head should be the first element of the non-empty list", 123456789, result.ssn);
    }

    @Test
    public void testSecondListEmpty() {
        MergeLists merger = new MergeLists();
        PatientRecord list1 = new PatientRecord(223456789, 40, "Alice Johnson");
        PatientRecord result = merger.merge(list1, null);
        assertNotNull("Result should not be null when first list is not empty", result);
        assertEquals("Head should be the first element of the non-empty list", 223456789, result.ssn);
    }

    @Test
    public void testNonOverlappingLists() {
        MergeLists merger = new MergeLists();
        PatientRecord list1 = new PatientRecord(123456789, 30, "Alice Johnson");
        PatientRecord list2 = new PatientRecord(223456789, 40, "Bob Smith");
        PatientRecord result = merger.merge(list1, list2);
        assertEquals("First element should be from the first list", 123456789, result.ssn);
        assertEquals("Second element should be from the second list", 223456789, result.next.ssn);
    }

    @Test
    public void testCompletelyOverlappingLists() {
        MergeLists merger = new MergeLists();
        PatientRecord list1 = new PatientRecord(123456789, 30, "Alice Johnson");
        PatientRecord list2 = new PatientRecord(123456789, 30, "Alice Johnson");
        PatientRecord result = merger.merge(list1, list2);
        assertEquals("Both elements should have the same SSN", list1.ssn, result.ssn);
        assertEquals("Both elements should be identical and appear in the list", list1.ssn, result.next.ssn);
    }

    @Test
    public void testListsMultipleEntriesAndOverlap() {
        MergeLists merger = new MergeLists();
        PatientRecord list1 = new PatientRecord(123456789, 30, "Alice Johnson");
        list1.next = new PatientRecord(323456789, 35, "Charlie Brown");
        PatientRecord list2 = new PatientRecord(223456789, 40, "Bob Smith");
        list2.next = new PatientRecord(323456789, 35, "Charlie Brown");
        PatientRecord result = merger.merge(list1, list2);
        assertEquals("First SSN should be 123456789", 123456789, result.ssn);
        assertEquals("Second SSN should be 223456789", 223456789, result.next.ssn);
        assertEquals("Third and fourth SSN should be 323456789, indicating overlap", 323456789, result.next.next.ssn);
        assertEquals("Duplicate entry for Charlie Brown should also appear", 323456789, result.next.next.next.ssn);
    }
}
