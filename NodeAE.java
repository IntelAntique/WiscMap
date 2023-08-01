import java.util.ArrayList;
import java.util.List;

public class NodeAE implements NodeInterface{
    public String locationName = "";
    public String locationType = "";
    public List<String> neighborNodeNames = new ArrayList<String>();
    public List<Double> neighborNodeDistances = new ArrayList<Double>();
    public NodeAE (String locationName, String locationType) {
      this.locationName = locationName;
      this.locationType = locationType;
    }
}
