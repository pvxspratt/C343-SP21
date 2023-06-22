import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class S {

    /**
     Given a generic input list xs, this method is supposed to return a *flat* list with all of the items in
     the input tripled (repeated 3 times).
     ----
     *Def*: A flat list is a simple, non-nested list. A [Listof [Listof E]] is not a flat list. A [Listof E]
     is a flat list.
     ----
     Ex.
     Given: [1, 2, 3]
     Expected: [1, 1, 1, 2, 2, 2, 3, 3, 3]
     Refer to STest.java for more examples.
     **/
    public static <E> List<E> triplicate (List<E> xs) {
        // TODO
        return xs.stream().flatMap(x -> Stream.of(x,x,x)).collect(Collectors.toList());
    }

    /**
     Returns a list with all integers in the input list-but with one catch: each integer is squared.
     ----
     Ex.
     Given: [1, 2, 3]
     Expected: [1, 4, 9]
     Refer to STest.java for more examples.
     **/
    public static List<Integer> square (List<Integer> xs) {
        // TODO
        //return xs.stream().forEach((n) -> Math.pow(n, 2));
        //return xs.stream().flatMap(n -> Math.pow(n, 2)).collect(Collectors.toList());
        return xs.stream().map(n -> n * n).collect(Collectors.toList());
    }

    /**
     Returns true if all integers in the input list are even-else, false.
     ----
     Ex.
     Given: [1, 2, 4]
     Expected: False
     Refer to STest.java for more examples.
     **/
    public static boolean allEven (List<Integer> xs) {
        return xs.stream().allMatch(n -> n % 2 == 0);
    }

    /**
     Returns a list with all of the odd numbers in the input list omitted (filtered out).
     ----
     Ex.
     Given: [1, 2, 3, 4]
     Expected: [2, 4]
     Refer to STest.java for more examples.
     **/
    public static List<Integer> evens (List<Integer> xs) {
        // TODO
        return xs.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        //return null;
    }

    /**
     Returns the value of multiplying every integer in the given list together.
     ----
     Ex.
     Given: [4, 2, 0]
     Expected: 0
     Refer to STest.java for more examples.
     **/
    public static int mul (List<Integer> xs) {
        return xs.stream().reduce(1, (r, n) -> (r * n));
    }

    /**
     Returns the value of adding every integer in the given list together, mod 10.
     ----
     Ex.
     Given: [10, 20, 30]
     Expected: 0
     Refer to STest.java for more examples.

     reduce
     **/
    public static int checksum (List<Integer> xs) {
        return xs.stream().reduce(0, (r,n) -> (r + n) % 10);
    }

    /**
     Returns the total length of strings.
     ----
     Ex.
     Given: ["ABC", "c", "de"]
     Expected: 6
     Refer to STest.java for more examples.
     **/
    public static int lengths (List<String> xs) {
        // TODO
        return xs.stream().reduce("", String::concat).length();
        //xs.stream().reduce("", (x,y) -> x + y).length();
        //xs.stream().reduce(0, (x,y) -> x + y.length(), (a,b) -> a+b);
    }
    /**
     Returns the string that has the greatest value as its first character (not case sensitive). Checks this
     lexicographically.
     ----
     Ex.
     Given: ["MNBY", "xabd", "XYZ", "ET", "tqfyus"]
     Expected: "XYZ"
     The strings do not meant anything. I just keyboard smashed.
     **/
    public static String largest (List<String> xs) {
        Comparator<String> c = String::compareToIgnoreCase;
        return xs.stream().sorted(c.reversed()).findFirst().get();
    }
}