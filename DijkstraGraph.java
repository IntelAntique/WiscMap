// --== CS400 File Header Information ==--
// Name: Han Yu Foong
// Email: hfoong@wisc.edu
// Group and Team: CH Blue
// Group TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: steep learning curve

import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
    	if(nodes.containsKey(start) == false 
    			|| nodes.containsKey(end) == false) 
    		throw new NoSuchElementException("this node doesn't exist");
    	//do not correspond to the data held in any nodes within the graph
    	
    	PriorityQueue<SearchNode> pq = new PriorityQueue<>();
    	Hashtable<NodeType , SearchNode> hash = new Hashtable<>(); // puts in visited node
    	SearchNode s = new SearchNode(nodes.get(start), 0.0 , null); // initialized start node to be put in pq
    	pq.add(s); //  inserted start node
    	SearchNode NodeTemp = null;
    	
    	while(!(pq.isEmpty())) {// until no node left to visit
    		NodeTemp = pq.poll();
    		if(!(hash.containsValue(NodeTemp))) {// if it is unvisited
    			hash.put(start, NodeTemp); // node is visited
    			if(NodeTemp.node.data.equals(end)) return NodeTemp;
    			for(Edge u: NodeTemp.node.edgesLeaving) {//successor U of C
    				pq.add(new SearchNode(u.successor, NodeTemp.cost + u.data.doubleValue(), NodeTemp));
    			}
    		}
    	}
    	
    	throw new NoSuchElementException("this node can't get there");
    	//there is no directed path that connects from the start node to the end node
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    	List<NodeType> sPD = new ArrayList<NodeType>();
    	SearchNode s = computeShortestPath(start, end);
        while(s != null) {
        	sPD.add(0, s.node.data);
        	s = s.predecessor;
        }
        return sPD;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        return computeShortestPath(start, end).cost;
    }

    // TODO: implement 3+ tests in step 8.
    public static DijkstraGraph<String, Double> DG = null;

    @BeforeEach
    public void create(){
    	DG = new DijkstraGraph<>();
    }
    
    /**
     * test method for accurate destination node from computeShortestPath method
     * 
     * @return true if displays correct destination node 
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test1() {// graph from lecture
    	DG.insertNode("A");
    	DG.insertNode("B");
    	DG.insertNode("C");
    	DG.insertNode("D");
    	DG.insertNode("E");
    	DG.insertNode("F");
    	DG.insertNode("G");
    	DG.insertNode("H");
    	DG.insertEdge("A", "B", 4.0);
    	DG.insertEdge("A", "C", 2.0);
    	DG.insertEdge("C", "D", 5.0);
    	DG.insertEdge("B", "D", 1.0);
    	DG.insertEdge("B", "E", 10.0);
    	DG.insertEdge("B", "E", 15.0);
    	DG.insertEdge("D", "F", 0.0);
    	DG.insertEdge("F", "D", 2.0);
    	DG.insertEdge("F", "H", 4.0);
    	DG.insertEdge("G", "H", 4.0);
    	assertEquals("C" ,
    			DG.computeShortestPath(DG.nodes.get("A").data, DG.nodes.get("C").data).node.data);
    }
    
    /**
     * test method for accurate cost and sequence of data along the shortest path with shortestPathCost
     * 
     * @return true if displays full node of path
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test2() { //graph from lecture
    	DG.insertNode("A");
    	DG.insertNode("B");
    	DG.insertNode("C");
    	DG.insertNode("D");
    	DG.insertNode("E");
    	DG.insertNode("F");
    	DG.insertNode("G");
    	DG.insertNode("H");
    	DG.insertEdge("A", "B", 4.0);
    	DG.insertEdge("A", "C", 2.0);
    	DG.insertEdge("C", "D", 5.0);
    	DG.insertEdge("B", "D", 1.0);
    	DG.insertEdge("B", "E", 10.0);
    	DG.insertEdge("B", "E", 15.0);
    	DG.insertEdge("D", "F", 0.0);
    	DG.insertEdge("F", "D", 2.0);
    	DG.insertEdge("F", "H", 4.0);
    	DG.insertEdge("G", "H", 4.0);
    	assertEquals(4.0 ,
    			DG.shortestPathCost(DG.nodes.get("G").data, DG.nodes.get("H").data));// cost
    	assertEquals("[G, H]" ,
    			DG.shortestPathData(DG.nodes.get("G").data, DG.nodes.get("H").data).toString());// sequence
    }
    
    /**
     * test method for accurate shortest path from shortestPathData method
     * 
     * @return true if displays full node of path
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test3() {
    	DG.insertNode("A");
    	DG.insertNode("B");
    	DG.insertEdge("A", "B", 4.0);
    	assertEquals("[A, B]" ,
    			DG.shortestPathData(DG.nodes.get("A").data, DG.nodes.get("B").data).toString());
    }// check the sequence of data along the shortest path between a different start and end node
    
    /**
     * test method for accurate shortest cost from computeShortestPath method
     * 
     * @return true if displays total cost to shortest path
     * 
     * @author Han Yu Foong
     */
    
    @Test
    public void test4() {
    	DG.insertNode("Home");
    	DG.insertNode("McDonalds");
    	DG.insertNode("McDonalds, but in Milwaukee");
    	DG.insertNode("McDonalds, but in China");
    	DG.insertEdge("Home", "McDonalds", 4.0);
    	DG.insertEdge("McDonalds", "McDonalds, but in Milwaukee", 129.0);
    	DG.insertEdge("McDonalds, but in Milwaukee", "McDonalds, but in China", 8000.0);
    	assertEquals(8133.0, 
    			DG.computeShortestPath("Home", "McDonalds, but in China").cost);
    }// check the cost of data along the shortest path between a different start and end node
    
    /**
     * test method for checking if edges are directed in 1 direction with computeShortestPath method
     * 
     * @return true if displays correct exception message on unable to traverse in opposite facing directed edge
     * 
     * @author Han Yu Foong
     */
    @Test
    public void test5() {
    	DG.insertNode("A");
    	DG.insertNode("B");
    	DG.insertEdge("A", "B", 2.0);
    	try {
	    	assertEquals(2, 
	    			DG.computeShortestPath(DG.nodes.get("B").data, DG.nodes.get("A").data));
    	} catch(Exception e) {
    		assertEquals( "this node can't get there" ,e.getMessage());
    	}
    }// searching for a path between exist in the graph, 
     //but there is no sequence of directed edges that connects them from the start to the end
    
    public static void main(String args[]) {
    	DG.test1();
    	DG.test2();
    	DG.test3();
    	DG.test3();
    	DG.test4();
    }
    
}