/**
 * @author Archie_Paredes
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class hw2 {
    /**
     *
     * @param l iterable list
     * @return size of the list
     */
    static <U> int size(Iterable<U> l){ // checks size
        int s = 0;
        for (U item : l)    s++;
        return s;
    }
    /**
     *
     * @param l iterable list
     * @param f Function that passes l
     * @return list mapped to a function
     */

    static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
        List<V> newList = new ArrayList<V>();

        if (size(l) == 0)  return null;
        else {
            for (U item : l)    newList.add(f.apply(item));
        }

        return newList;
    }
    /**
     *
     * @param l iterable list
     * @return List of of l excluding the first product
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
     *
     * @param l iterable list
     * @return The first product of List l
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
     *
     * @param e generic object
     * @param l generic list
     * @param f BiFunction that passes generic object V and U
     * @return generic object V
     */
    static <U,V> V foldLeft(V e, Iterable<U> l, BiFunction<V,U,V> f){
        if (e == null) throw new NullPointerException();
        if (f == null) throw new NullPointerException();
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
     *
     * @param e generic object of V
     * @param l generic list of object U
     * @param f BiFunction that passes generic object V and U
     * @return generic object V
     */
    static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
        if (e == null) throw new NullPointerException();
        if (f == null) throw new NullPointerException();
        List<U> tail;
        U head;
        if(l != null){ // not null
            tail = tail(l);
            head = head(l);
        } else { // if null just return e
            return e;
        }

        if (l.size() == 0)  return e;
        else{
            return f.apply(head, foldRight(e, tail, f));
        }
    }

    /**
     *
     * @param l list of generic object U
     * @param p Predicate function returns boolean
     * @return l filtered out by Predicate p
     */
    static <U> List<U> filter(List<U> l, Predicate<U> p){
        if (p == null) return l;
        if (l == null)  throw new NullPointerException();
        if (l.size() == 0)  return l;

        for (Iterator<U> iterator = l.iterator(); iterator.hasNext(); ) {
            U value = iterator.next();
            if (p.test(value)) {
                iterator.remove();
            }
        }
        return l;
    }

    /**
     *
     * @param e generic object U
     * @param l generic list of object U
     * @param f BiFunction that takes in same object U
     * @return values returned from BiFuntion f
     */
    static <U> U binFoldLeft(U e, List<U>l, BiFunction<U,U,U> f){
        if (e == null) throw new NullPointerException();
        if (f == null) throw new NullPointerException();
        if(l == null || l.size() == 0) return e;
        int sizeL = l.size(); // optimization
        if (sizeL > 1){
            U e2 = e;
            e2 = binFoldLeft(e2, (l.subList(0, sizeL/2)), f);
            e2 = binFoldLeft(e2, (l.subList(sizeL/2, sizeL)),f);
            return e2;
        }
        else{
           return f.apply(e, l.get(0));
        }

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