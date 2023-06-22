import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BSTTest {
    BST one, two, left;
    Empty e = new Empty();

    // TODO does it work ?
    @Test
    public void delete () throws EmptyBSTE {
        one = new BSTNode(6, new BSTNode(4, e, e), new BSTNode(26, e, e));
        two = new BSTNode(6, new BSTNode(4, e, e), new BSTNode(26, new BSTNode(12, e, e), e));
        left = new BSTNode(6, e, new BSTNode(12, e, e));

        int del = one.getValue();
        BST small = one.delete(del);
        int lef = one.getRightTree().deleteLeftMostChild().getFirst();
        assertEquals(lef, (int)small.getValue());

        one = one.insert(1);
        Pair<Integer, BST> pear = one.deleteLeftMostChild();
        assertEquals(1, (int)pear.getFirst());
    }
}
