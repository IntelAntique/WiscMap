import java.util.ArrayList;
import java.util.List;

public class DataReaderAE implements DataReaderInterface{
    public List<Node> storedMap = new ArrayList<Node>();
    public void readData (String filename) {
      //Hashtable<NodeType,Node> rHash = new Hashtable<NodeType,Node>();
      //This is a placeholder, the list of nodes will be pulled correctly in the DW implementation.
      Node node1 = new Node("M","");
      node1.neighborNodeNames.add("M1");
      node1.neighborNodeDistances.add(5.0);
      node1.neighborNodeNames.add("G");
      node1.neighborNodeDistances.add(4.0);
      node1.neighborNodeNames.add("W");
      node1.neighborNodeDistances.add(7.0);
      Node node2 = new Node("M1","");
      node2.neighborNodeNames.add("F");
      node2.neighborNodeDistances.add(7.0);
      node2.neighborNodeNames.add("G");
      node2.neighborNodeDistances.add(2.0);
      node2.neighborNodeNames.add("M");
      node2.neighborNodeDistances.add(5.0);
      Node node3 = new Node("G","");
      node3.neighborNodeNames.add("M");
      node3.neighborNodeDistances.add(4.0);
      node3.neighborNodeNames.add("M1");
      node3.neighborNodeDistances.add(2.0);
      node3.neighborNodeNames.add("W");
      node3.neighborNodeDistances.add(5.0);
      Node node4 = new Node("W","");
      node4.neighborNodeNames.add("G");
      node4.neighborNodeDistances.add(5.0);
      node4.neighborNodeNames.add("M");
      node4.neighborNodeDistances.add(7.0);
      Node node5 = new Node("F","");
      node5.neighborNodeNames.add("M1");
      node5.neighborNodeDistances.add(7.0);
      storedMap.add(node1);
      storedMap.add(node2);
      storedMap.add(node3);
      storedMap.add(node4);
      storedMap.add(node5);
      
      //System.out.println(storedMap);
    }
    public DataReaderAE() {
      storedMap = new ArrayList<Node>();
    }
}