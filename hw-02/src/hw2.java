
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class hw2 {

    // argumments: list and function. type 1 = U(item), type 2 = V(list). return type list<V>
    static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
        List<V> newList = new ArrayList<V>();

        int size = 0;
        // check size
        for (U item : l)    size++;

        if (size == 0)  return null;
        else {
            for (U item : l)    newList.add(f.apply(item));
        }

        return newList;
    }


    static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
        return null;
    }




    static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
        return null;
    }



    static <U> List<U> filter(List<U> l, Predicate<U> p){
        return null;
    }




    static <U> U binFoldLeft(U e, List<U>l, BiFunction<U,U,U> f){
        return null;
    }

    public static void main(String[] args) {
        // Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
        // names = ['Mary', 'Isla', 'Sam']
        // for i in range(len(names)):
        // names[i] = hash(names[i])

        // Use foldleft to calculate the sum of a list of integers.
        // i.e write a method: int sum(List<Integer> l)

        // Use foldRight to concatenate a list of strings i.e write a method
        // String s (List<String> l)

        // consider an array of Persons. Use filter to
        // print the names of the Persons whose salary is
        // greater than 100000

        //Use binFoldLeft to find the number of occurrences
        // of 5 in an array of integers


    }

}

class Person{
    final int salary;
    final String name;

    Person(int salary, String name){
        this.salary = salary;
        this.name = name;
    }

    int getSalary() { return salary; }
    String name() { return name;}

}