import org.junit.jupiter.api.Test;
import java.util.ArrayList;
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

        // 2.)
        List<Person> people = new ArrayList<Person>();
        m = new hw2();
        people.add(new Person(79000, "Aly P."));
        people.add(new Person(123000, "Bob S."));
        people.add(new Person(73400, "Sandy G."));
        people.add(new Person(55000, "Sam P."));

        Function<Integer, Integer> Christmas_Bonus = k -> {
            return k + 1000;
        };

        List<Integer> salary = new ArrayList<Integer>();
        for(Person p : people){
            salary.add(p.getSalary());
        }
        List<Integer> copy = salary;
        salary = m.map(salary, Christmas_Bonus);

    }

    @Test
    void testFoldLeft(){
        List a = new ArrayList();
        hw2 m = new hw2();
        for (int i = 1; i <= 5; i++){
            a.add(i);
        }
        BiFunction<String, Integer, String> f = (p,q) ->{
          return (p + "[" + q + "]");
        };
        assertEquals("@[1][2][3][4][5]",m.foldLeft("@", a, f));
        assertEquals("@", m.foldLeft("@", null, f));
    }

    @Test
    void testFoldRight(){
        List a = new ArrayList();
        hw2 m = new hw2();
        for (int i = 1; i <= 5; i++){
            a.add(i);
        }
        BiFunction<Integer, String, String> h = (p,q) ->{
            return (q + "[" + p + "]");
        };
        assertEquals("@[5][4][3][2][1]",m.foldRight("@", a, h));
        assertEquals("@", m.foldRight("@", null, h));
    }


    @Test
    void testFilter(){
        List a = new ArrayList();
        hw2 m = new hw2();
        for (int i = 1; i <= 5; i++){
            a.add(i);
        }
        Predicate<Integer> even = i -> {return(i % 2 == 0);};
        a = m.filter(a, even);
//        for (Integer i : a){
//            assertTrue(i%2 != 0);
//        }
    }
}