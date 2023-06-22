import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    // TODO does it work ?
    @Test
    public void sort () {
        Tree tree = new Empty();
        tree = tree.insert(5);
        tree = tree.insert(9);
        tree = tree.insert(6);
        tree = tree.insert(11);
        tree = tree.insert(5);
        tree = tree.insert(4);
        tree = tree.insert(2);
        tree = tree.insert(7);
        tree = tree.insert(2);
        TreePrinter.print(tree);

        List<Integer> tee = Sort.inOrder(tree);
        Sort.sort(tee);
        System.out.println(tee);

        Tree twee = new Empty();
        twee = twee.insert(1);
        twee = twee.insert(3);
        twee = twee.insert(3);
        twee = twee.insert(2);
        TreePrinter.print(twee);
        List<Integer> tea = Sort.inOrder(twee);
        Sort.sort(tea);
        System.out.println(tea);


    }
}
