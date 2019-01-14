import junit.framework.TestCase;
import org.junit.Test;
import java.util.Iterator;


import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void testAddNumOwned() {
        InventorySet bb = new InventorySet();
        bb.addNumOwned(i, 20);
        bb.addNumOwned(j, 30);
        bb.addNumOwned(k, 30);

        // test for new record
        Record check = bb.get(k);
        assertEquals(30, check.numOwned);

        // test contains
        bb.addNumOwned(k, 60);
        check = bb.get(k); // refresh
        assertEquals(60, check.numOwned);

        // test for no record
        bb.addNumOwned(j, 0);
        assertEquals(2, bb.size()); // make sure it got deleted
        assertNull(bb.get(j));
    }
    @Test
    public void testCheckOutCheckIn() {
        InventorySet bb = new InventorySet();
        bb.addNumOwned(i, 20);
        bb.addNumOwned(j, 30);
        bb.addNumOwned(k, 40);

        // rent video i 5 times
        for(int k = 0; k < 5; k++){
            bb.checkOut(i);
        }
        assertEquals(5, bb.get(i).numOut);
        assertEquals(5, bb.get(i).numRentals);

        for(int p = 0; p < 3; p++){ // returning 3
            bb.checkIn(i);
        }
        assertEquals(2, bb.get(i).numOut); // only two out rented
        assertEquals(5, bb.get(i).numRentals); // rentals stay the same

        bb.addNumOwned(j, 0);
        try{
            bb.checkOut(j);
            fail();
        } catch (Exception e){ }

        for(int p = 0; p < 40; p++){
            bb.checkOut(k);
        }
        try{
            bb.checkOut(k);
            fail(); // all is rented out
        } catch (Exception e){ }
        assertEquals(40, bb.get(k).numOut);
        bb.addNumOwned(k, 60);
        for(int p = 0; p < 20; p++){
            bb.checkOut(k);
        }
        for(int p = 0; p < 20; p++){
            bb.checkIn(k);
        }
        assertEquals(60, bb.get(k).numRentals);
        for(int p = 0; p < 40; p++){ // return all
            bb.checkIn(k);
        }
        assertEquals(0, bb.get(k).numOut);
        try{
            bb.checkIn(k);
            fail(); // all videos should be return therefore fail
        } catch (Exception e) {}

        try{
            bb.checkIn(j);
            fail(); // there are no records
        } catch (Exception e) {}
    }
    /*
     * Test for clear method
     * */
    @Test
    public void testClear() {
        InventorySet bb = new InventorySet();
        bb.addNumOwned(i, 20);
        bb.addNumOwned(j, 30);
        bb.addNumOwned(k, 40);
        bb.addNumOwned(k_2, 50);
        bb.addNumOwned(i_2, 60);
        bb.clear(); // clears records
        assertEquals(0, bb.size());

        VideoObj[] videos = new VideoObj[]{i,j,k,k_2,i_2};
        for(int p = 0; p < videos.length; p++){
            assertNull(bb.get(videos[p]));
        }
    }
    /*
     * Test for get method
     * */

    @Test
    public void testGet() {
        InventorySet bb = new InventorySet();
        VideoObj[] videos = new VideoObj[]{i,j,k,k_2,i_2};
        int i = 10;
        for(int p = 0; p < videos.length; p++){
            bb.addNumOwned(videos[p], i);
            i += 10;
        }
        for(int p = videos.length-1; p >=0 ; p--){
            i -= 10;
            Record r = bb.get(videos[p]);
            assertEquals(i, r.numOwned);
        }

        for(int p = 0; p < videos.length; p++){
            assertNotNull(bb.get(videos[p]));
        }
        // test for no record
        bb.addNumOwned(videos[videos.length - 1], 0);
        assertNull(bb.get(videos[videos.length - 1]));
    }
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
}

