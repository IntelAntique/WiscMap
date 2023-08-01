import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlgorithmEngineerTests {
    //instance used in junit testers
    protected Pathfinder _instance = null;
    
    @BeforeEach
    public void initializeMap() {
        _instance = new Pathfinder();
        _instance.loadFile("test");
    }
    @Test
    //testing a path for errors
    public void testPath1() {
      //System.out.println(_instance.storedData.storedMap);
      List<String> testList = _instance.findNodesFromPath(_instance.storedData.storedMap.get(0).locationName,_instance.storedData.storedMap.get(3).locationName);
      //System.out.println(testList.get(2).locationName);
      assertEquals(testList.get(0),"M");
      assertEquals(testList.get(1),"W");
    }
    //testing an alternate path for errors
    @Test
    public void testPath2() {
      List<String> testList = _instance.findNodesFromPath(_instance.storedData.storedMap.get(4).locationName,_instance.storedData.storedMap.get(0).locationName);
      assertEquals(testList.get(0),"F");
      assertEquals(testList.get(1),"M1");
      assertEquals(testList.get(2),"M");
    }
    //testing an empty path
    @Test
    public void testImpossiblePath() {
      List<String> testList = _instance.findNodesFromPath(_instance.storedData.storedMap.get(4).locationName,"Some inaccessible island");
      assertEquals(testList.size(),0);
    }
    //testing the length of a path
    @Test
    public void testLength() {
      assertEquals(_instance.findTimeFromPath(_instance.storedData.storedMap.get(3).locationName,_instance.storedData.storedMap.get(1).locationName),7.0);
    }
    //testing the length of an empty path
    @Test
    public void testImpossibleLength() {
      assertEquals(_instance.findTimeFromPath(_instance.storedData.storedMap.get(0).locationName,_instance.storedData.storedMap.get(0).locationName),0.0);
    }
    
    //tests my AE code with BD to see if the implementation of the pathfinder works
    @Test
    public void testIntegrationAEBD() {
      Mapper testMP = new Mapper();
      testMP.Pf.loadFile("");
      testMP.setCurrentPosition("F");
      assertEquals(testMP.findNearestplaceDist(), 7.0);
    }
    //tests my AE code with FD to see if their code which uses the BD to access the pathfinder works
    @Test
    public void testIntegrationAEFD() {
      Mapper testMP = new Mapper();
      Scanner sc = new Scanner(System.in);
      TextUITester testUITester = new TextUITester("f\nm\nn");
      CHWiscMapFrontend wiscMap = new CHWiscMapFrontend(sc,testMP);
      wiscMap.runCommandLoop();
      String result = wiscMap.getBD().getCurrentPosition();
      assertEquals("f", result);
    }
    
    //tests if backend's shortestPathTime returns the correct value
    @Test
    void testBackend() {
      Mapper testMP = new Mapper();
      testMP.StoredMap();
      testMP.setCurrentPosition("F");
      double result = testMP.shortestPathTime("W");
      assertEquals(result, 14.0);
    }
    //tests if frontend's method of returning the shortest path time returns the correct value
    @Test
    void testFrontend() {
      MapperFD testMP = new MapperFD();
      Scanner sc = new Scanner(System.in);
      TextUITester testUITester = new TextUITester("m\nf\nn");
      CHWiscMapFrontend wiscMap = new CHWiscMapFrontend(sc,testMP);
      wiscMap.runCommandLoop();
      assertEquals(wiscMap.getTime(), 15.0);
    }
}