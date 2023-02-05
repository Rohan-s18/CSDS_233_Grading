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
    public void sizeTest(){
        LibraryDatabase temp = new LibraryDatabase(10);
        assertEquals(0, temp.size());
        temp.add(new Book("Tintin in the Land of the Soviets","9782203011014","Hergé"));
        temp.add(new Book("Tintin in America","9782203011013","Hergé"));
        temp.add(new Book("Tintin in the Congo","9782203011012","Hergé"));
    }

    @Test
    public void findTest(){

    }
    
}
