package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.types.SystemConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Test for AnalyzerApp class.
 */
public class AnalyzerAppTest {

  @Before
  public void setUp() throws Exception {
    AnalyzerApp.resetAnalyzerApp();
  }

  /**
   * Return the configuration from the AnalyzerApp, this is initialized when AnalyzerApp is instantiated, which
   * happens when it retrieved.
   */
  @Test
  public void getConfig() {
    AnalyzerApp.getAnalyzerApp();
    SystemConfig systemConfig =  AnalyzerApp.getAnalyzerApp().getConfig();
    assertEquals(true, systemConfig instanceof SystemConfig);
  }

  /**
   * Test that we can get the AnalyzerApp instance form the singleton.
   */
  @Test
  public void getAnalyzerApp() {
    AnalyzerApp analyzerApp = AnalyzerApp.getAnalyzerApp();
    assertEquals(true, analyzerApp instanceof AnalyzerApp);
  }

  /**
   * test that we can instantiate the data structure to an object of ArrayList.
   */
  @Test
  public void initDataStructure() {
    AnalyzerApp.getAnalyzerApp().initDataStructure();
    Object testData = AnalyzerApp.getAnalyzerApp().getData();
    assertEquals(true, testData instanceof ArrayList);
  }

  /**
   * Check that is we update the data then we will have expected number of members in teh arraylist.
   */
  @Test
  public void updateData() {
    AnalyzerApp.getAnalyzerApp().initDataStructure();
    AnalyzerApp.getAnalyzerApp().updateData(new int[1]);
    AnalyzerApp.getAnalyzerApp().updateData(new int[1]);
    AnalyzerApp.getAnalyzerApp().updateData(new int[1]);
    int testDataSize =  AnalyzerApp.getAnalyzerApp().getData().size();
    assertEquals(3,testDataSize);
  }

  /**
   * Add data to the data structure and then retrieve it and make sure it is all as it was added.
   */
  @Test
  public void getData() {
    int[] dd = new int[] {1};

    AnalyzerApp.getAnalyzerApp().initDataStructure();
    AnalyzerApp.getAnalyzerApp().updateData(new int[] {1});
    AnalyzerApp.getAnalyzerApp().updateData(new int[] {2});
    AnalyzerApp.getAnalyzerApp().updateData(new int[] {3});

    int expected = 1;
    for (int[] datum : AnalyzerApp.getAnalyzerApp().getData()) {
      assertEquals(expected,datum[0]);
      expected++;
    }
  }

}