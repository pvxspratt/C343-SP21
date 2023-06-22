import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTest {

    @Test
    void inc () {
        List<Integer> expected = Arrays.asList(1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929,
                16001, 36289, 64769, 146305, 260609, 587521, 1045505, 2354689, 4188161);
        for (int i=0; i<20; i++) assertEquals(expected.get(i), Sort.increment(i));
    }

    @Test
    void digit () {
        assertEquals(7, Sort.getDigit(1347,0));
        assertEquals(4, Sort.getDigit(1347,1));
        assertEquals(3, Sort.getDigit(1347,2));
        assertEquals(1, Sort.getDigit(1347,3));
    }

    @Test
    void tros() {
        List<Integer> slop = Arrays.asList(906, 708, 312, 847, 870, 630, 773, 424, 224);
            List<Integer> slob1 = slop.subList(0, 4);
            List<Integer> slob2 = slop.subList(4, slop.size());
        List<Integer> sout = Arrays.asList(224, 312, 424, 630, 708, 773, 847, 870, 906);

        assertEquals(sout, Sort.streamSort(slop));
        assertEquals(sout, Sort.insertionSort(slop));

        PList slopP = PList.fromList(slop);
        PList fir = PList.fromList(slob1);
        PList sec = PList.fromList(slob2);
        Pair<PList, PList> slug = new Pair<>(fir, sec);

        System.out.println(PList.toString(slopP.splitAt(4)));
        System.out.println(slopP.splitAt(4));
        System.out.println(PList.toString(slopP));
        System.out.println();
        System.out.println(slob1.toString());
        System.out.println(slob2.toString());
        System.out.println(PList.toString(slug.getFst()));
        System.out.println(PList.toString(slug.getSnd()));

        //assertEquals(slug, slopP.splitAt(4));


        assertEquals(sout, Sort.shellSort(slop));

    }

    @Test
    void sort () throws PEmptyE {
        List<Integer> ns = Arrays.asList(70,100,203,784,412,135,101);
        List<Integer> sorted = Arrays.asList(70, 100, 101, 135, 203, 412, 784);

        assertEquals(sorted, Sort.streamSort(ns));
        assertEquals(sorted, Sort.insertionSort(ns));
        assertEquals(sorted, Sort.mergeSort(ns));
        assertEquals(sorted, Sort.shellSort(ns));
        assertEquals(sorted, Sort.radixSort(ns,3));
    }

    Duration timeM (Function<List<Integer>,List<Integer>> f, List<Integer> ns) {
        Instant start = Instant.now();
        f.apply(ns);
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeSort () {
        Random r = new Random(500);
        int size = 10000;
        int bound = 1000;

        List<Integer> ns =
                r.ints(size, 0,bound).
                        boxed().
                        collect(Collectors.toList());

        long d;

        d = timeM(Sort::streamSort, ns).toMillis();
        System.out.printf("Java sort takes %d ms%n", d);

        d = timeM(Sort::insertionSort, ns).toMillis();
        System.out.printf("Insertion sort takes %d ms%n", d);

        //d = timeM(Sort::mergeSort, ns).toMillis();
        //System.out.printf("Merge sort takes %d ms%n", d);

        d = timeM(Sort::shellSort, ns).toMillis();
        System.out.printf("Shell sort takes %d ms%n", d);

        d = timeM(nums -> Sort.radixSort(nums, 3), ns).toMillis();
        System.out.printf("Radix sort takes %d ms%n", d);
    }

}