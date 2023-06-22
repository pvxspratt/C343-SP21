import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    @Test
    void moreLists() throws EmptyListE {
        // testing integer List
        List<Integer> intL =
                new NodeL<>(0,
                        new NodeL<>(1,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(4,
                                                        new NodeL<>(5,
                                                                new EmptyL<>()))))));

        List<Integer> intLRest =
                new NodeL<>(1,
                        new NodeL<>(2,
                                new NodeL<>(3,
                                        new NodeL<>(4,
                                                new NodeL<>(5,
                                                        new EmptyL<>())))));

        assertEquals(0, intL.getFirst());
        assertEquals(intLRest, intL.getRest());
        assertEquals(6, intL.length());
        assertEquals(5, intLRest.length());
        assertEquals(true, intL.inList(0));
        assertEquals(true, intL.inList(3));
        assertEquals(false, intL.inList(26));

        List<Integer> intLInsert =
                new NodeL<>(0,
                        new NodeL<>(1,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(12,
                                                        new NodeL<>(4,
                                                                new NodeL<>(5,
                                                                        new EmptyL<>())))))));

        List<Integer> intL2 =
                new NodeL<>(0,
                        new NodeL<>(3,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(4,
                                                        new NodeL<>(5,
                                                                new EmptyL<>()))))));

        List<Integer> intLInsert2 =
                new NodeL<>(0,
                        new NodeL<>(3,
                                new NodeL<>(26,
                                        new NodeL<>(2,
                                                new NodeL<>(3,
                                                        new NodeL<>(26,
                                                                new NodeL<>(4,
                                                                        new NodeL<>(5,
                                                                                new EmptyL<>()))))))));

        List<Integer> intLRemoveF =
                new NodeL<>(0,
                        new NodeL<>(3,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(26,
                                                        new NodeL<>(4,
                                                                new NodeL<>(5,
                                                                        new EmptyL<>())))))));

        assertEquals(intLInsert, intL.insertAfter(3, 12));
        assertEquals(intLInsert2, intL2.insertAfter(3, 26));
        assertEquals(intLRest, intL.removeFirst(0));
        assertEquals(intL, intLInsert.removeFirst(12));
        assertEquals(intLRemoveF, intLInsert2.removeFirst(26)); // check that it only removes the first one, not all
        assertEquals(2, intL.indexOf(2));
        assertEquals(1, intLRest.indexOf(2));

        // filter tests
        List<Integer> filT =
                new NodeL<>(-3,
                        new NodeL<>(-2,
                                new NodeL<>(-1,
                                        // new NodeL<>(0,
                                                new NodeL<>(1,
                                                        new NodeL<>(2,
                                                                new NodeL<>(3,
                                                                        new EmptyL<>()))))));

        List<Integer> filTNeg =
                new NodeL<>(-3,
                        new NodeL<>(-2,
                                new NodeL<>(-1,
                                        new EmptyL<>())));

        List<Integer> filTPos =
                new NodeL<>(1,
                        new NodeL<>(2,
                                new NodeL<>(3,
                                        new EmptyL<>())));

        assertEquals(filTNeg, filT.filter(n -> n < 0));
        assertEquals(filTPos, filT.filter(n -> n > 0));

        // map tests
        List<Integer> mapT =
                new NodeL<>(1,
                        new NodeL<>(3,
                                new NodeL<>(6,
                                        new EmptyL<>())));

        List<Integer> mapTC =
                new NodeL<>(1,
                        new NodeL<>(27,
                                new NodeL<>(216,
                                        new EmptyL<>())));

        List<Integer> mapTA =
                new NodeL<>(2,
                        new NodeL<>(4,
                                new NodeL<>(7,
                                        new EmptyL<>())));

        List<Integer> mapTR =
                new NodeL<>(1,
                        new NodeL<>(44,
                                new NodeL<>(6,
                                        new EmptyL<>())));

        assertEquals(mapTC, mapT.map(n -> n*n*n));
        assertEquals(mapTA, mapT.map(n -> n + 1));
        assertEquals(mapTR, mapT.map(n -> n == 3 ? 44 : n));

        // reduce tests
        assertEquals(10, mapT.reduce(0, Integer::sum));
        assertEquals(6, mapT.reduce(0, Integer::max));
        assertEquals(18, mapT.reduce(1, (r, n) -> (r * n)));

        // append and reverse
        List<Integer> app =
                new NodeL<>(1,
                        new NodeL<>(3,
                                new NodeL<>(6,
                                        new NodeL<>(2,
                                                new NodeL<>(4,
                                                        new NodeL<>(7,
                                                                new EmptyL<>()))))));

        List<Integer> rev =
                new NodeL<>(7,
                        new NodeL<>(4,
                                new NodeL<>(2,
                                        new NodeL<>(6,
                                                new NodeL<>(3,
                                                        new NodeL<>(1,
                                                                new EmptyL<>()))))));

        assertEquals(app, mapT.append(mapTA));
        assertEquals(rev, (mapTA.reverse()).append(mapT.reverse()));

        assertEquals(app, rev.reverse());
        assertEquals(filTNeg, (filTPos.map(n -> n*(-1))).reverse());

        assertEquals(filT, ((filTPos.map(n -> n*(-1))).reverse()).append((filTNeg.map(n -> n*(-1))).reverse()));
        assertEquals(intL.reverse(), List.countdown(5));

        // power set -- 2, 4, 7
        List<List<Integer>> ps = mapTA.powerSet();
        List<List<Integer>> ps2 = intL.powerSet();

        assertEquals(8, ps.length());
        assertEquals(64, ps2.length());

        assertTrue(ps.inList(new NodeL<>(2, new NodeL<>(4, new NodeL<>(7, new EmptyL<>())))));
        assertTrue(ps.inList(new NodeL<>(2, new NodeL<>(4, new EmptyL<>()))));
        assertTrue(ps.inList(new NodeL<>(2, new NodeL<>(7, new EmptyL<>()))));
        assertTrue(ps.inList(new NodeL<>(4, new NodeL<>(7, new EmptyL<>()))));
        assertTrue(ps.inList(new NodeL<>(2, new EmptyL<>())));
        assertTrue(ps.inList(new NodeL<>(4, new EmptyL<>())));
        assertTrue(ps.inList(new NodeL<>(7, new EmptyL<>())));
        assertTrue(ps.inList(new EmptyL<>()));
    }

    @Test
    void lists() throws EmptyListE {
        List<Integer> ints =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(3,
                                        new NodeL<>(2,
                                                new NodeL<>(1,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));
        assertEquals(ints, List.countdown(5));

        List<Integer> ints100 =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(3,
                                        new NodeL<>(100,
                                                new NodeL<>(2,
                                                        new NodeL<>(1,
                                                                new NodeL<>(0,
                                                                        new EmptyL<>())))))));

        assertEquals(ints100, ints.insertAfter(3, 100));

        assertEquals(ints, ints100.removeFirst(100));

        assertEquals(3, ints.indexOf(2));

        List<Integer> intsEvens =
                new NodeL<>(4,
                        new NodeL<>(2,
                                new NodeL<>(0,
                                        new EmptyL<>())));

        List<Integer> intsOdds =
                new NodeL<>(5,
                        new NodeL<>(3,
                                new NodeL<>(1,
                                        new EmptyL<>())));

        assertEquals(intsEvens, ints.filter(n -> n%2==0));
        assertEquals(intsOdds, ints.filter(n -> n%2==1));

        List<Integer> intsSquared =
                new NodeL<>(25,
                        new NodeL<>(16,
                                new NodeL<>(9,
                                        new NodeL<>(4,
                                                new NodeL<>(1,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));

        assertEquals(intsSquared, ints.map(n -> n*n));

        assertEquals(15, ints.reduce(0, Integer::sum));

        List<Integer> intsEvensOdds =
                new NodeL<>(5,
                        new NodeL<>(3,
                                new NodeL<>(1,
                                        new NodeL<>(4,
                                                new NodeL<>(2,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));

        assertEquals(intsEvensOdds, intsOdds.append(intsEvens));

        List<Integer> intsRev =
                new NodeL<>(0,
                        new NodeL<>(1,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(4,
                                                        new NodeL<>(5,
                                                                new EmptyL<>()))))));

        assertEquals(intsRev, ints.reverse());

        List<List<Integer>> evensPS = intsEvens.powerSet();

        assertEquals(8, evensPS.length());
        assertTrue(evensPS.inList(new EmptyL<>()));
        assertTrue(evensPS.inList(new NodeL<>(0, new EmptyL<>())));
        assertTrue(evensPS.inList(new NodeL<>(2, new EmptyL<>())));
        assertTrue(evensPS.inList(new NodeL<>(4, new EmptyL<>())));
        assertTrue(evensPS.inList(new NodeL<>(4, new NodeL<>(2, new EmptyL<>()))));
        assertTrue(evensPS.inList(new NodeL<>(4, new NodeL<>(0, new EmptyL<>()))));
        assertTrue(evensPS.inList(new NodeL<>(2, new NodeL<>(0, new EmptyL<>()))));
        assertTrue(evensPS.inList(new NodeL<>(4, new NodeL<>(2, new NodeL<>(0, new EmptyL<>())))));
    }
}