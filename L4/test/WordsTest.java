import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;

class WordsTest {

    @Test
    void tests() {
        int la = (int) 'a';
        System.out.println(la);

        Word w = new Word("a");
        assertEquals(la, w.hashCode());

        int h = (int) 'h' + (int) 'o' + (int) 't' + (int) 'd' + (int) 'o' + (int) 'g';
        Word g = new Word("hotdog");
        assertEquals(h, g.hashCode());

    }


=======
class WordsTest {

>>>>>>> ee970f5a66e04b8650bdcaee96caae10170314d0
    // write your own tests timing how long it takes to build a hashset with a Listof String and then a Listof Word
    // refer to Lab 3 tests if you do not remember how to time tests using methods of the Instant & Duration class
    @Test
    void timeW () {
        // TODO
<<<<<<< HEAD
        Instant start = Instant.now();

        List<String> l = Words.slist;
        HashSet<String> h = new HashSet<>(l);

        Instant end = Instant.now();
        Duration d1 = Duration.between(start, end);
        System.out.printf("Running %d operations; time in ms = %d%n", 100, d1.toMillis());

        Instant s = Instant.now();

        List<Word> l2 = Words.wlist;
        HashSet<Word> h2 = new HashSet<>(l2);

        Instant e = Instant.now();
        Duration d2 = Duration.between(start, end);
        System.out.printf("Running %d operations; time in ms = %d%n", 100, d2.toMillis());


=======
>>>>>>> ee970f5a66e04b8650bdcaee96caae10170314d0
    }

}
