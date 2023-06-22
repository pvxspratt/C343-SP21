import java.util.*;
import java.util.function.Consumer;

abstract class GraphTraversal {
    protected Hashtable<Node, ArrayList<Edge>> neighbors;
    protected NodeCollection nodesToTraverse;


    GraphTraversal(Hashtable<Node, ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
    }

    void traverse(Consumer<Node> consumer) {
        while (!nodesToTraverse.isEmpty())
            visit(nodesToTraverse.extract(), consumer);
    }

    void visit(Node u, Consumer<Node> consumer) {
        if (!u.isVisited()) {
            u.setVisited();
            consumer.accept(u);
            neighbors.get(u).forEach(this::relax);
        }
    }

    abstract void relax(Edge e);
}

class StronglyConnected extends GraphTraversal {

    StronglyConnected(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        nodesToTraverse = new STACK_COLL();
    }

    Hashtable<Node, ArrayList<Edge>> transpose() {
        // TODO
<<<<<<< HEAD
        Hashtable<Node, ArrayList<Edge>> otherNeighbors = new Hashtable<>();
        for(Node n : neighbors.keySet()) {
            otherNeighbors.put(n, new ArrayList<>());
        }

        for(ArrayList<Edge> ed : neighbors.values()) {
            ed.forEach(e -> {
                Edge newEd = e.flip();
                otherNeighbors.get(newEd.getSource()).add(newEd);
            });
        }

        return otherNeighbors;
        //return null;
=======
>>>>>>> 015ef32f5febe4047ac6f4d5b45a64033d96d7d9
    }


    void relax(Edge e) {
        nodesToTraverse.insert(e.getDestination());
    }

    List<Node> DFS(Node n) {
        // TODO
<<<<<<< HEAD
        List<Node> turn = new ArrayList<>();
        nodesToTraverse.insert(n);
        traverse(turn::add);
        return turn;
=======
>>>>>>> 015ef32f5febe4047ac6f4d5b45a64033d96d7d9
    }

    Stack<Node> orderOfDFS() {
        Stack<Node> dfsStack = new Stack<>();
        Set<Node> nodes = neighbors.keySet();
        nodes.forEach(Node::reset);

        for (Node n : nodes) {
            if (!n.isVisited()) {
                orderOfDFSHelper(n, dfsStack);
            }
        }

        return dfsStack;
    }

    void orderOfDFSHelper(Node n, Stack<Node> dfsStack) {
        n.setVisited();

        for (Edge outgoingEdge : neighbors.get(n)) {
            Node destination = outgoingEdge.getDestination();
            if (!destination.isVisited()) {
                orderOfDFSHelper(destination, dfsStack);
            }
        }

        dfsStack.push(n);
    }

    List<List<Node>> findSCCs() {
        // TODO
    }
}
