import junit.framework.TestCase;
import org.junit.Test;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;


public class InventoryTEST extends TestCase {
    public InventoryTEST(String name) {
        super(name);
    }

    // TODO
    VideoObj i = new VideoObj("The Protector", 2005, "Prachya Pinkaew");
    VideoObj i_2 = new VideoObj("The_Protector", 2005, "Prachya Pinkaew");
    VideoObj j = new VideoObj("The Protector 2", 2013, "Prachya Pinkaew");
    VideoObj k = new VideoObj("Batman Begins", 2005, "Christopher Nolan");
    VideoObj k_2 = new VideoObj("Batman Begins", 2005, "Christopher Polan");
    /*
     * Test size method for inventory
     * */
    @Test
    public void testSize() {
        InventorySet bb = new InventorySet();
        // test for zeros
        assertEquals(0, bb.size());
        bb.addNumOwned(i, 20);
        assertEquals(1, bb.size());
        bb.addNumOwned(j, 30);
        bb.addNumOwned(k, 30);
        // test for 3 records
        assertEquals(3, bb.size());
        // test for no records
        bb.clear();
        assertEquals(0, bb.size());
    }

    /*
     * Testing for addNumOwned
     * */
    //@Test
//    public void testAddNumOwned() {
//        InventorySet bb = new InventorySet();
//        bb.addNumOwned(i, 20);
//        bb.addNumOwned(j, 30);
//        bb.addNumOwned(k, 30);
//
//        // test for new record
//        Record check = bb.get(k);
//        assertEquals(30, check.numOwned);
//
//        // test contains
//        bb.addNumOwned(k, 60);
//        check = bb.get(k); // refresh
//        assertEquals(60, check.numOwned);
//
//        // test for no record
//        bb.addNumOwned(j, 0);
//        assertEquals(2, bb.size()); // make sure it got deleted
//        assertNull(bb.get(j));
//    }
//    //@Test
//    public void testCheckOutCheckIn() {
//        InventorySet bb = new InventorySet();
//        bb.addNumOwned(i, 20);
//        bb.addNumOwned(j, 30);
//        bb.addNumOwned(k, 40);
//
//        // rent video i 5 times
//        for(int k = 0; k < 5; k++){
//            bb.checkOut(i);
//        }
//        assertEquals(5, bb.get(i).numOut);
//        assertEquals(5, bb.get(i).numRentals);
//
//        for(int p = 0; p < 3; p++){ // returning 3
//            bb.checkIn(i);
//        }
//        assertEquals(2, bb.get(i).numOut); // only two out rented
//        assertEquals(5, bb.get(i).numRentals); // rentals stay the same
//
//        bb.addNumOwned(j, 0);
//        try{
//            bb.checkOut(j);
//            fail();
//        } catch (Exception e){ }
//
//        for(int p = 0; p < 40; p++){
//            bb.checkOut(k);
//        }
//        try{
//            bb.checkOut(k);
//            fail(); // all is rented out
//        } catch (Exception e){ }
//        assertEquals(40, bb.get(k).numOut);
//        bb.addNumOwned(k, 60);
//        for(int p = 0; p < 20; p++){
//            bb.checkOut(k);
//        }
//        for(int p = 0; p < 20; p++){
//            bb.checkIn(k);
//        }
//        assertEquals(60, bb.get(k).numRentals);
//        for(int p = 0; p < 40; p++){ // return all
//            bb.checkIn(k);
//        }
//        assertEquals(0, bb.get(k).numOut);
//        try{
//            bb.checkIn(k);
//            fail(); // all videos should be return therefore fail
//        } catch (Exception e) {}
//
//        try{
//            bb.checkIn(j);
//            fail(); // there are no records
//        } catch (Exception e) {}
//    }
//    /*
//     * Test for clear method
//     * */
//    @Test
//    public void testClear() {
//        InventorySet bb = new InventorySet();
//        bb.addNumOwned(i, 20);
//        bb.addNumOwned(j, 30);
//        bb.addNumOwned(k, 40);
//        bb.addNumOwned(k_2, 50);
//        bb.addNumOwned(i_2, 60);
//        bb.clear(); // clears records
//        assertEquals(0, bb.size());
//
//        VideoObj[] videos = new VideoObj[]{i,j,k,k_2,i_2};
//        for(int p = 0; p < videos.length; p++){
//            assertNull(bb.get(videos[p]));
//        }
//    }
//    /*
//     * Test for get method
//     * */
//
//    @Test
//    public void testGet() {
//        InventorySet bb = new InventorySet();
//        VideoObj[] videos = new VideoObj[]{i,j,k,k_2,i_2};
//        int i = 10;
//        for(int p = 0; p < videos.length; p++){
//            bb.addNumOwned(videos[p], i);
//            i += 10;
//        }
//        for(int p = videos.length-1; p >=0 ; p--){
//            i -= 10;
//            Record r = bb.get(videos[p]);
//            assertEquals(i, r.numOwned);
//        }
//
//        for(int p = 0; p < videos.length; p++){
//            assertNotNull(bb.get(videos[p]));
//        }
//        // test for no record
//        bb.addNumOwned(videos[videos.length - 1], 0);
//        assertNull(bb.get(videos[videos.length - 1]));
//    }
    @Test
    public void testToCollection() {
        // Be sure to test that changing records in the returned
        // collection does not change the original records in the
        // inventory.  ToCollection should return a COPY of the records,
        // not the records themselves.
        InventorySet bb = new InventorySet();
        VideoObj[] videos = new VideoObj[]{i,j,k,k_2,i_2};
        int i = 10;
        for(int p = 0; p < videos.length; p++){
            bb.addNumOwned(videos[p], i);
            i += 10;
        }

        Iterator<Record> iterator = bb.toCollection().iterator();
        while(iterator.hasNext()){
            assertNotNull(iterator.next());
        }
    }
    ///////////////////////////////////////////////////////////////////////////////
    InventorySet s = new InventorySet();
    final VideoObj v1 = new VideoObj( "A", 2000, "B" );
    final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
    final VideoObj v2 = new VideoObj( "B", 2000, "B" );
    @Test
    public void testSize1() {
        assertEquals( 0, s.size() );
        s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
        s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
        s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
        s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
        s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
        try { s.addNumOwned(v1, -3); } catch ( IllegalArgumentException e ) {}
        assertEquals( 0, s.size() );
    }
    @Test
    public void testAddNumOwned1() {
        assertEquals( 0, s.size() );
        s.addNumOwned(v1, 1);     assertEquals( v1, s.get(v1).video );
        assertEquals( 1, s.get(v1).numOwned );
        s.addNumOwned(v1, 2);     assertEquals( 3, s.get(v1).numOwned );

        s.addNumOwned(v1, -1);    assertEquals( 2, s.get(v1).numOwned );
        s.addNumOwned(v2, 1);     assertEquals( 2, s.get(v1).numOwned );
        s.addNumOwned(v1copy, 1); assertEquals( 3, s.get(v1).numOwned );
        assertEquals( 2, s.size() );
        s.addNumOwned(v1, -3);
        assertEquals( 1, s.size() );
        try { s.addNumOwned(null, 1);   fail(); } catch ( IllegalArgumentException e ) {}
    }
    @Test
    public void testCheckOutCheckIn1() {
        try { s.checkOut(null);     fail(); } catch ( IllegalArgumentException e ) {}
        try { s.checkIn(null);      fail(); } catch ( IllegalArgumentException e ) {}
        s.addNumOwned(v1, 2); assertTrue( s.get(v1).numOut == 0 && s.get(v1).numRentals == 0 );
        s.checkOut(v1);       assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 1 );
        try { s.addNumOwned(v1,-3); fail(); } catch ( IllegalArgumentException e ) {}
        try { s.addNumOwned(v1,-2); fail(); } catch ( IllegalArgumentException e ) {}
        s.addNumOwned(v1,-1); assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 1 );
        s.addNumOwned(v1, 1); assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 1 );
        s.checkOut(v1);       assertTrue( s.get(v1).numOut == 2 && s.get(v1).numRentals == 2 );
        try { s.checkOut(v1);       fail(); } catch ( IllegalArgumentException e ) {}
        s.checkIn(v1);        assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 2 );
        s.checkIn(v1copy);    assertTrue( s.get(v1).numOut == 0 && s.get(v1).numRentals == 2 );
        try { s.checkIn(v1);        fail(); } catch ( IllegalArgumentException e ) {}
        try { s.checkOut(v2);       fail(); } catch ( IllegalArgumentException e ) {}
        s.checkOut(v1);       assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 3 );
    }

    public void testClear1() {
        s.addNumOwned(v1, 2); assertEquals( 1, s.size() );
        s.addNumOwned(v2, 2); assertEquals( 2, s.size() );
        s.clear();            assertEquals( 0, s.size() );
        try { s.checkOut(v2);       fail(); } catch ( IllegalArgumentException e ) {}
    }
    @Test
    public void testGet1() {
        s.addNumOwned(v1, 1);
        Record r1 = s.get(v1);
        Record r2 = s.get(v1);
        assertFalse( r1.equals(r2) );
        assertTrue( r1 != r2 );
    }
    @Test
    public void testToCollection1() {
        // Be sure to test that changing records in the returned
        // collection does not change the original records in the
        // inventory.  ToCollection should return a COPY of the records,
        // not the records themselves.
        assertTrue(s.toCollection() != s.toCollection());

        s.addNumOwned(v1, 1);
        Record r1 = (Record) s.toCollection().iterator().next();
        Record r2 = (Record) s.toCollection().iterator().next();
        assertTrue( r1 != r2);
        assertTrue( r1.numOwned == r2.numOwned);

        s.addNumOwned(v1, 1);
        Record r3 = (Record) s.toCollection().iterator().next();
        assertTrue( r1.numOwned != r3.numOwned);

        s.clear();
        s.addNumOwned(v1,10);
        s.addNumOwned(v2,20);
        Set<VideoObj> expected = new HashSet<VideoObj>();
        expected.add(v1);
        expected.add(v2);

        Iterator<Record> i = s.toCollection().iterator();
        //  The following would make sure that the collection is
        //  immutable, but this is not case here
        //try { i.remove(); fail(); }
        //catch (UnsupportedOperationException e) { }
        while(i.hasNext()) {
            Record r = i.next();
            assertTrue(expected.contains(r.video));
            expected.remove(r.video);
        }
        assertTrue(expected.isEmpty());
    }
}

