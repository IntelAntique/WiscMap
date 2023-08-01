import java.util.List;

public interface PathfinderInterface {
    public DataReaderInterface storedData = null;
    public void loadFile (String filename);
    public List<String> findNodesFromPath(String startNode, String endNode);
    public Double findTimeFromPath(String startNode, String endNode);
}
