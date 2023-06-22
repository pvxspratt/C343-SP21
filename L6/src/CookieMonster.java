import java.util.Arrays;

public class CookieMonster {
    private int[][] cookies, cache;
    private int size;

    CookieMonster (int[][] cookies, int size) {
        this.cookies = cookies;
        this.cache = new int[size][size];
        this.size = size;
    }

    // Given two ints (that represent row and column indices), this method will return
    // the greatest int between the values at (r+1, c), (r+1, c-1), and (r+1, c+1).
    int maxBottom3 (int r, int c) {
        // TODO
        int maxwell = 0;
        int st = 0;
        int le = 0;
        int rg = 0;

        st = cookies[r + 1][c];
        //le = cookies[r + 1][c - 1];
        //rg = cookies[r + 1][c + 1];

        if(c == 0) {
            le = 0;
        } else {
            le = cookies[r + 1][c - 1];
        }

        if(c == cookies.length) {
            rg = 0;
        } else {
            rg = cookies[r + 1][c + 1];
        }

        if(st > le && st > rg) {
            maxwell = st;
        } else if(le > st && le > rg) {
            maxwell = le;
        } else {
            maxwell = rg;
        }

        return maxwell;
        //return 0;
    }

    // Using a bottom-up (tabular) dynamic programming approach, this method returns the "max amount of
    // cookies".
    // Specifics:
    //      - Your search area will be an NxN matrix. (this.cookies)
    //      - Start from the last row of your matrix and build up.
    //      - Do not change matrix cookies; instead, use this.cache as your tabulation playground.
    //      - Your answer will be in the the first row, middle column (N/2).
    // As always, please read Lab 6's canvas post for more information (in particular, how to build) ^.^
    int findBest () {
        // TODO
        for(int i = 0; i < cookies.length - 1; i++) {
            for(int j = 0; j < cookies[0].length - 1; j++) {
                int maxine = maxBottom3(i , j);
                this.cache[i][j] = maxine;
            }
        }


        System.out.println(Arrays.deepToString(cache).replace("], ", "]\n"));
        return this.cache[0][cookies.length / 2];







        // this will help you debug - will print out the tabulation of your algo :)
        //System.out.println(Arrays.deepToString(cache).replace("], ", "]\n"));
        //return 0;
    }
}
