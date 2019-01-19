
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class hw2 {

    static <U> int size(Iterable<U> l){ // checks size
        int s = 0;
        for (U item : l)    s++;
        return s;
    }

    // argumments: list and function. type 1 = U(item), type 2 = V(list). return type list<V>
    static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
        List<V> newList = new ArrayList<V>();

        if (size(l) == 0)  return null;
        else {
            for (U item : l)    newList.add(f.apply(item));
        }

        return newList;
    }

    static <U> List<U> tail(Iterable<U> l){ // returns list of tailing l
        List<U> t = new ArrayList<U>();
        int i = 0;

        for(U item : l){
            if(i > 0) t.add(item);
            i++;
        }

        return t;
    }

    static <U> U head(Iterable<U> l){ // returns the head
        U h = null;

        for(U item : l){
            h = item;
            break;
        }

        return h;
    }


    static <U,V> V foldLeft(V e, Iterable<U> l, BiFunction<V,U,V> f){
        List<U> tail;
        U head;
        if(l != null){
            tail = tail(l);
            head = head(l);
        } else {
            return e;
        }

        if (size(l) == 0)  return e;
        else{
            return foldLeft(f.apply(e, head), tail, f);
        }
    }


    static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
        List<U> tail;
        U head;
        if(l != null){
            tail = tail(l);
            head = head(l);
        } else {
            return e;
        }

        if (size(l) == 0)  return e;
        else{
            return f.apply(head, foldRight(e, tail, f));
        }
    }

    static <U> List<U> filter(List<U> l, Predicate<U> p){
        List<U> t = l;
        for (U item : l){
            if(p.test(item)){
                t.remove(item);
            }
        }
        return t;
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