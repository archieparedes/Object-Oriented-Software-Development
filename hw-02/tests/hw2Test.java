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


    }
}