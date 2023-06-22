import java.util.ArrayList;
import java.util.Arrays;

// Bottom-Up Dynamic Programming Practice
public class DP {

    // helper to convert our List class to an ArrayList
    // OR you can use our List class's .get() method
    // converting would be more efficient... can you see why :)
    static <E> ArrayList<E> toArray (List<E> ls) {
        ArrayList<E> converted = new ArrayList<>();
        try {
            converted.add(ls.getFirst());
            converted.addAll(toArray(ls.getRest()));
            return converted;
        } catch (EmptyListE e) {
            return converted;
        }
    }

    // -----------------------------------------------------------------------------
    // Partition ...
    // -----------------------------------------------------------------------------

    /**
     * Partition: take a list, a desired sum 's', and return
     * T/F depending on whether it is possible to find a
     * subsequence of the list whose sum is exactly 's'
     */
    static boolean partition (List<Integer> s, int sum) {
        try {
            return partition(s.getRest(), sum - s.getFirst()) ||
                    partition(s.getRest(), sum);
        }
        catch (EmptyListE e) {
            return sum == 0;
        }
    }

    // hint: use the toArray() method above to convert objects of our List class to an ArrayList
    // do bottom-up approach to partition
    static boolean bupartition (List<Integer> s, int sum) {
        // TODO
        ArrayList<Integer> ss = toArray(s);

        int len = s.length() + 1;
        Boolean[][] table = new Boolean[len][sum + 1];

        for(int i = 0; i < sum + 1; i++) {
            table[len - 1][i] = false;
        }

        for(int c = 0; c < len; c++) {
            table[c][0] = true;
        }

        for(int c = len - 2; c >= 0; c--) {
            int v = ss.get(c);
            for(int i = 1; i < sum + 1; i++) {
                if(i - v < 0) {
                    table[c][i] = table[c + 1][i];
                } else {
                    table[c][i] = (i - v <= i) && table[c + 1][i] || table[c + 1][i - v];
                }
            }
        }

        return table[0][sum];
        //return false;
    }


    // -----------------------------------------------------------------------------
    // Min distance ...
    // -----------------------------------------------------------------------------

    final static int GAP = 2;
    final static int MATCH = 0;
    final static int MISMATCH = 1;

    enum BASE { A, T, G, C }

    /**
     * We want to align two DNA sequences; the idea is to compare
     * the first entry in the first sequence with the first entry in
     * the second sequence:
     * - if they match, then great, move on
     * - if they do not match, we can try to repair this in one of
     *   three possible ways
     *   - pay a penalty of 1 and move on,
     *   - pay a penalty of 2 to use a wild card in the first sequence
     *     and move on
     *   - pay a penalty of 2 to use a wild card in the second sequence
     *     and move on
     * Our goal is to find the best way to align the sequences (with
     * the least penalty)
     */
    static int minDistance (List<BASE> dna1, List<BASE> dna2) {
        try {
            int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
            int d1 = current + minDistance(dna1.getRest(), dna2.getRest());
            int d2 = GAP + minDistance(dna1.getRest(), dna2);
            int d3 = GAP + minDistance(dna1, dna2.getRest());
            return Math.min(d1,Math.min(d2,d3));
        }
        catch (EmptyListE e) {
            if (dna1.isEmpty()) return GAP * dna2.length();
            else return GAP * dna1.length();
        }
    }


    // feel free to refer to Kev's video (I embedded it again to this week's lab post on Canvas)
    static int buminDistance (List<BASE> dna1, List<BASE> dna2) {
        // TODO
        ArrayList<BASE> strand1 = toArray(dna1);
        ArrayList<BASE> strand2 = toArray(dna2);

        int lD1 = dna1.length() + 1;
        int lD2 = dna2.length() + 1;

        int[][] table = new int[lD1][lD2];

        try {
            for(int i = 0; i < lD2; i++) {
                table[0][i] = GAP * i;
            }
            for(int c = 0; c < lD1; c++) {
                table[c][0] = GAP * c; // c * GAP
            }

            List<BASE> temp1 = dna1;
            for(int c = 1; c < lD1; c++) {
                BASE g1 = temp1.getFirst();
                temp1 = temp1.getRest();

                List<BASE> temp2 = dna2;
                for(int i = 1; i < lD2; i++) {
                    BASE g2 = temp2.getFirst();
                    temp2 = temp2.getRest();

                    int diagonal = 0;
                    if(g2.equals(g1)) {
                        diagonal = table[c - 1][i - 1] + MATCH;
                    } else {
                        diagonal = table[c - 1][i - 1] + MISMATCH;
                    }

                    int left = table[c - 1][i] + 2;
                    int right = table[c][i - 1] + 2;

                    table[c][i] = Math.min(Math.min(left, right), diagonal);
                }
            }
            return table[dna1.length()][dna2.length()];
        } catch (EmptyListE e) {
            return 0;
        }

        //return 0;
    }
}
