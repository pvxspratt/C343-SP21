import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void queues () throws EmptyQueueE {
        Queue<Integer> q1 = new SlowQueue<>();
        for (int i=0; i<10; i++) {
            q1.enqueue(i);
        }

        for (int i=0; i<10; i++) {
            assertEquals(i, q1.getFront());
            q1.dequeue();
        }

        Queue<Integer> q2 = new AmortizedQueue<>();
        for (int i=0; i<10; i++) {
            q2.enqueue(i);
        }

        for (int i=0; i<10; i++) {
            assertEquals(i, q2.getFront());
            q2.dequeue();
        }
    }

    Duration timeQueue (Random r, int size, int bound, Queue<Integer> q) throws EmptyQueueE {

        Instant start = Instant.now();

        for (int i=0; i<100; i++) {
            q.enqueue(r.nextInt(bound));
        }

        for (int i=0; i<size; i++) {
            if (r.nextBoolean()) {
                int e = r.nextInt(bound);
                q.enqueue(e);
            }
            else {
                q.dequeue();
            }
        }

        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeQueues () throws EmptyQueueE {
        Random r = new Random(100);
        int size = 10000;
        int bound = 1000;

        Duration d1 = timeQueue(r, size, bound, new SlowQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on slow queue; time in ms = %d%n",
                size, d1.toMillis());

        Duration d2 = timeQueue(r, size, bound, new AmortizedQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on amortized queue; time in ms = %d%n",
                size, d2.toMillis());


    }

    @Test
    void tQueues() throws EmptyQueueE {
        Random r = new Random(100);
        int sizeS = 100;
        int bound = 100;

        Duration d1 = timeQueue(r, sizeS, bound, new SlowQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on slow queue; time in ms = %d%n",
                sizeS, d1.toMillis());

        Duration d2 = timeQueue(r, sizeS, bound, new AmortizedQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on amortized queue; time in ms = %d%n",
                sizeS, d2.toMillis());

        int sizeM = 1000;
        Duration d3 = timeQueue(r, sizeM, bound, new SlowQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on slow queue; time in ms = %d%n",
                sizeM, d3.toMillis());

        Duration d4 = timeQueue(r, sizeM, bound, new AmortizedQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on amortized queue; time in ms = %d%n",
                sizeM, d4.toMillis());

        int sizeL = 100000;
        /*
        Duration d5 = timeQueue(r, sizeL, boundL, new SlowQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on slow queue; time in ms = %d%n",
                sizeL, d5.toMillis());
         */

        Duration d6 = timeQueue(r, sizeL, bound, new AmortizedQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on amortized queue; time in ms = %d%n",
                sizeL, d6.toMillis());

    }

}