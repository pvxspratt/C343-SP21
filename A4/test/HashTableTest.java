import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test
<<<<<<< HEAD
    public void myLinear() throws NotFoundE {
        HashTable<Integer, String> hash = new HashLinearProbing<>();
        hash.insert(0, "red");
        hash.insert(1, "orange");
        hash.insert(2, "yellow");
        hash.insert(3, "green");
        hash.insert(4, "blue");
        hash.insert(5, "violet");
        assertEquals(6, hash.getSize());
        hash.insert(9, "black");

        assertTrue(hash.getDeleted().isEmpty());
        assertEquals(7, hash.getSize());
        assertEquals(17, hash.getCapacity());

        ArrayList<Optional<Map.Entry<Integer, String>>> mySlots = hash.getSlots();
        //System.out.println(hash.toString());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"red")), mySlots.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(1,"orange")), mySlots.get(1));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(2,"yellow")), mySlots.get(2));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(3,"green")), mySlots.get(3));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(4,"blue")), mySlots.get(4));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(5,"violet")), mySlots.get(5));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(9,"black")), mySlots.get(9));
        assertEquals(Optional.empty(), mySlots.get(7));
        assertEquals(Optional.empty(), mySlots.get(6));

        hash.delete(3);
        hash.delete(5);

        assertEquals(5, hash.getSize());
        assertEquals(2, hash.getDeleted().size());

        HashTable<Integer, Integer> birth = new HashLinearProbing<>();
        birth.insert(0, 1216);
        birth.insert(4, 62);
        birth.insert(6, 725);
        birth.insert(5, 314);

        assertTrue(birth.getDeleted().isEmpty());
        assertEquals(4, birth.getSize());
        assertEquals(17, birth.getCapacity());

    }

    @Test
=======
>>>>>>> ee970f5a66e04b8650bdcaee96caae10170314d0
    public void simpleLinear () {
        HashTable<Integer,String> ht = new HashLinearProbing<>();
        ht.insert(0,"lamb");
        ht.insert(1,"cat");
        ht.insert(2,"dog");
        ht.insert(3,"horse");
        ht.insert(4,"cow");
        ht.insert(5,"chicken");
        ht.insert(6,"monkey");

        assertEquals(7, ht.getSize());
        assertTrue(ht.getDeleted().isEmpty());

        ArrayList<Optional<Map.Entry<Integer,String>>> slots = ht.getSlots();
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"lamb")),slots.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(1,"cat")),slots.get(1));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(2,"dog")),slots.get(2));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(3,"horse")),slots.get(3));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(4,"cow")),slots.get(4));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(5,"chicken")),slots.get(5));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(6,"monkey")),slots.get(6));
    }

    @Test
<<<<<<< HEAD
    public void myQuadriceps() {
        HashTable<Integer, String> hash = new HashQuadProbing<>();
        hash.insert(0, "red");
        hash.insert(1, "orange");
        hash.insert(2, "yellow");
        hash.insert(3, "green");
        hash.insert(4, "blue");
        hash.insert(5, "violet");
        assertEquals(6, hash.getSize());
        hash.insert(9, "black");

        assertTrue(hash.getDeleted().isEmpty());
        assertEquals(7, hash.getSize());
        assertEquals(17, hash.getCapacity());

        ArrayList<Optional<Map.Entry<Integer, String>>> mySlots = hash.getSlots();
        //System.out.println(mySlots.toString());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"red")), mySlots.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(1,"orange")), mySlots.get(1));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(2,"yellow")), mySlots.get(2));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(3,"green")), mySlots.get(3));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(4,"blue")), mySlots.get(4));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(5,"violet")), mySlots.get(5));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(9,"black")), mySlots.get(9));
        assertEquals(Optional.empty(), mySlots.get(7));

        HashTable<Integer, Integer> birth = new HashQuadProbing<>();
        birth.insert(0, 1216);
        birth.insert(4, 62);
        birth.insert(6, 725);
        birth.insert(5, 314);

        assertTrue(birth.getDeleted().isEmpty());
        assertEquals(4, birth.getSize());
        assertEquals(17, birth.getCapacity());
    }

    @Test
