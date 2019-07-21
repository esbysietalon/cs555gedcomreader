import java.util.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test10 {
    @Test
    public void Test_US10() {
        Main main = new Main();
        Main.readData("UserStory21.ged");
        
        ArrayList<Individual> uso = US10.checkMarriageAges(main.getIndividuals(), main.getFamilies());
        
        boolean anomaly = false;
        
        //Check that the output from US21 is the expected result, that is: 1 result with id @I3@
	if(!(uso.size()==1 && uso.get(0).getId().equals("@I3@"))) {
            anomaly = true; // if the expected result is not found, flag a problem
        }
	assertFalse(anomaly);
    }
}
