import static org.junit.Assert.*;
import org.junit.Test;

public class Tester {

    @Test
    public void BookTester(){
        Book temp = new Book("Tintin in the Land of the Soviets","9782203011014","Hergé");
        assertEquals("Tintin in the Land of the Soviets", temp.getTitle());
        assertEquals("9782203011014", temp.getISBN());
        assertEquals("Hergé", temp.getAuthor());
    }

    @Test
    public void dbConstructorTester(){
        try{
            LibraryDatabase temp = new LibraryDatabase(-10); 
            fail();
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test 
    public void addTest(){
        LibraryDatabase temp = new LibraryDatabase(2); 
        temp.add(new Book("Tintin in the Land of the Soviets","9782203011014","Hergé"));
        temp.add(new Book("Tintin in America","9782203011013","Hergé"));
        temp.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
        assertEquals("9782203011014", temp.findByISBN("9782203011014").getISBN());
        assertEquals("9782203011013", temp.findByISBN("9782203011013").getISBN());
        assertEquals("9782203011012", temp.findByISBN("9782203011012").getISBN());
        assertEquals(3, temp.size());
    }


    @Test
    public void sizeTest(){
        LibraryDatabase temp = new LibraryDatabase(10);
        assertEquals(0, temp.size());
        temp.add(new Book("Tintin in the Land of the Soviets","9782203011014","Hergé"));
        temp.add(new Book("Tintin in America","9782203011013","Hergé"));
        temp.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
        assertEquals(3, temp.size());
        temp.remove("9782203011013");
        assertEquals(2, temp.size());
    }

    @Test
    public void findByTitleTest(){
        LibraryDatabase temp = new LibraryDatabase(10);
        temp.add(new Book("Tintin in the Land of the Soviets","9782203011014","Hergé"));
        temp.add(new Book("Tintin in America","9782203011013","Hergé"));
        temp.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
        Book book = temp.findByTitle("Tintin in America");
        assertEquals("9782203011013", book.getISBN());
        assertEquals("Hergé", book.getAuthor());
        assertEquals("Tintin in America",book.getTitle());
        assertNotNull(temp.findByTitle("Tintin in America"));
        Book book2 = temp.findByTitle("Goo Goo Gaa Gaa");
        assertEquals(null, book2);

    }

    @Test 
    public void findByISBNTest(){
        LibraryDatabase temp = new LibraryDatabase(10);
        temp.add(new Book("Tintin in the Land of the Soviets","9782203011014","Hergé"));
        temp.add(new Book("Tintin in America","9782203011013","Hergé"));
        temp.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
        Book book = temp.findByISBN("9782203011013");
        assertEquals("9782203011013", book.getISBN());
        assertEquals("Hergé", book.getAuthor());
        assertEquals("Tintin in America",book.getTitle());
        assertNotNull(temp.findByISBN("9782203011013"));
        Book book2 = temp.findByISBN("9782203011019");
        assertEquals(null, book2);
    }

    @Test 
    public void removeTest(){
        LibraryDatabase temp = new LibraryDatabase(10);
        temp.add(new Book("Tintin in the Land of the Soviets","9782203011014","Hergé"));
        temp.add(new Book("Tintin in America","9782203011013","Hergé"));
        temp.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
        Book removed = temp.remove("9782203011013");
        assertEquals("9782203011013", removed.getISBN());
        assertEquals("Hergé", removed.getAuthor());
        assertEquals("Tintin in America",removed.getTitle());
        assertEquals(2, temp.size());
        assertEquals(null, temp.findByISBN("9782203011013"));
    }

    @Test
    public void testRandomSelection(){
        LibraryDatabase db = new LibraryDatabase(10);
        assertEquals(null, db.randomSelection());
        Book b1 = new Book("Tintin in the Land of the Soviets","9782203011014","Hergé");
        db.add(b1);
        assertEquals(b1, db.randomSelection());
        if (db.size() == 0) db.add(b1);
        db.add(new Book("Tintin in America","9782203011013","Hergé"));
        db.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
        db.add(new Book("Of Mice and Men","0000000000000","Steinbeck"));
        db.add(new Book("Charlotte's Web","2389256621023","White"));
        db.add(new Book("Romeo and Juliet","0032852830020","Shakespeare"));
        Book selected = db.randomSelection();
        if (db.size() < 6){
            db.add(selected);
        }
        boolean different = false;
        for (int i = 0; i < 100; i++){
            Book temp = db.randomSelection();
            if (db.size() < 6){
                db.add(temp);
            }
            if (temp != selected) {
                different = true;
                break;
            }
        }
        // (1/6)^100 chance of failing this test if selection is truly random
        assertTrue(different);
    }

    @Test
    public void testGetAllByAuthor() {
        LibraryDatabase db = new LibraryDatabase(10);
        assertArrayEquals(new Book[]{}, db.getAllByAuthor("Kafka"));
        Book b1 = new Book("Of Mice and Men","0000000000000","Steinbeck");
        db.add(b1);
        assertArrayEquals(new Book[]{b1}, db.getAllByAuthor("Steinbeck"));
        Book b2 = new Book("Tintin in America","9782203011013","Hergé");
        db.add(b2);
        Book b3 = new Book("Tintin in the Congo","9782203011012","Hergé");
        db.add(b3);
        Book b4 = new Book("Tintin in the Land of the Soviets","9782203011014","Hergé");
        db.add(b4);
        db.add(new Book("Charlotte's Web","2389256621023","White"));
        Book[] bookArray = db.getAllByAuthor("Hergé");
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;
        for (Book b : bookArray) {
            if (b == b2) found2 = true;
            else if (b == b3) found3 = true;
            else if (b == b4) found4 = true;
        }
        if (!found2 || !found3 || !found4 || bookArray.length != 3) {
            fail("Fails at returning multiple books");
        }
        else assertTrue(true);
        assertArrayEquals(new Book[]{}, db.getAllByAuthor("Kafka"));
    }
    
    @Test
    public void testRemoveDuplicates() {
        LibraryDatabase db = new LibraryDatabase(5);
        db.removeDuplicates();
        boolean passed = true;
        boolean foundNull = false;
        Book[] arr = db.toArray();
        for (Book b : arr) {
            if (b != null) {
                passed = false;
                break;
            }
        }
        assertTrue(passed);
        Book b1 = new Book("Charlotte's Web","2389256621023","White");
        db.add(b1);
        db.removeDuplicates();
        passed = true;
        arr = db.toArray();
        for (Book b : arr) {
            if (foundNull) {
                if (b != null) {
                    passed = false;
                    break;
                }
            }
            else {
                if (b == null) foundNull = true;
            }
        }
        if (arr[0] != b1 || arr.length > 1 && arr[1] != null) passed = false;
        assertTrue(passed);
        Book b2 = new Book("Tintin in America","9782203011012","Hergé");
        db.add(b2);
        Book b3 = new Book("Tintin in the Congo","9782203011012","Hergé");
        db.add(b3);
        Book b4 = new Book("Tintin in the Land of the Soviets","9782203011012","Hergé");
        db.add(b4);
        db.removeDuplicates();
        passed = true;
        foundNull = false;
        arr = db.toArray();
        for (Book b : arr) {
            if (foundNull) {
                if (b != null) {
                    passed = false;
                    break;
                }
            }
            else {
                if (b == null) foundNull = true;
            }
        }
        if (arr[0] != b1 || arr[1] != b2 || arr.length > 2 && arr[2] != null) passed = false;
        assertTrue(passed);
    }

    @Test
    public void testToArray() {
        LibraryDatabase db = new LibraryDatabase(0);
        boolean passed = true;
        boolean foundNull = false;
        Book[] arr = db.toArray();
        for (Book b : arr) {
            if (b != null) {
                passed = false;
                break;
            }
        }
        assertTrue(passed);
        Book b1 = new Book("Charlotte's Web","2389256621023","White");
        db.add(b1);
        passed = true;
        arr = db.toArray();
        for (Book b : arr) {
            if (foundNull) {
                if (b != null) {
                    passed = false;
                    break;
                }
            }
            else {
                if (b == null) foundNull = true;
            }
        }
        if (arr[0] != b1 || arr.length > 1 && arr[1] != null) passed = false;
        assertTrue(passed);
        Book b2 = new Book("Tintin in America","9782203011013","Hergé");
        db.add(b2);
        Book b3 = new Book("Tintin in the Congo","9782203011012","Hergé");
        db.add(b3);
        Book b4 = new Book("Tintin in the Land of the Soviets","9782203011014","Hergé");
        db.add(b4);
        passed = true;
        arr = db.toArray();
        foundNull = false;
        for (Book b : arr) {
            if (foundNull) {
                if (b != null) {
                    passed = false;
                    break;
                }
            }
            else {
                if (b == null) foundNull = true;
            }
        }
        if (arr[0] != b1 || arr[1] != b3 || arr[2] != b2 || arr[3] != b4 || arr.length > 4 && arr[4] != null) passed = false;
        assertTrue(passed);
    }
    
}
