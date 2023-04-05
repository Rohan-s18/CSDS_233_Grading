import java.util.NoSuchElementException;

import org.junit.*;

/* 
 * Tests are structured such that if you pass more than half of cases in a test method, you will be given 1 point out of 26 for
 * that test method (0 otherwise). Therefore, some edge cases can fail while still getting full credit. CMDbGroup and some
 * CMDbProfile methods are graded for completion.
*/
public class ExtraTester {
    /* Verifies that the values in the PriorityQueue are the same as the reverse-sorted input values, destroying the queue in
       the process */
    private void testHeapOrder(PriorityQueue<Integer,Object> actual, Object[] expectedValues) {
        for (Object value : expectedValues) {
            Assert.assertEquals(value, actual.poll());
        }
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void priorityQueueTester() {
        int failCount = 0;
        try {
            PriorityQueue<Integer,String> q = new PriorityQueue<>();
            Assert.assertEquals(0, q.size());
        } catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<String,Math> q = new PriorityQueue<>();
            Assert.assertEquals(0, q.size());
        } catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 2);
    }
    
    @Test
    public void priorityQueueArrayTester() {
        int failCount = 0;
        try {
            PriorityQueue<Integer,Object> q = new PriorityQueue<>(new Integer[]{1, 2, 3}, new Object[]{"1", 2, '3'});
            testHeapOrder(q, new Object[]{'3', 2, "1"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q = new PriorityQueue<>(new Integer[]{3, 2, 1}, new Object[]{'3', 2, "1"});
            testHeapOrder(q, new Object[]{'3', 2, "1"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q = new PriorityQueue<>(new Integer[]{2, 3, 1}, new Object[]{2, '3', "1"});
            testHeapOrder(q, new Object[]{'3', 2, "1"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,String> q = new PriorityQueue<>(new Integer[]{}, new String[]{});
            Assert.assertEquals(0, q.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q = new PriorityQueue<>(new Integer[]{6}, new String[]{"str"});
            testHeapOrder(q, new String[]{"str"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
            new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
            testHeapOrder(q, new Integer[]{8, 7, 7, 6, 5, 4, 4, 2, 1});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q = new PriorityQueue<>(new Integer[]{8, 8, 8, 8, 8}, new Integer[]{8, 8, 8, 8, 8});
            testHeapOrder(q, new Integer[]{8, 8, 8, 8, 8});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 7);
    }
    
    @Test
    public void addTester() {
        int failCount = 0;
        try {
            PriorityQueue<Integer,Object> q1 = new PriorityQueue<>();
            q1.add(1, "1");
            q1.add(2, 2);
            q1.add(3, '3');
            testHeapOrder(q1, new Object[]{'3', 2, "1"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q2 = new PriorityQueue<>();
            q2.add(3, '3');
            q2.add(2, 2);
            q2.add(1, "1");
            testHeapOrder(q2, new Object[]{'3', 2, "1"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q3 = new PriorityQueue<>();
            q3.add(2, 2);
            q3.add(3, '3');
            q3.add(1, "1");
            testHeapOrder(q3, new Object[]{'3', 2, "1"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q4 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
            new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
            q4.add(9, 9);
            q4.add(0, 0);
            q4.add(3, 3);
            q4.add(7, 7);
            testHeapOrder(q4, new Integer[]{9, 8, 7, 7, 7, 6, 5, 4, 4, 3, 2, 1, 0});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 4);
    }
    
    @Test
    public void updateTester() {
        int failCount = 0;
        try {
            PriorityQueue<Integer,Object> q1 = new PriorityQueue<>();
            q1.update(0, 0);
        } catch (NoSuchElementException e) {} // Expected
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try{
            PriorityQueue<Integer,Object> q2 = new PriorityQueue<>(new Integer[]{3}, new String[]{"3"});
            q2.update(33, "3");
            testHeapOrder(q2, new String[]{"3"});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q3 = new PriorityQueue<>(new Integer[]{1, 3, 5}, new Integer[]{1, 3, 5});
            q3.update(4, 1);
            testHeapOrder(q3, new Integer[]{5, 1, 3});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q4 = new PriorityQueue<>(new Integer[]{1, 3, 5}, new Integer[]{1, 3, 5});
            q4.update(6, 3);
            testHeapOrder(q4, new Integer[]{3, 5, 1});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q5 = new PriorityQueue<>(new Integer[]{1, 3, 5}, new Integer[]{1, 3, 5});
            q5.update(0, 5);
            testHeapOrder(q5, new Integer[]{3, 1, 5});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q6 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
            new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
            q6.update(3,7);
            testHeapOrder(q6, new Integer[]{8, 7, 6, 5, 4, 4, 7, 2, 1});
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 6);
    }
    
    @Test
    public void peekTester() {
        int failCount = 0;
        try {
            PriorityQueue<Integer,Object> q1 = new PriorityQueue<>(new Integer[]{3}, new String[]{"3"});
            Assert.assertEquals("3", q1.peek());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q2 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
            new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
            Assert.assertEquals(8, q2.peek());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 2);
    }
    
    @Test
    public void peekMultipleTester() {
        // Just check that it doesn't error
        PriorityQueue<Integer,String> q1 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
        new String[]{"8", "4", "5", "2", "7", "4", "6", "1", "7"});
        q1.peek(4);
    }
    
    @Test
    public void pollTester() {
        int failCount = 0;
        PriorityQueue<Integer,Object> q1 = new PriorityQueue<>(new Integer[]{3}, new String[]{"3"});
        try {
            Assert.assertEquals("3", q1.poll());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            q1.poll();
        } catch (NoSuchElementException e) {} // Expected
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q2 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
            new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
            Assert.assertEquals(8, q2.poll());
            Assert.assertEquals(8, q2.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void pollSpecificTester() {
        int failCount = 0;
        PriorityQueue<Integer,Object> q1 = new PriorityQueue<>(new Integer[]{3}, new String[]{"3"});
        try {
            Assert.assertEquals((Integer)3, q1.poll("3"));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            q1.poll("3");
            Assert.fail();
        } catch (NoSuchElementException e) {} // Expected
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            PriorityQueue<Integer,Object> q2 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
            new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
            Assert.assertEquals((Integer)6, q2.poll(6));
            Assert.assertEquals(8, q2.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void sizeTester() {
        int failCount = 0;
        PriorityQueue<Integer,Integer> q1 = new PriorityQueue<>(new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7},
        new Integer[]{8, 4, 5, 2, 7, 4, 6, 1, 7});
        try {
            Assert.assertEquals(9,q1.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            q1.add(4, 2);
            Assert.assertEquals(10,q1.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            q1.poll(2);
            Assert.assertEquals(9,q1.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            q1.poll();
            Assert.assertEquals(8,q1.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            q1.poll(9);
            Assert.fail();
        }
        catch (NoSuchElementException e) {
            Assert.assertEquals(8,q1.size());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 5);
    }
    
    @Test
    public void cmdbProfileTester() {
        new CMDbProfile("JocularGlint54");
    }
    
    @Test
    public void changeUserNameTester() {
        int failCount = 0;
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        try {
            p1.rate("Cars 2", 10);
            Assert.assertEquals("JocularGlint54", p1.profileInformation().substring(0,14));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            p1.changeUserName("rxs1182");
            Assert.assertEquals("rxs1182", p1.profileInformation().substring(0,7));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 2);
    }
    
    @Test
    public void rateTester() {
        int failCount = 0;
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        try {
            Assert.assertFalse(p1.rate("Cars 2", 11));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            Assert.assertTrue(p1.rate("Ratatouille", 9));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            Assert.assertEquals("JocularGlint54 (1)\nFavorite Movie: Ratatouille", p1.profileInformation());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void changeRatingTester() {
        int failCount = 0;
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        try {
            Assert.assertFalse(p1.changeRating("Cars 2", 10));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            p1.rate("Cars 2", 10);
            Assert.assertFalse(p1.changeRating("Cars 2", 11));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            Assert.assertTrue(p1.changeRating("Cars 2", 1));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void removeRatingTester() {
        int failCount = 0;
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        p1.rate("Cars 2", 10);
        p1.rate("Ratatouille", 9);
        try {
            Assert.assertTrue(p1.removeRating("Cars 2"));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            Assert.assertEquals("JocularGlint54 (1)\nFavorite Movie: Ratatouille", p1.profileInformation());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            Assert.assertFalse(p1.removeRating("The Phantom Menace"));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void favoriteTester() {
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        int failCount = 0;
        try {
            Assert.assertNull(p1.favorite());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            p1.rate("Ratatouille", 9);
            Assert.assertArrayEquals(new String[]{"Ratatouille"}, p1.favorite());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            p1.rate("Cars 2", 10);
            Assert.assertArrayEquals(new String[]{"Cars 2"}, p1.favorite());
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void favoriteMultipleTester() {
        // Just check for no errors
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        p1.rate("Ratatouille", 9);
        p1.rate("Cars 2", 10);
        p1.favorite(1);
    }
    
    @Test
    public void profileInformationTester() {
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        p1.rate("Cars 2", 10);
        p1.rate("Ratatouille", 9);
        Assert.assertEquals("JocularGlint54 (2)\nFavorite Movie: Cars 2", p1.profileInformation());
    }
    
    @Test
    public void equalsTester() {
        int failCount = 0;
        CMDbProfile p1 = new CMDbProfile("JocularGlint54");
        try {
            String s1 = "JocularGlint54";
            Assert.assertFalse(p1.equals(s1));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            CMDbProfile p2 = new CMDbProfile("JocularGlint54");
            Assert.assertTrue(p1.equals(p2));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        try {
            CMDbProfile p3 = new CMDbProfile("Lime029");
            Assert.assertFalse(p1.equals(p3));
        }
        catch (Throwable e) {
            e.printStackTrace();
            failCount++;
        }
        Tester.assertPass(failCount, 3);
    }
    
    @Test
    public void cmdbGroupTester() {
        try {
            new CMDbGroup();
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void registeredUsersTester() {
        try {
            CMDbGroup.registeredUsers();
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void groupTester() {
        try {
            CMDbGroup g1 = new CMDbGroup();
            g1.group();
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void addMemberTester() {
        try {
            CMDbGroup g1 = new CMDbGroup();
            g1.addMember(new CMDbProfile("JocularGlint54"));
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void addMembersTester() {
        try {
            CMDbGroup g1 = new CMDbGroup();
            g1.addMember(new CMDbProfile[]{new CMDbProfile("JocularGlint54"), new CMDbProfile("Lime029")});
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void favoriteUserTester() {
        try {
            CMDbGroup g1 = new CMDbGroup();
            g1.addMember(new CMDbProfile[]{new CMDbProfile("JocularGlint54"), new CMDbProfile("Lime029")});
            CMDbGroup.favorite("Lime029");
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void groupFavoritesTester() {
        try {
            CMDbGroup g1 = new CMDbGroup();
            g1.addMember(new CMDbProfile[]{new CMDbProfile("JocularGlint54"), new CMDbProfile("Lime029")});
            g1.groupFavorites();
        } catch (Throwable e) {} // Completion
    }
    
    @Test
    public void randomMovieTester() {
        try {
            CMDbGroup g1 = new CMDbGroup();
            g1.addMember(new CMDbProfile[]{new CMDbProfile("JocularGlint54"), new CMDbProfile("Lime029")});
            g1.randomMovie(3);
        } catch (Throwable e) {} // Completion
    }
}
