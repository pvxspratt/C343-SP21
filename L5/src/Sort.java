import java.util.ArrayList;
import java.util.List;

public class Sort {

    // This method should sort the input list using the algorithm for insertion sort.
    // That is, first create a new ArrayList with all of the elements from input ns.
    // Then, iterate through this new ArrayList - comparing a current element to its
    // predecessor. While current is less, it is swapped w predecessor.

    // For those who prefer wordier instructions, check out Lab 5 post on canvas :)
    // Otherwise, best of luck on the lab! Tests/debugging will help a lot with IndexOutOfBoundsExceptions
    static List<Integer> insertionSort (List<Integer> ns) {
        // TODO
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
}
