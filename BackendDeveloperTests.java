// --== CS400 File Header Information ==--
// Name: Han Yu Foong
// Email: hfoong@wisc.edu
// Group and Team: CH Blue
// Group TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: my team's code is hard to understand

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// The Assertions class that we import from here includes assertion methods like assertEquals()
// which we will used in test1000Inserts().
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * Tester class for Backend Developer
 * 
 * @author Han Yu Foong
 *
 */
public class BackendDeveloperTests {

    public static Mapper MP = null;

    @BeforeEach
    public void BDcreate(){
        MP = new Mapper();
    }
    
    /**
     * test to find the nearest place to visit for fun in campus or to quickly find shelter in a snowstorm
     */
    
    @Test
    public void test1() {
        MP.Pf.loadFile("");
        MP.setCurrentPosition("M");
        assertEquals(MP.findNearestplace(), "G");
//      System.out.println(MP.findNearestplace());
    }
    
    /**
     * test to find the distance to the nearest place
     */
    
    @Test
    public void test2() {
        MP.Pf.loadFile("");
        MP.setCurrentPosition("M");
        assertEquals(MP.findNearestplaceDist(), 4.0);
//      System.out.println(MP.findNearestplaceDist());
    }
    
    /**
     * test to check if the input data is taken in correctly and returns the right output
     */
    
    @Test
    public void test3() {
        MP.Pf.loadFile("");
        MP.setCurrentPosition("M");
        assertEquals(MP.getCurrentPosition(), "M");
//      System.out.println(MP.getCurrentPosition());
    }
    
    /**
     * test to check the places that exists on the map
     */
    
    @Test
    public void test4() {
        MP.Pf.loadFile("");
        MP.setCurrentPosition("M");
        assertEquals(MP.Placelist().toString(), "[M, M1, G, W, F]");
//      System.out.println(MP.Placelist().toString());
    }
    
    /**
     * test to check for the ultimate shortest path
     * 
     * Note: it is calling my place holder that returns the starting node and destination node, which also mean
     * it is the AE's job to return the right output
     */
    
    @Test
    public void test5() {
        MP.Pf.loadFile("");
        MP.setCurrentPosition("M");
        assertEquals(MP.shortestPath("F").toString(), "[M, M1, F]");
//      System.out.println(MP.shortestPath("Chipotle"));
    }  
    /**
     * test to check app's correct output
     */
    
    @Test
    public void Integration1() {
        TextUITester uiTester = new TextUITester("M\nM1\nn");
        WiscMapApp.main(null);
        String SystemOutput = uiTester.checkOutput();
//      System.out.println(SystemOutput);
        assertEquals( true, SystemOutput.contains("[M-->M1]"));
    }
    
    /**
     * method to test if the app returns the right shortest path time
     */
    
    @Test
    public void Integration2() {
        TextUITester uiTester = new TextUITester("M1\nG\nn");
        WiscMapApp.main(null);
        String SystemOutput = uiTester.checkOutput();
//      System.out.println(SystemOutput);
        assertEquals( true, SystemOutput.contains("The shortest route between these two locations will take 2.0 minutes."));
    }
    
    /**
     * method to test if the front end code is executing correctly
     */
    
    @Test
    public void CodeReviewOfFrontEndDeveloper1() {
        TextUITester uiTester = new TextUITester("M\nG\n");
        Scanner s = new Scanner(System.in);
        Mapper m = new Mapper();
        CHWiscMapFrontend frontend = new CHWiscMapFrontend(s,m);
        try {
            frontend.runCommandLoop();
        } catch(Exception e) {
        }
        String SystemOutput = uiTester.checkOutput();
//      System.out.println(SystemOutput);
        assertEquals(true, SystemOutput.contains("Welcome to the WiscMap! ")); // correct starting up of frontend
    }
    
    
    /**
     * 
     */
    
    @Test
    public void CodeReviewOfFrontEndDeveloper2() {
        TextUITester uiTester = new TextUITester("M\n");
        Scanner s = new Scanner(System.in);
        Mapper m = new Mapper();
        CHWiscMapFrontend frontend = new CHWiscMapFrontend(s,m);
        frontend.mainMenuPrompt();
        String SystemOutput = uiTester.checkOutput();
//      System.out.println(SystemOutput);
        assertEquals(true, SystemOutput.contains("Please select a destination:"));
    }
    
    /**
     * test for the correct output of GPS's location
     */
    
    public void demotest() {
        WiscMapApp.main(null);
    }
    
    /**
     * main method to run at least 5 tests
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        BackendDeveloperTests AGT = new BackendDeveloperTests();
        AGT.test1();
        AGT.test2();
        AGT.test3();
        AGT.test4();
        AGT.test5();
        AGT.Integration1();
        AGT.Integration2();
        AGT.CodeReviewOfFrontEndDeveloper1();
        AGT.CodeReviewOfFrontEndDeveloper2();
        AGT.demotest();
    }
}
