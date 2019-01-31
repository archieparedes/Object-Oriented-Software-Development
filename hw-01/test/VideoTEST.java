import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @author Archie_Paredes
 * */

public  class VideoTEST extends TestCase {

    public VideoTEST(String name) {
        super(name);
    }

    @Test
    public void testConstructorAndAttributes() {
        String title1 = "XX";
        String director1 = "XY";
        String title2 = " XX ";
        String director2 = " XY ";
        int year = 2002;

        VideoObj v1 = new VideoObj(title1, year, director1);
        assertSame(title1, v1.title());
        assertEquals(year, v1.year());
        assertSame(director1, v1.director());

        VideoObj v2 = new VideoObj(title2, year, director2);
        assertEquals(title1, v2.title());
        assertEquals(director1, v2.director());
    }
    @Test
    public void testConstructorExceptionYear() {
        try {
            new VideoObj("X", 1800, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("X", 5000, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("X", 1801, "Y");
            new VideoObj("X", 4999, "Y");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
    @Test
    public void testConstructorExceptionTitle() {
        try { // fail test for title = null
            new VideoObj(null, 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {// fail test for title empty
            new VideoObj("", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }

        try { // fail test for empty spaces: title
            new VideoObj(" ", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try { // fail test for many spaces:
            new VideoObj("       ", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
    }
    @Test
    public void testConstructorExceptionDirector() {
        try { // director fail null
            new VideoObj("Hello", 2002, null);
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("Hello", 2002, "Y ");
        } catch (IllegalArgumentException e) { }
        try { // fail test for multiple spaces
            new VideoObj("Hello", 2002, "   ");
            fail();
        } catch (IllegalArgumentException e) { }
        try { // fail test for director empty
            new VideoObj("Hello", 2002, "");
            fail();
        } catch (IllegalArgumentException e) { }
        try { // fail test for empty spaces: director
            new VideoObj("Hello", 2002, " ");
            fail();
        } catch (IllegalArgumentException e) { }
    }
    @Test
    public void testHashCode() {
        assertEquals
                (-875826552,
                        new VideoObj("None", 2009, "Zebra").hashCode());
        assertEquals
                (-1391078111,
                        new VideoObj("Blah", 1954, "Cante").hashCode());
    }
    //@Test
    public void testEquals() {
        VideoObj i = new VideoObj("The Protector", 2005, "Prachya Pinkaew");
        VideoObj j = new VideoObj("The Protector 2", 2013, "Prachya Pinkaew");
        VideoObj k = new VideoObj("Batman Begins", 2005, "Christopher Nolan");

        assertTrue(i.director().equals(j.director()));
        assertFalse(i.equals(j));
        assertTrue(k.equals(k));

    }
    // Video objects for test
    VideoObj i = new VideoObj("The Protector", 2005, "Prachya Pinkaew");
    VideoObj i_2 = new VideoObj("The_Protector", 2005, "Prachya Pinkaew");
    VideoObj j = new VideoObj("The Protector 2", 2013, "Prachya Pinkaew");
    VideoObj k = new VideoObj("Batman Begins", 2005, "Christopher Nolan");
    VideoObj k_2 = new VideoObj("Batman Begins", 2005, "Christopher Polan");

    @Test
    public void testCompareTo() {
        // compare director. object k is less than k_2. Polan and greater than Nolan
        assertEquals(-1, k_2.compareTo(k));

        // compares titles i_2 is greater than i because int value for '_' is greater than ' '
        assertEquals(1, i.compareTo(i_2));

        // test if object j is greater than i
        assertEquals(1, i.compareTo(j));

        // test if object i is less than j
        assertEquals(-1, j.compareTo(i));

        // test for the same
        assertEquals(0, j.compareTo(j));
    }
    @Test
    public void testToString() {
        // test for i
        assertEquals("The Protector (2005) : Prachya Pinkaew", i.toString());
        // test for i_2
        assertEquals("The_Protector (2005) : Prachya Pinkaew", i_2.toString());

        // test fail for year
        try {
            new VideoObj("Blah", 50000, "Cante").toString();
            fail();
        } catch (IllegalArgumentException e) { }

    }

    //////////////////////////////////////////////
    @Test
    public void testConstructorAndAttributes1() {
        String title1 = "XX";
        String director1 = "XY";
        String title2 = " XX ";
        String director2 = " XY ";
        int year = 2002;

        VideoObj v1 = new VideoObj(title1, year, director1);
        assertSame(title1, v1.title());
        assertEquals(year, v1.year());
        assertSame(director1, v1.director());

        VideoObj v2 = new VideoObj(title2, year, director2);
        assertEquals(title1, v2.title());
        assertEquals(director1, v2.director());
    }
    @Test
    public void testConstructorExceptionYear1() {
        try {
            new VideoObj("X", 1800, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("X", 5000, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("X", 1801, "Y");
            new VideoObj("X", 4999, "Y");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
    @Test
    public void testConstructorExceptionTitle1() {
        try {
            new VideoObj(null, 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj("", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            new VideoObj(" ", 2002, "Y");
            fail();
        } catch (IllegalArgumentException e) { }
    }
    

    @Test
    public void testEquals1() {
        String title = "A";
        int year = 2009;
        String director = "Zebra";
        VideoObj a = new VideoObj(title,year,director);
        assertTrue( a.equals(a) );
        assertTrue( a.equals( new VideoObj(title, year, director) ) );
        assertTrue( a.equals( new VideoObj(new String(title), year, director) ) );
        assertTrue( a.equals( new VideoObj(title, year, new String(director)) ) );
        assertFalse( a.equals( new VideoObj(title+"1", year, director) ) );
        assertFalse( a.equals( new VideoObj(title, year+1, director) ) );
        assertFalse( a.equals( new VideoObj(title, year, director+"1") ) );
        assertFalse( a.equals( new Object() ) );
        assertFalse( a.equals( null ) );
    }
    @Test
    public void testCompareTo1() {
        String title = "A", title2 = "B";
        int year = 2009, year2 = 2010;
        String director = "Zebra", director2 = "Zzz";
        VideoObj a = new VideoObj(title,year,director);
        VideoObj b = new VideoObj(title2,year,director);
        assertTrue( a.compareTo(b) < 0 );
        assertTrue( a.compareTo(b) == -b.compareTo(a) );
        assertTrue( a.compareTo(a) == 0 );

        b = new VideoObj(title,year2,director);
        assertTrue( a.compareTo(b) < 0 );
        assertTrue( a.compareTo(b) == -b.compareTo(a) );

        b = new VideoObj(title,year,director2);
        assertTrue( a.compareTo(b) < 0 );
        assertTrue( a.compareTo(b) == -b.compareTo(a) );

        b = new VideoObj(title2,year2,director2);
        assertTrue( a.compareTo(b) < 0 );
        assertTrue( a.compareTo(b) == -b.compareTo(a) );

        try {
            a.compareTo(null);
            fail();
        } catch ( NullPointerException e ) {}
        catch ( ClassCastException e ) {}
    }
    @Test
    public void testToString1() {
        String s = new VideoObj("A",2000,"B").toString();
        assertEquals( s, "A (2000) : B" );
        s = new VideoObj(" A ",2000," B ").toString();
        assertEquals( s, "A (2000) : B" );
    }

}