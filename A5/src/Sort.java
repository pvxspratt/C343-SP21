import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All sorting algorithms must return a NEW sorted list. Do not
 * modify the incoming list
 */
public class Sort {
    static List<Integer> streamSort(List<Integer> ns) {
        return ns.stream().sorted().collect(Collectors.toList());
    }

    static List<Integer> insertionSort (List<Integer> ns) {
        // TODO (from lab)
        ArrayList<Integer> iSort = new ArrayList<Integer>();
        for(int l = 0; l < ns.size(); l++) {
            iSort.add(l, ns.get(l));
        }

        for(int i = 1; i < iSort.size(); i++) {
            int curElem = iSort.get(i);
            int j = i - 1;

            while((j >= 0) && ((iSort.get(j).compareTo(curElem)) == 1)) {
                iSort.set(j + 1, iSort.get(j));
                j--;
            }

            iSort.set(j + 1, curElem);
        }

        return iSort;
    }

    static List<Integer> mergeSort (List<Integer> ns) {
        // real work done in PList
        return PList.toList(PList.fromList(ns).mergeSort());
    }

    static int increment(int n) {
        // From https://oeis.org/search?q=shell+sort
        // a(n) = 9*2^n - 9*2^(n/2) + 1 if n is even;
        // a(n) = 8*2^n - 6*2^((n+1)/2) + 1 if n is odd.
        if (n % 2 == 0)
            return (int) (9 * Math.pow(2, n) - 9 * Math.pow(2, n / 2) + 1);
        else
            return (int) (8 * Math.pow(2,n) - 6 * Math.pow(2,(n + 1) / 2) + 1);
    }

    /**
     * Steps:
     * 1. create an array incSequence that calls increment above until the
     *    gap is more than half of the size of the array
     * 2. Start from the largest gap and iterate down the list of gaps
     * 3. For each gap, do an insertion sort for the elements separated
     *    by the given gap
     */
    static List<Integer> shellSort (List<Integer> ns) {
        List<Integer> copy = new ArrayList<>(ns);
        ArrayList<Integer> incSequence = new ArrayList<>();
        for(int l = 0; l < ns.size(); l++) {
            incSequence.add(increment(l));
            if(increment(l) > ns.size()/2){
                break;
            }
        }

        for(int turtle = incSequence.size()-1; turtle >= 0; turtle-= 1) {
            int gap = incSequence.get(turtle);
            for(int i = gap; i < copy.size(); i+=1) {
                int curElem = copy.get(i);
                int pos = i;
                while(pos-gap >= 0 && curElem < copy.get(pos-gap)){
                    copy.set(pos, copy.get(pos-gap));
                    pos = pos - gap;
                }
                copy.set(pos, curElem);
            }
        }

        return copy;
    }

    static int getDigit (int n, int d) {
        if (d == 0) return n % 10;
        else return getDigit (n / 10, d-1);
    }

    /**
     * Steps:
     * 1. Create 10 buckets represented as ArrayLists, one for each digit
     * 2. For each digit d (d=0 to len-1) with 0 the least significant digit,
     *    do the following
     * 3. Take a number from the input list, look at digit 'd' in that number,
     *    and add it to the bucket 'd'
     * 4. When you finish processing the list, copy the contents of the
     *    buckets into a temporary list
     * 5. Clear the buckets and repeat for the next 'd'
     */
    // len is the max number of digits in each number in thte list
    static List<Integer> radixSort (List<Integer> ns, int len) {

        ArrayList<ArrayList<Integer>> bucks = new ArrayList<>(10);
        ArrayList<Integer> temp = new ArrayList<>(ns);
        List<Integer> stage = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            bucks.add(i, new ArrayList<>());
        }

        for(int d = 0; d < len; d++) {
            for(int n:temp){
                ArrayList<Integer> bucket = bucks.get(getDigit(n, d));
                bucket.add(n);
            }

            int resIndex = 0;
            for(ArrayList<Integer> bucket:bucks){
                for(int n:bucket){
                    stage.add(resIndex++, n);
                }
                bucket.clear();
            }
            temp = new ArrayList<>(stage);
            stage.clear();
        }

        return temp;
    }

}
