/**
* @author Archie Paredes
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class hw2 {
    /**
     * @return size of list l
     */
    static <U> int size(Iterable<U> l){ // checks size
        int s = 0;
        for (U item : l)    s++;
        return s;
    }

    /**
     * @return list mapped to function f
     */
    // argumments: list and function. type 1 = U(item), type 2 = V(list). return type list<V>
    static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
        List<V> newList = new ArrayList<V>();

        if (size(l) == 0)  return null;
        else {
            for (U item : l)    newList.add(f.apply(item));
        }

        return newList;
    }

    /**
     * @return list l excluding the first product
     */
    static <U> List<U> tail(Iterable<U> l){ // returns list of tailing l
        List<U> t = new ArrayList<U>();
        int i = 0;

        for(U item : l){
            if(i > 0) t.add(item);
            i++;
        }

        return t;
    }

    /**
     * @return the first product of l
     */
    static <U> U head(Iterable<U> l){ // returns the head
        U h = null;

        for(U item : l){
            h = item;
            break;
        }

        return h;
    }

    /**
     * @return list folded left with object e in function f
     */
    static <U,V> V foldLeft(V e, Iterable<U> l, BiFunction<V,U,V> f){
        List<U> tail;
        U head;
        if(l != null){ // not null
            tail = tail(l);
            head = head(l);
        } else { // if null just return e
            return e;
        }

        if (size(l) == 0)  return e;
        else{
            return foldLeft(f.apply(e, head), tail, f);
        }
    }

    /**
     * @return list folded right with object e in function f
     */
    static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
        List<U> tail;
        U head;
        if(l != null){ // not null
            tail = tail(l);
            head = head(l);
        } else { // if null just return e
            return e;
        }

        if (size(l) == 0)  return e;
        else{
            return f.apply(head, foldRight(e, tail, f));
        }
    }
    /**
     * @return list l with a product removed using Predicate p
     */
    static <U> List<U> filter(List<U> l, Predicate<U> p){
        for (Iterator<U> iterator = l.iterator(); iterator.hasNext(); ) {
            U value = iterator.next();
            if (p.test(value)) {
                iterator.remove();
            }
        }

        return l;
    }

    static <U> U binFoldLeft(U e, List<U>l, BiFunction<U,U,U> f){
        U result = e;
        int size = l.size();
        Iterator<U> iterator = l.iterator();
        if (l != null) {
            if (iterator.hasNext())
                f.apply(binFoldLeft(e, (l.subList(0, size / 2)), f),    binFoldLeft(e, (l.subList((size / 2 + 1), size)), f));
            else
                return e;
        }

        return e;
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