=======
>>>>>>> ee970f5a66e04b8650bdcaee96caae10170314d0
    public void simpleQuad () {
        HashTable<Integer,String> ht = new HashQuadProbing<>();
        ht.insert(0,"lamb");
        ht.insert(1,"cat");
        ht.insert(2,"dog");
        ht.insert(3,"horse");
        ht.insert(4,"cow");
        ht.insert(5,"chicken");
        ht.insert(6,"monkey");

        assertEquals(7, ht.getSize());
        assertTrue(ht.getDeleted().isEmpty());

        ArrayList<Optional<Map.Entry<Integer,String>>> slots = ht.getSlots();
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"lamb")),slots.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(1,"cat")),slots.get(1));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(2,"dog")),slots.get(2));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(3,"horse")),slots.get(3));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(4,"cow")),slots.get(4));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(5,"chicken")),slots.get(5));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(6,"monkey")),slots.get(6));
    }


    @Test
    public void clashQuad () {
        HashTable<Integer,String> ht = new HashQuadProbing<>();
        ht.insert(0,"lamb");
        ht.insert(17,"cat");
        ht.insert(34,"dog");
        ht.insert(51,"horse");
        ht.insert(68,"cow");
        ht.insert(8, "fox");
        ht.insert(85,"tiger");

        assertEquals(7, ht.getSize());
        assertTrue(ht.getDeleted().isEmpty());

        ArrayList<Optional<Map.Entry<Integer,String>>> slots = ht.getSlots();
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"lamb")),slots.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(17,"cat")),slots.get(1));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(34,"dog")),slots.get(4));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(51,"horse")),slots.get(9));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(8,"fox")),slots.get(8));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(85,"tiger")),slots.get(2));
    }

    @Test
    public void clashLinear () throws NotFoundE {
        HashTable<Integer,String> ht = new HashLinearProbing<>();

        assertThrows(NotFoundE.class, () -> ht.search(1000));

        ht.insert(0,"lamb");
        ht.insert(7,"cat");
        ht.insert(14,"dog");
        ht.insert(21,"horse");
        ht.insert(35,"chicken");
        ht.insert(42,"monkey");
        ht.insert(24,"cow");

        assertEquals("lamb", ht.search(0));
        assertEquals("cat", ht.search(7));
        assertEquals("dog", ht.search(14));
        assertEquals("horse", ht.search(21));
        assertEquals("chicken", ht.search(35));
        assertEquals("monkey", ht.search(42));
        assertEquals("cow", ht.search(24));

        assertThrows(NotFoundE.class, () -> ht.search(1000));

        ht.insert(49, "lion");

        ArrayList<Optional<Map.Entry<Integer,String>>> slots = ht.getSlots();
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"lamb")),slots.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(35,"chicken")),slots.get(1));
        assertTrue(slots.get(2).isEmpty());
        assertTrue(slots.get(3).isEmpty());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(21,"horse")),slots.get(4));
        assertTrue(slots.get(5).isEmpty());
        assertTrue(slots.get(6).isEmpty());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(7,"cat")),slots.get(7));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(42,"monkey")),slots.get(8));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(24,"cow")),slots.get(9));
        assertTrue(slots.get(10).isEmpty());
        assertTrue(slots.get(11).isEmpty());
        assertTrue(slots.get(12).isEmpty());
        assertTrue(slots.get(13).isEmpty());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(14,"dog")),slots.get(14));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(49,"lion")),slots.get(15));
        assertTrue(slots.get(16).isEmpty());
    }

    @Test
    public void deletes () throws NotFoundE {
        HashTable<Integer,String> ht = new HashLinearProbing<>();

        ht.insert(1,"cat");
        ht.insert(18,"dog");
        ht.insert(35,"horse");
        ht.insert(52,"cow");
        ht.insert(69,"chicken");
        ht.insert(86,"lion");
        ht.insert(103,"tiger");
        ht.insert(120,"cheetah");

        assertThrows(NotFoundE.class, () -> ht.delete(1000));
        ht.delete(18);
        ht.delete(69);

        assertThrows(NotFoundE.class, () -> ht.search(18));
        assertThrows(NotFoundE.class, () -> ht.search(69));
        assertTrue(ht.getDeleted().contains(2));
        assertTrue(ht.getDeleted().contains(5));
        assertTrue(ht.getSlots().get(5).isEmpty());
        assertTrue(ht.getSlots().get(2).isEmpty());

        assertEquals("cat", ht.search(1));
        assertEquals("horse", ht.search(35));
        assertEquals("cow", ht.search(52));
        assertEquals("lion", ht.search(86));
        assertEquals("tiger", ht.search(103));
        assertEquals("cheetah", ht.search(120));

        ht.insert(18, "fox");
        assertEquals(ht.getSlots().get(2).orElseThrow(NotFoundE::new).getValue(), "fox");
    }

    // TODO
    // your own test cases here
