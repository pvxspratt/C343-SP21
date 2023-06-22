import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    public void basic () {
        Tree t1, t2, t3, t4, t5, t6;
        t1 = new Node(5, new Empty(), new Empty());
        t2 = new Node(30, new Empty(), new Empty());
        t3 = new Node(2, t1, t2);
        t4 = new Node(38, t3.map(k -> k+1), t3.mirror().insert(100));
        t5 = Tree.fromArray(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        t6 = new Node(0, new Node(-1, new Empty(), new Empty()), t5);

        TreePrinter.print(t1);
        TreePrinter.print(t2);
        TreePrinter.print(t3);
        TreePrinter.print(t4);
        TreePrinter.print(t5);
        TreePrinter.print(t6);

        assertEquals(4, t4.height());
        assertEquals(2, t4.numberMaxPaths());
        assertEquals(16, t5.numberMaxPaths());
        assertEquals(7, t4.diameter());
        assertEquals(8, t5.diameter());
        assertEquals(8,t6.diameter());
        assertEquals(215, t4.reduce(0, (a,b,c) -> a+b+c));
        assertEquals(170, t4.maxSum());
    }

    @Test
    public void myTrees() {
        Empty e = new Empty();
        Tree eTree = new Empty();
        Tree tree = new Node(0,
                new Node(1, new Node(3, e, e), new Node(4, e, e)),
                new Node(2, new Node(5, e, e), new Node(6, e, e)));
        Tree t1 = new Node(5, new Empty(), new Empty());
        Tree t2 = new Node(30, new Empty(), new Empty());
        Tree t3 = new Node(2, t1, t2);
        Tree Mt3 = new Node(2, t2, t1);

        Tree three = new Node(1,
                new Node(2,
                        new Node(4, new Node(8, e, e), new Node(9, e, e)),
                        new Node(5, new Node(10, e, e), new Node(11, e, e))),
                new Node(3,
                        new Node(6, new Node(12, e, e), new Node(13, e, e)),
                        new Node(7, new Node(14, e, e), new Node(15, e, e))));

        assertEquals(0, eTree.height());
        assertEquals(3, tree.height());
        assertEquals(1, t1.height());
        assertEquals(1, t2.height());
        assertEquals(2, t3.height());
        assertEquals(4, three.height());
        assertEquals(8, three.diameter());
        assertEquals(26, three.maxSum());


        TreePrinter.print(tree);
        TreePrinter.print(tree.mirror());
        TreePrinter.print(t1);
        TreePrinter.print(t2);
        TreePrinter.print(t3);
        TreePrinter.print(t3.mirror());
        TreePrinter.print(three);
        System.out.println(three.numberMaxPaths());

        //assertEquals(Mt3, t3.mirror());

        Tree twee = new Node(6, new Node(4, e, e), new Node(26, e, e));
        Tree inTwee = new Node(6, new Node(4, e, e), new Node(26, new Node(12, e, e), e));
        TreePrinter.print(twee);
        TreePrinter.print(inTwee);
        TreePrinter.print(twee.insert(12));
        //assertEquals(inTwee, twee.insert(12));

        Tree tweePlus1 = new Node(7, new Node(5, e, e), new Node(27, e, e));
        TreePrinter.print(twee.map(n -> n+1));
        //assertEquals(tweePlus1, twee.map(n -> n + 1));






    }

    @Test
    public void eyeball() {
        Empty e = new Empty();
        Tree tree = new Node(0,
                new Node(1, new Node(3, e, e), new Node(4, e, e)),
                new Node(2, new Node(5, e, e), new Node(6, e, e)));

        System.out.println();
        System.out.println(Tree.DFSRec(tree));
        System.out.println();
        System.out.println(Tree.DFSIter(tree));
        System.out.println();

        // DFS
        int[] d = {0, 2, 6, 5, 1, 4, 3};
        ArrayList<Integer> DFS = new ArrayList<>();
        for (Integer i : d) { DFS.add(i); }
        System.out.println(DFS);

        // BF level order
        int[] l = {0, 1, 2, 3, 4, 5, 6};
        ArrayList<Integer> level = new ArrayList<>();
        for (Integer i : l) { level.add(i); }
        System.out.println(level);

        // BF pre order
        int[] pr = {0, 1, 3, 4, 2, 5, 6};
        ArrayList<Integer> pre = new ArrayList<>();
        for (Integer i : pr) { pre.add(i); }
        System.out.println(pre);

        // BF in order
        int[] in = {3, 1, 4, 0, 5, 2, 6};
        ArrayList<Integer> inO = new ArrayList<>();
        for (Integer i : in) { inO.add(i); }
        System.out.println(inO);

        // BF post order
        int[] po = {3, 4, 1, 5, 6, 2, 0};
        ArrayList<Integer> post = new ArrayList<>();
        for (Integer i : po) { post.add(i); }
        System.out.println(post);


        assertEquals(DFS, Tree.DFSRec(tree));
        assertEquals(DFS, Tree.DFSIter(tree));

        assertEquals(level, Tree.BFS(tree));

        System.out.println(Tree.BFS(tree));



    }
}
