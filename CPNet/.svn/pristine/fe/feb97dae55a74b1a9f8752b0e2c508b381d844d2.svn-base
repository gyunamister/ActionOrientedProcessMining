package org.processmining.tests.newpackage;
import junit.framework.TestCase;

import org.junit.Test;
import org.processmining.contexts.cli.CLI;

/**
 * @author michael
 *
 */
public class NewPackageTest extends TestCase {

  /**
 * @throws Throwable
 */
@Test
  public void testNewPackage1() throws Throwable {
    String args[] = new String[] {"-l"};
    CLI.main(args);
  }

  /**
 * @throws Throwable
 */
@Test
  public void testNewPackage2() throws Throwable {
    String testFileRoot = System.getProperty("test.testFileRoot", ".");
    String args[] = new String[] {"-f", testFileRoot+"/NewPackage_Example.txt"};
    
    CLI.main(args);
  }
  
  /**
 * @param args
 */
public static void main(String[] args) {
    junit.textui.TestRunner.run(NewPackageTest.class);
  }
  
}
