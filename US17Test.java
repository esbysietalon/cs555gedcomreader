//JMJ

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Marzullo
 */
public class US17Test {
    
    public US17Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findParentsMarriedToChildren method, of class US17.
     */
    @Test
    public void testFindParentsMarriedToChildren() {
        Main main = new Main();
	main.readData("UserStory17.ged");
		
        ArrayList<Individual> individuals = main.getIndividuals();
        ArrayList<Family> families = main.getFamilies();
        ArrayList<Individual> expResult = new ArrayList<>();
        expResult.add(new Individual());
        expResult.get(0).setId("@I1@");
        ArrayList<Individual> result = US17.findParentsMarriedToChildren(individuals, families);
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
        
        
        
    }
    
}
