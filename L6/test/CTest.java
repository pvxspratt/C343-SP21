import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CTest {

    @Test
    void CMTest() {
        int[][] cookies = {
                { 9, 4, 1, 2, 5},
                { 7, 2, 6, 4, 2},
                { 3, 8, 2, 2, 1},
                { 5, 2, 9, 3, 5},
                { 4, 9, 5, 9, 7},
        };

        int m = new CookieMonster(cookies,5).findBest();

        System.out.printf("m=%d%n", m);

        // TODO more correctness tests
        int[][] taco = {
                { 7, 2, 3, 9, 5},
                { 8, 4, 1, 4, 2},
                { 3, 1, 2, 3, 1},
                { 7, 2, 9, 1, 5},
                { 2, 2, 8, 9, 4},
        };

        int f = new CookieMonster(taco,5).findBest();

        System.out.printf("m=%d%n", f);


        int[][] pie = {
                { 5, 8, 2, 6, 2},
                { 3, 6, 3, 7, 9},
                { 2, 6, 3, 6, 8},
                { 4, 6, 4, 3, 2},
                { 9, 7, 5, 8, 4},
        };

        int n = new CookieMonster(pie,5).findBest();

        System.out.printf("m=%d%n", n);
    }
}