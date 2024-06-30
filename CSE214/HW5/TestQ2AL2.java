public class TestQ2AL2{
    public static void main(String[] args) {
        Q2AL<Integer> list = new Q2AL<>();
        // Filling the list for testing
        for (int i = 0; i < 10; i++) {
           list.add(i); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        }
  
        testGetWithInvalidIndex(list);
        testSetWithInvalidIndex(list);
        testAddAtInvalidIndex(list);
        testRemoveOnEmptyList();
        testIndexOfWithNonexistentElement(list);
        testAddOnEmptyList();
     }
  
     private static void testGetWithInvalidIndex(Q2AL<Integer> list) {
        try {
           list.get(-1);
           System.out.println("Test failed: Expected IndexOutOfBoundsException for get(-1)");
        } catch (IndexOutOfBoundsException e) {
           System.out.println("Passed testGetWithInvalidIndex: " + e.getMessage());
        }
  
        try {
           list.get(10);
           System.out.println("Test failed: Expected IndexOutOfBoundsException for get(10)");
        } catch (IndexOutOfBoundsException e) {
           System.out.println("Passed testGetWithInvalidIndex: " + e.getMessage());
        }
     }
  
     private static void testSetWithInvalidIndex(Q2AL<Integer> list) {
        try {
           list.set(-1, 100);
           System.out.println("Test failed: Expected IndexOutOfBoundsException for set(-1, 100)");
        } catch (IndexOutOfBoundsException e) {
           System.out.println("Passed testSetWithInvalidIndex: " + e.getMessage());
        }
  
        try {
           list.set(10, 100);
           System.out.println("Test failed: Expected IndexOutOfBoundsException for set(10, 100)");
        } catch (IndexOutOfBoundsException e) {
           System.out.println("Passed testSetWithInvalidIndex: " + e.getMessage());
        }
     }
  
     private static void testAddAtInvalidIndex(Q2AL<Integer> list) {
        if (list.add(11, 200)) {
           System.out.println("Test failed: Expected add(11, 200) to fail");
        } else {
           System.out.println("Passed testAddAtInvalidIndex: add(11, 200) failed as expected");
        }
     }
  
     private static void testRemoveOnEmptyList() {
        Q2AL<Integer> emptyList = new Q2AL<>();
        if (emptyList.remove(0)) {
           System.out.println("Test failed: Expected remove(0) to fail on an empty list");
        } else {
           System.out.println("Passed testRemoveOnEmptyList: remove(0) failed as expected on an empty list");
        }
     }
  
     private static void testIndexOfWithNonexistentElement(Q2AL<Integer> list) {
        int index = list.indexOf(100);
        if (index != -1) {
           System.out.println("Test failed: Expected indexOf(100) to return -1");
        } else {
           System.out.println("Passed testIndexOfWithNonexistentElement: indexOf(100) returned -1 as expected");
        }
     }
  
     private static void testAddOnEmptyList() {
        Q2AL<Integer> newList = new Q2AL<>();
        newList.add(100);
        if (newList.get(0) == 100) {
           System.out.println("Passed testAddOnEmptyList: add on empty list worked as expected");
        } else {
           System.out.println("Test failed: Expected the first element to be 100 after add on empty list");
        }
     }
}