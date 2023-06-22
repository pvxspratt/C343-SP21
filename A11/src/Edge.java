public class Edge {
    private final Node source;
    private final Node destination;
    private int weight;

    Edge (Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    Edge (Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    int getWeight () { return weight; }

    Node getSource () { return source; }

    Node getDestination() { return destination; }
}