<<<<<<< HEAD

    @Test
    public void boop() throws NotFoundE {
        HashTable<Integer, String> hashish = new HashLinearProbing<>();
        hashish.insert(0, "red");
        hashish.insert(1, "orange");
        hashish.insert(2, "yellow");
        hashish.insert(3, "green");
        hashish.insert(4, "blue");
        hashish.insert(5, "violet");
        assertEquals(6, hashish.getSize());
        hashish.insert(12, "grey");
        hashish.insert(26, "pink");
        hashish.insert(44, "brown");

        ArrayList<Optional<Map.Entry<Integer, String>>> sluts = hashish.getSlots();
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(0,"red")), sluts.get(0));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(1,"orange")), sluts.get(1));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(2,"yellow")), sluts.get(2));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(3,"green")), sluts.get(3));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(4,"blue")), sluts.get(4));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(5,"violet")), sluts.get(5));
        assertTrue(sluts.get(6).isEmpty());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(44, "brown")), sluts.get(7));
        assertTrue(sluts.get(8).isEmpty());
        assertTrue(sluts.get(9).isEmpty());
        assertTrue(sluts.get(10).isEmpty());
        assertTrue(sluts.get(11).isEmpty());
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(12, "grey")), sluts.get(12));
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(26, "pink")), sluts.get(26));
        System.out.println(sluts);
        hashish.delete(12);
        hashish.delete(3);

        assertTrue(sluts.get(12).isEmpty());
        assertTrue(sluts.get(3).isEmpty());

        assertThrows(NotFoundE.class, () -> hashish.delete(12));
        assertThrows(NotFoundE.class, () -> hashish.delete(3));

        hashish.insert(40, "black");
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(40, "black")), sluts.get(3));
        hashish.insert(80, "cyan");
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(80, "cyan")), sluts.get(6));
        hashish.insert(37, "aqua");
        assertEquals(Optional.of(new AbstractMap.SimpleImmutableEntry<>(37, "aqua")), sluts.get(8));

        assertEquals("red", hashish.search(0));
        assertEquals("orange", hashish.search(1));
        assertEquals("yellow", hashish.search(2));
        assertThrows(NotFoundE.class, () -> hashish.search(3));
        assertEquals("black", hashish.search(40));
        assertEquals("blue", hashish.search(4));
        assertEquals("violet", hashish.search(5));
        assertEquals("cyan", hashish.search(80));
        assertEquals("brown", hashish.search(44));
        assertEquals("aqua", hashish.search(37));
        assertThrows(NotFoundE.class, () -> hashish.search(9));


    }
=======
>>>>>>> ee970f5a66e04b8650bdcaee96caae10170314d0
}
