import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DequeueArrayTest {

    @Test
    public void noReTest() throws NoSuchElementE {
        DequeueArray<Integer> l = new DequeueArrayDouble<>(6);
        assertEquals(0, l.size());
        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);
        assertEquals(3, l.size());
        assertEquals(0, l.getBack());
        assertEquals(2, l.getFront());
        assertEquals(3, l.getFirst());
        assertEquals(1, l.getLast());

        assertEquals(3, l.removeFirst());
        assertEquals(3, l.getFront());
        assertEquals(0, l.getBack());

        assertEquals(1, l.removeLast());
        assertEquals(5, l.getBack());

        assertEquals(2, l.removeFirst());
        assertEquals(4, l.getFront());

        assertEquals(5, l.getBack());
        assertEquals(4, l.getFront());

        assertEquals(0, l.size());

        // _ _ _ _ _ _
        l.addLast(4); // back 0     B _ _ _ F 4
        assertEquals(0, l.getBack());
        l.addLast(6); // back 1     6 B _ _ F 4
        assertEquals(1, l.getBack());
        l.addFirst(12); // fro 3    6 B _ F 12 4
            assertEquals(3, l.getFront());
        l.addFirst(26); // fro 2    6 B F 26 12 4
            assertEquals(2, l.getFront());
        l.addLast(44); // ba 2      6 44 BF 26 12 4
        assertEquals(2, l.getBack());
        l.addFirst(31); // fr 1     6 F44 B31 26 12 4
            assertEquals(1, l.getFront());

        assertEquals(1, l.getFront());
        assertEquals(2, l.getBack());
        assertEquals(31, l.getFirst());
        assertEquals(44, l.getLast());
    }

    @Test
    public void dequeDoubleNoResize() throws NoSuchElementE {
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        assertEquals(0, d.size());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, d.removeFirst());
        assertEquals(1, d.removeLast());
        assertEquals(2, d.removeLast());
        assertEquals(0, d.size());
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, d.removeFirst());
        assertEquals(3, d.removeFirst());
        assertEquals(1, d.removeFirst());
        assertEquals(2, d.removeFirst());
        assertEquals(4, d.removeFirst());
    }

    @Test
    public void deuceT() throws NoSuchElementE {
        DequeueArray<Integer> l = new DequeueArrayDouble<>(6);
        l.addLast(4);
        l.addLast(6);
        l.addFirst(12);
        l.addFirst(26);
        l.addLast(44);
        l.addFirst(31);
        System.out.println(l.getFront());
        System.out.println(l.getBack());

        l.addLast(14);
        System.out.println(Arrays.toString(l.getElements()));
        assertEquals(12, l.getCapacity());
        assertEquals(14, l.getLast());
    }

    @Test
    public void p5() throws NoSuchElementE {
        DequeueArray<Integer> l = new DequeueArrayOneAndHalf<>(6);
        l.addLast(4);
        l.addLast(6);
        l.addFirst(12);
        l.addFirst(26);
        l.addLast(44);
        l.addFirst(31);
        System.out.println(l.getFront());
        System.out.println(l.getBack());

        l.addLast(14);
        System.out.println(Arrays.toString(l.getElements()));
        assertEquals(9, l.getCapacity());
        assertEquals(14, l.getLast());
    }

    @Test
    public void bringAGuest() throws NoSuchElementE {
        DequeueArray<Integer> l = new DequeueArrayPlusOne<>(6);
        l.addLast(4);
        l.addLast(6);
        l.addFirst(12);
        l.addFirst(26);
        l.addLast(44);
        l.addFirst(31);
        System.out.println(l.getFront());
        System.out.println(l.getBack());

        l.addLast(14);
        System.out.println(Arrays.toString(l.getElements()));
        assertEquals(7, l.getCapacity());
        assertEquals(14, l.getLast());
    }

    @Test
    public void dequeDoubleResize() throws NoSuchElementE {
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        d.addFirst(6);
        assertEquals(10, d.getCapacity());
        assertEquals(6, d.removeFirst());
        assertEquals(5, d.removeFirst());
        assertEquals(3, d.removeFirst());
        assertEquals(1, d.removeFirst());
        assertEquals(2, d.removeFirst());
        assertEquals(4, d.removeFirst());
    }

    Duration timeDequeue (Random r, int size, int bound, DequeueArray<Integer> dq) throws NoSuchElementE {
        Instant start = Instant.now();

        for (int i=0; i<1000; i++) {
            dq.addFirst(r.nextInt(bound));
        }

        for (int i=0; i<size; i++) {
            int e = r.nextInt(bound);
            switch (r.nextInt(4)) {
                case 0 -> dq.addFirst(e);
                case 1 -> dq.addLast(e);
                case 2 -> dq.removeFirst();
                case 3 -> dq.removeLast();
                default -> throw new Error("Impossible");
            }
        }

        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeDQ() throws NoSuchElementE {
        Random r = new Random(5000);
        int size = 100000;
        int bound = 10000;

        Duration d1 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d1.toMillis());

        Duration d2 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d2.toMillis());

        Duration d3 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d3.toMillis());

        System.out.println();
        r = new Random(50);
        size = 1000;
        bound = 100;

        d1 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d1.toMillis());

        d2 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d2.toMillis());

        d3 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d3.toMillis());

        System.out.println();
        r = new Random(50000);
        size = 1000000;
        bound = 100000;

        d1 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d1.toMillis());

        d2 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d2.toMillis());

        /*
        d3 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d3.toMillis());

         */
    }

    @Test
    void timeDeQueues () throws NoSuchElementE {
        Random r = new Random(500);
        int size = 10000;
        int bound = 1000;

        Duration d1 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d1.toMillis());

        Duration d2 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d2.toMillis());

        Duration d3 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d3.toMillis());
    }

}

