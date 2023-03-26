import org.junit.*;
public class Tester {
    private String directory = "C:\\Users\\emile\\Desktop"; // Replace with path to folder on your PC where test files exist

    @Test
    public void tokenizerFileTester() {
        System.out.println(Character.isLetterOrDigit('Ł'));
        Tokenizer t1 = new Tokenizer(directory + "\\test1");
        Assert.assertEquals(t1.wordList().toString(), "[im, going, to, eat, twentyfive, pancakes]");
    }

    @Test
    public void tokenizerFileEdgeTester() {
        Tokenizer t2 = new Tokenizer(directory + "\\test2");
        try {
            Assert.assertEquals(t2.wordList().toString(), "[]");
        } catch (NullPointerException e) {} // Allow wordList to be null for files with no normalizable words
        Tokenizer t3 = new Tokenizer(directory + "\\test3");
        Assert.assertEquals(t3.wordList().toString(), "[supercalifragilisticexpialidocious]");
        Tokenizer t4 = new Tokenizer(directory + "\\test4");
        try {
            Assert.assertEquals(t4.wordList().toString(), "[]");
        } catch (NullPointerException e) {} // Allow wordList to be null for files with no normalizable words
        Tokenizer t5 = new Tokenizer(directory + "\\test5");
        Assert.assertEquals(t5.wordList().toString(), "[gdynia, 10, sopot, sopot, 13, gdańsk, gdańsk, 36, gdynia, 422, " +
        "białystok, białystok, 198, warsaw, lublin, 173, warsaw, warsaw, 340, gdańsk, 290, kraków, 136, łódź, łódź, 126, " +
        "częstochowa, 217, wrocław, częstochowa, 73, katowice, 258, wrocław, 126, łódź, wrocław, 182 poznań, poznań, 63, " +
        "gniezno, 311, warsaw, katowice]");
    }

    @Test
    public void tokenizerArrayTester() {
        Tokenizer t1 = new Tokenizer(new String[]{"I’m", "going", "to", "eat", "twenty-five", "pancakes."});
        Assert.assertEquals(t1.wordList().toString(), "[im, going, to, eat, twentyfive, pancakes]");
    }

    @Test
    public void tokenizerArrayEdgeTester() {}

    @Test
    public void hashTableEmptyTester() {}

    @Test
    public void hashTableCapacityTester() {}

    @Test
    public void getTester() {}

    @Test
    public void getEdgeTester() {}

    @Test
    public void putTester() {}

    @Test
    public void putEdgeTester() {}

    @Test
    public void removeTester() {}

    @Test
    public void removeEdgeTester() {}

    @Test
    public void sizeTester() {}

    @Test
    public void wordStatFileTester() {}

    @Test
    public void wordStatArrayTester() {}

    @Test
    public void wordCountTester() {}

    @Test
    public void wordCountEdgeTester() {}

    @Test
    public void wordRankTester() {}

    @Test
    public void wordRankEdgeTester() {}

    @Test
    public void mostCommonWordsTester() {}

    @Test
    public void mostCommonWordsEdgeTester() {}

    @Test
    public void leastCommonWordsTester() {}

    @Test
    public void leastCommonWordsEdgeTester() {}

    @Test
    public void mostCommonCollocationsTester() {}

    @Test
    public void mostCommonCollocationsEdgeTester() {}
}
