/**
 * @author Archie_Paredes
 * @version 1.0
 */

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

class hw2Test {
    @Test
    void testMap(){
        // 1.) Standard test. Just to get used to Function
        List a = new ArrayList();
        for (int i = 1; i <= 5; i++){
            a.add(i);
        }
        hw2 m = new hw2();
        Function<Integer, Integer> function1 = k -> {
            return k + 1;
        };
        a = m.map(a, function1);
        assertEquals("[2, 3, 4, 5, 6]", a.toString());

        // 2.) Test for name length
        String[] names = new String[]{"Mary", "Isla", "Sam"};
        int[] namesLength = new int[]{names[0].length(), names[1].length(), names[2].length()};
        Function<String, Integer> function2 = k -> {return k.length();};
        List b = Arrays.asList(names);
        List<Integer> namesLength2 = m.map(b, function2);
        int j = 0;
        for(Integer i : namesLength2){
            assertEquals(namesLength[j], i);
            j++;
        }

        // test for null
        try{
            m.map(null, function1);
            fail();
        } catch (NullPointerException e){}
        try{
            m.map(a, null);
            fail();
        } catch (NullPointerException e){}
    }

    @Test
    void testFoldLeft(){
        // Generic test from Programming Languages (SCALA)
        List a = new ArrayList();
        hw2 m = new hw2();
        for (int i = 1; i <= 5; i++){
            a.add(i);
        }
        BiFunction<String, Integer, String> f = (p,q) ->{
          return (p + "[" + q + "]");
        };
        assertEquals("@[1][2][3][4][5]",m.foldLeft("@", a, f));

        // test for null list
        assertEquals("@", m.foldLeft("@", null, f));

        // Test for list summation
        BiFunction<Integer, Integer, Integer> h = (p,q) ->{
            return (p + q);
        };

        List<Integer> b = new ArrayList<Integer>();
        int sum = 0;
        for (int i = 1; i <= 5; i++){
            b.add(5);
        }
        for(Integer i : b){
            sum += i;
        }
        assertEquals(sum, m.foldLeft(0, b, h));

        // Test for other param null
        try{
            m.foldLeft(null, b, h);
            fail();
        } catch (NullPointerException e){}
        try{
            m.foldLeft(1, b, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.foldLeft(null, b, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.foldLeft(null, null, null);
            fail();
        } catch (NullPointerException e){}

    }

    @Test
    void testFoldRight(){
        // Generic test from Programming Languages (SCALA)
        List a = new ArrayList();
        hw2 m = new hw2();
        for (int i = 1; i <= 5; i++){
            a.add(i);
        }
        BiFunction<Integer, String, String> h = (p,q) ->{
            return (q + "[" + p + "]");
        };
        assertEquals("@[5][4][3][2][1]",m.foldRight("@", a, h));

        // Test for null list
        assertEquals("@", m.foldRight("@", null, h));

        // Test for string concat
        List<String> b = new ArrayList<String>();
        b.add("!");
        b.add("World");
        b.add(" ");
        b.add("Hello");
        BiFunction<String, String, String> g = (p,q) ->{
            return (q + p);
        };
        assertEquals("Hello World!", m.foldRight("", b, g));

        // more test for nulls
        try{
            m.foldRight(null, b, g);
            fail();
        } catch (NullPointerException e){}
        try{
            m.foldRight(1, b, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.foldRight(null, b, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.foldRight(null, null, null);
            fail();
        } catch (NullPointerException e){}

    }


    @Test
    void testFilter(){
        List<Person> people = new ArrayList<Person>();
        hw2 m = new hw2();
        people.add(new Person(79000, "Aly P."));
        people.add(new Person(123000, "Bob S."));
        people.add(new Person(73400, "Sandy G."));
        people.add(new Person(55000, "Sam P."));
        // filters out anyone under 70k salary
        m.filter(people, (Person p) -> {return (p.getSalary() < 70000);});
        // Predicate<Person> lessThan70k = new Predicate<Person>() {
        //        @Override
        //        public boolean test(Person p)
        //        {
        //            return p.getSalary() < 70000;
        //        }
        //    };
        // m.filter(people, lessThan70k);
        int size = 0;
        for (Person p : people) {
            assertNotEquals("Sam P.", p.name());
            size++;
        }
        assertEquals(3, size);

        size = 0;
        // filters out anyone over 100k salary
        m.filter(people, (Person p) -> {return (p.getSalary() > 100000);});
        // Predicate<Person> greaterThan100k = new Predicate<Person>() {
        //        @Override
        //        public boolean test(Person p)
        //        {
        //            return p.getSalary() > 100000;
        //        }
        //    };
        // m.filter(people, greaterThan100k);
        for (Person p : people) {
            assertNotEquals("Sam P.", p.name());
            assertNotEquals("Bob S.", p.name());
            size++;
        }
        assertEquals(2, size);

        // Test for null
        try{
            m.filter(null, (Person p) -> {return (p.getSalary() > 100000);});
            fail();
        } catch (NullPointerException e){}
        try{
            m.filter(people, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.filter(null, null);
            fail();
        } catch (NullPointerException e){}
    }

    @Test
    void testBinFoldLeft(){
        List<Integer> a = new ArrayList<Integer>();
        hw2 m = new hw2();

        for (int i = 1; i <= 5; i++){
            a.add(i);
        }

        BiFunction<Integer, Integer, Integer> sum = (p,q) -> {
            if (q == 5) {
                return ++p;
            }
            else {
                return p;
            }
        };

        // test for 1
        assertEquals(1,m.binFoldLeft(0,a, sum));

        // test for multiple
        for (int i = 1; i <= 5; i++){
            a.add(5);
        }
        assertEquals(6,m.binFoldLeft(0,a, sum));

        // test for null list
        assertEquals(0, m.binFoldLeft(0,null,sum));

        // empty list
        List<Integer> b = new ArrayList<Integer>();
        assertEquals(0,m.binFoldLeft(0,b,sum));

        // more test for nulls
        try{
            m.binFoldLeft(null,a, sum);
            fail();
        } catch (NullPointerException e){}
        try{
            m.binFoldLeft(null,a, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.binFoldLeft(null,null, null);
            fail();
        } catch (NullPointerException e){}
        try{
            m.binFoldLeft(0,a, null);
            fail();
        } catch (NullPointerException e){}
    }
}