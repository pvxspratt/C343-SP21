import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

class SortTest {

    @Test
    void sort () {
        // TODO more correctness tests
        List<Integer> ns = Arrays.asList(70,100,203,784,412,135,101);

        System.out.printf("Original list = %s%n", ns);
        System.out.printf("insertionSort = %s%n%n", Sort.insertionSort(ns));

        // MY TESTs
        List<Integer> bingus = Arrays.asList(4, 26, 12, 44, 31, 426, 222);
        System.out.printf("Original bingus = %s%n", bingus);
        System.out.printf("insertionSort = %s%n%n", Sort.insertionSort(bingus));

        List<Integer> bongus = Arrays.asList(4, 14, 7, 25, 12, 16, -3, 3, -5, 3);
        System.out.printf("Original bongus = %s%n", bongus);
        System.out.printf("insertionSort = %s%n%n", Sort.insertionSort(bongus));
    }

    @Test
    void timeSort () {
        Instant start = Instant.now();
        // TODO efficiency tests
        // In particular, include a test that demonstrates why insertion sort's worst-case
        // performance is O(n^2)
        List<Integer> orange = Arrays.asList(8, 3, 7, 2, 5, 6, 1, 0, 9, 4);
        Sort.insertionSort(orange);

        Instant finish = Instant.now();
        Duration time = Duration.between(start, finish);
        System.out.println("orange time: " + time.toNanos());

        Instant begin = Instant.now();
        // TODO efficiency tests
        // In particular, include a test that demonstrates why insertion sort's worst-case
        // performance is O(n^2)
        List<Integer> sage = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        Sort.insertionSort(sage);

        Instant end = Instant.now();
        Duration period = Duration.between(begin, end);
        System.out.println("sage time: " + period.toNanos());
    }

}
