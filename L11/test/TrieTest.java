import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    public void poz() {
        Tile[][] tit = new Tile[3][3];
        tit[0][0] = new Tile('d', 0, 0);
        tit[0][1] = new Tile('o', 0, 1);
        tit[0][2] = new Tile('g', 0, 2);
        tit[1][0] = new Tile('u', 1, 0);
        tit[1][1] = new Tile('l', 1, 1);
        tit[1][2] = new Tile('g', 1, 2);
        tit[2][0] = new Tile('p', 2, 0);
        tit[2][1] = new Tile('i', 2, 1);
        tit[2][2] = new Tile('e', 2, 2);

        /*
            d o g
            u l g
            p i e
         */

        Trie trye = new Trie();
        trye.insert("dog");
        trye.insert("egg");
        trye.insert("eip");
        System.out.println(trye.toString());

        Board bored = new Board(tit, trye);
        HashSet<String> res = bored.findWordsFromPos(0, 0, "");
        System.out.println(res.toString());
    }

    @Test
    public void insert() {
        // TODO test insert
        Trie toot = new Trie();
        toot.insert("pickle");
        toot.insert("slut");
        toot.insert("laura");
        toot.insert("fox");
        toot.insert("cake");
        toot.insert("fouet");
        toot.insert("rari");
    }

    @Test
    public void contains() throws IOException {
        Trie trie = new Trie();
        Scanner scanner = new Scanner(Paths.get("commonwords.txt"));
        while (scanner.hasNext()) {
            trie.insert(scanner.next());
        }

        assertFalse(trie.contains("aarons"));
        assertTrue(trie.contains("aaron"));
        assertFalse(trie.contains("aaro"));
        assertTrue(trie.contains("absent"));
    }

    @Test
    public void possiblePrefix() throws IOException {
        Trie trie = new Trie();
        Scanner scanner = new Scanner(Paths.get("commonwords.txt"));
        while (scanner.hasNext()) {
            trie.insert(scanner.next());
        }

        assertFalse(trie.possiblePrefix("aarons"));
        assertTrue(trie.possiblePrefix("aaron"));
        assertTrue(trie.possiblePrefix("aaro"));
        assertTrue(trie.possiblePrefix("absent"));
    }

    @Test
    public void trie () {
        Trie t = new Trie();
        t.insert("on");
        t.insert("one");
        t.insert("of");
        t.insert("off");
        t.insert("oft");
        t.insert("or");

        System.out.println(t.getChildren());
    }

    @Test
    public void dict () throws IOException {
        Trie trie = new Trie();
        Scanner scanner = new Scanner(Paths.get("commonwords.txt"));
        while (scanner.hasNext()) {
            trie.insert(scanner.next());
        }

        assertTrue(trie.contains("abandon"));
        assertTrue(trie.contains("abandoned"));
        assertTrue(trie.contains("abandonment"));
        assertFalse(trie.contains("abandonmenth"));
        assertFalse(trie.contains("abando"));
        assertFalse(trie.contains("aband"));
        assertFalse(trie.contains("aban"));
        assertFalse(trie.contains("aba"));
        assertFalse(trie.contains("ab"));
        assertFalse(trie.contains("a"));
        assertTrue(trie.possiblePrefix("abando"));
        assertTrue(trie.possiblePrefix("aband"));
        assertTrue(trie.possiblePrefix("aban"));
        assertTrue(trie.possiblePrefix("aba"));
        assertTrue(trie.possiblePrefix("ab"));
        assertTrue(trie.possiblePrefix("a"));
    }
}
