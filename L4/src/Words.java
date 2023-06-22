import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class Word {
    private String w;

    Word (String w) { this.w = w; }

    /**
     * this functionally reduces our hash structure (in our case, HashSet) to a list (due to hashing at the same spot)
     * after your tests are created, modify this hash function to something of your choice
     * see how much you can improve your timing :)
     * (YOU MAY NOT USE BUILT-IN hashCode)
     */
    // TODO after testing
<<<<<<< HEAD
    public int hashCode () {
        char[] wC = new char[this.w.length()];

        int count = 0;

        for(int i = 0; i < this.w.length(); i++) {
            wC[i] = this.w.charAt(i);
            count = count + (int) wC[i];
        }

        return count;
        // return 7;
    }
=======
    public int hashCode () { return 7; }
>>>>>>> ee970f5a66e04b8650bdcaee96caae10170314d0
}

class Words {
    static String filename = "commonwords.txt";
    // from http://www.mieliestronk.com/corncob_lowercase.txt

    static List<String> slist;
    static List<Word> wlist;

    static {
        try {
            // note that objects in our slist will have the default hashCode; whereas wlist is a list of objects
            // from our Word class that has our own hashCode method implemented
            slist = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            wlist = slist.stream().map(Word::new).collect(Collectors.toList());

        } catch (IOException e) {
            throw new Error();
        }
    }
}
