import java.util.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test26 {
    @Test
    public void Test_US26() {
    	Main main = new Main();
	Main.readData("UserStory03.ged");
        
        //check spouses
        ArrayList<Individual> mismatchedSpousesI = US26.checkIndividualSpouses(main.getIndividuals(), main.getFamilies());
        ArrayList<Family> mismatchedSpousesF = US26.checkFamilySpouses(main.getIndividuals(), main.getFamilies());
        //check children
        ArrayList<Individual> mismatchedChildrenI = US26.checkIndividualChildren(main.getIndividuals(), main.getFamilies());
        ArrayList<Family> mismatchedChildrenF = US26.checkFamilyChildren(main.getIndividuals(), main.getFamilies());

	boolean anomaly1 = false;
        boolean anomaly2 = false;
        boolean anomaly3 = false;
        boolean anomaly4 = false;
        
        //Check that the output from US21 is the expected result, that is: 1 result in the 2nd test
	if(!(mismatchedSpousesI.size()==0)) {
            anomaly1 = true; // if the expected result is not found, flag a problem
        }
        if(!(mismatchedSpousesF.size()==1)) {
            anomaly2 = true; // if the expected result is not found, flag a problem
        }
        if(!(mismatchedChildrenI.size()==0)) {
            anomaly3 = true; // if the expected result is not found, flag a problem
        }
        if(!(mismatchedChildrenF.size()==0)) {
            anomaly4 = true; // if the expected result is not found, flag a problem
        }
	assertFalse(anomaly1);
        assertFalse(anomaly2);
        assertFalse(anomaly3);
        assertFalse(anomaly4);
    }
}
