import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Integer;

class PEmptyE extends Exception {}

abstract class PList {
    abstract int getElem () throws PEmptyE;
    abstract PList getRest () throws PEmptyE;
    abstract int length ();

    /**
     * Splits the current list in two at the given
     * index
     */
    abstract Pair<PList,PList> splitAt (int index);

    /**
     * Keep dividing the list in two until you reach
     * a base case; then merge the sorted lists
     * resulting from the recursive calls
     */
    abstract PList mergeSort ();

    /**
     * The given list 'ns' is sorted; the current
     * list (this) is also sorted. Return a new
     * sorted list from these two lists.
     */
    abstract PList merge (PList ns);

    static List<Integer> toList (PList ns) {
        List<Integer> result = new ArrayList<>();
        while (true) {
            try {
                result.add(ns.getElem());
                ns = ns.getRest();
            } catch (PEmptyE e) {
                return result;
            }
        }
    }

    static PList fromList (List<Integer> ns) {
        PList result = new PEmpty();
        for (int i=0; i<ns.size(); i++) {
            result = new PNode(ns.get(i), result);
        }
        return result;
    }

    static String toString(Pair<PList, PList> poop) {
        List<Integer> premier = toList(poop.getFst());
        List<Integer> seconde = toList(poop.getSnd());
        return "Pair<A,B> : " + toList(poop.getFst()) + toList(poop.getSnd()) + "\n" +
        "first: " + premier + "  ||  second: " + seconde;
    }

    static String toString(PList piss) {
        List<Integer> sac = toList(piss);
        //Collections.reverse(sac);
        return "list: " + sac.toString();
    }
}

class PEmpty extends PList {
    int getElem() throws PEmptyE {
        throw new PEmptyE();
    }

    PList getRest() throws PEmptyE {
        throw new PEmptyE();
    }

    int length() {
        return 0;
    }

    Pair<PList, PList> splitAt(int index) {
        return new Pair<>(new PEmpty(), new PEmpty());
        //return null; // TODO
    }

    PList mergeSort() {
        return new PEmpty();
        //return null; // TODO
    }

    PList merge(PList ns) {
        return ns;
        //return null; // TODO
    }
}


class PNode extends PList {
    private final int elem;
    private final PList rest;
    private final int len;

    PNode(int elem, PList rest) {
        this.elem = elem;
        this.rest = rest;
        this.len = rest.length() + 1;
    }

    int getElem() {
        return elem;
    }

    PList getRest() {
        return rest;
    }

    int length() {
        return len;
    }

    Pair<PList, PList> splitAt(int index) {
        if(index == 0){
            return new Pair<>(new PEmpty(), this);
        }else{
            Pair<PList, PList> taco = rest.splitAt(index-1);
            return new Pair<>(new PNode(elem, taco.getFst()), taco.getSnd());
        }
        //return null; // TODO
    }

    PList mergeSort(){
        if(len == 1){
            return this;
        }
        Pair<PList, PList> pear = this.splitAt(this.length() / 2);
        return pear.getFst().mergeSort().merge(pear.getSnd().mergeSort());
        //return null; // TODO
    }

    PList merge(PList ns){
        try{
            if(this.getElem() < ns.getElem()){
                return new PNode(this.getElem(), this.rest.merge(ns));
            }
            else{
                return new PNode(ns.getElem(), this.merge(ns.getRest()));
            }
        }catch(PEmptyE e){
            return this;
        }
    }
}

