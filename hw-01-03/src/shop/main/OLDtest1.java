package shop.main;
import org.junit.Test;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Comparator;
import java.util.Iterator;
import junit.framework.TestCase;

public class OLDtest1 extends TestCase {
    private Inventory _inventory = Data.newInventory();
//    public OLDtest1(String name){
//        super(name);
//    }

    Video i = Data.newVideo("The Protector", 2005, "Prachya Pinkaew");
    Video i_2 = Data.newVideo("The_Protector", 2005, "Prachya Pinkaew");
    Video j = Data.newVideo("The Protector 2", 2013, "Prachya Pinkaew");
    Video k = Data.newVideo("Batman Begins", 2005, "Christopher Nolan");
    Video k_2 = Data.newVideo("Batman Begins", 2005, "Christopher Polan");

    @Test
    public void testAdd(){
        assertEquals(0, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i, 20).run());
        assertEquals(1, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i, 5).run());
        assertEquals(1, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, i_2, 20).run());
        assertEquals(2, _inventory.size());
    }

    @Test
    public void testClear(){
        assertTrue(Data.newAddCmd(_inventory, i, 20).run());
        assertTrue(Data.newAddCmd(_inventory, i, 5).run());
        assertTrue(Data.newOutCmd(_inventory, i).run()); // check out 1. should fail to clear
        //assertFalse(Data.newClearCmd(_inventory).run());
        assertTrue(Data.newAddCmd(_inventory, i_2, 20).run());
        assertTrue(Data.newInCmd(_inventory, i).run()); // check in 1. should succeed to clear
        assertTrue(Data.newClearCmd(_inventory).run());
        assertEquals(0, _inventory.size());
       // assertFalse(Data.newClearCmd(_inventory).run()); // can't clear twice
    }

    @Test
    public void testnewOutnewIn(){
        assertTrue(Data.newAddCmd(_inventory, i, 20).run());
        assertTrue(Data.newOutCmd(_inventory, i).run());
        assertTrue(Data.newAddCmd(_inventory, i_2, 20).run());
        assertFalse(Data.newOutCmd(_inventory, j).run());
        assertFalse(Data.newInCmd(_inventory, i_2).run());
        assertTrue(Data.newInCmd(_inventory, i).run());
        assertTrue(Data.newOutCmd(_inventory, i_2).run());
        assertTrue(Data.newInCmd(_inventory, i_2).run());
    }

    @Test
    public void testIterator(){
        Video a = Data.newVideo("a", 2001, "Prachya Pinkaew");
        Video b = Data.newVideo("b", 2002, "Prachya Pinkaew");
        Video c = Data.newVideo("c", 2003, "Prachya Pinkaew");
        Video d = Data.newVideo("d", 2004, "Prachya Pinkaew");
        Data.newAddCmd(_inventory, a, 20).run();
        Data.newAddCmd(_inventory, c, 20).run();
        Data.newAddCmd(_inventory, b, 20).run();
        Data.newAddCmd(_inventory, d, 20).run();

        Comparator<Record> f = new Comparator<Record>() { // sorts from smallest to largest
            @Override
            public int compare(Record o1, Record o2) {
                return (o1.toString()).compareTo(o2.toString());
            }
        };

        Comparator<Record> f2 = new Comparator<Record>() { // sorts from largest to smallest
            @Override
            public int compare(Record o1, Record o2) {
                return (o2.toString()).compareTo(o1.toString());
            }
        };

        Iterator r = _inventory.iterator(f);
        Iterator s = _inventory.iterator(f2);
        while(r.hasNext()){
            System.out.println(r.next().toString());
        }
        while(s.hasNext()){
            System.out.println(s.next().toString());
        }
//        String w = r.next().toString();
//        String x = r.next().toString();
//        String y = r.next().toString();
//        String z = r.next().toString();
//
//        // bad way to check if something is sorted
//        assertEquals(0, w.compareTo(w));
//        assertTrue( w.compareTo(x) < 0);
//        assertTrue( w.compareTo(y) < 0);
//        assertTrue(w.compareTo(z) < 0);
//
//        assertEquals(0, x.compareTo(x));
//        assertTrue( x.compareTo(y) < 0);
//        assertTrue( x.compareTo(z) < 0);
//        assertTrue(x.compareTo(w) > 0);
//
//        assertTrue(y.compareTo(z) < 0);
//        assertEquals(0, y.compareTo(y));
//        assertTrue(y.compareTo(x) > 0);
//        assertTrue(y.compareTo(w) > 0);
//
//        assertFalse(r.hasNext()); // done
    }

    /////////////////////////////
    @Test
    public void testConstructorAndAttributes() {
        String title1 = "XX";
        String director1 = "XY";
        String title2 = " XX ";
        String director2 = " XY ";
        int year = 2002;

        Video v1 = Data.newVideo(title1, year, director1);
        assertSame(title1, v1.title());
        assertEquals(year, v1.year());
        assertSame(director1, v1.director());

        Video v2 = Data.newVideo(title2, year, director2);
        assertEquals(title1, v2.title());
        assertEquals(director1, v2.director());
    }

    @Test
    public void testConstructorExceptionYear() {
        try {
            Data.newVideo("X", 1800, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            Data.newVideo("X", 5000, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            Data.newVideo("X", 1801, "Y");
            Data.newVideo("X", 4999, "Y");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testConstructorExceptionTitle() {
        try {
            Data.newVideo(null, 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            Data.newVideo("", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            Data.newVideo(" ", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
    }

    private void expect(Video v, String s) {
        assertEquals(s,_inventory.get(v).toString());
    }
    private void expect(Record r, String s) {
        assertEquals(s,r.toString());
    }

}   
