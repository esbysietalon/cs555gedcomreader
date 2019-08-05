import java.util.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test28 {
    @Test
    public void Test_US28() {
    	Main main = new Main();
	Main.readData("UserStory08.ged");

        ArrayList<Individual> children = new ArrayList<>();
        for(Individual i : main.getIndividuals()) {
            if(main.getFamilies().get(0).getChildrenIds().contains(i.getId())) {
                children.add(i);
            }
        }
        ArrayList<Individual> uso = US28.orderByAge(children);

	boolean anomaly1 = false;
        boolean anomaly2 = false;
        boolean anomaly3 = false;
        boolean anomaly4 = false;

        //Check that the output from US21 is the expected result, that is: @US08I4@, @US08I3@, @US08I5@, @US08I6@
	if(!(uso.get(0).getId().equals("@US08I4@"))) {
            anomaly1 = true; // if the expected result is not found, flag a problem
        }
        if(!(uso.get(1).getId().equals("@US08I3@"))) {
            anomaly2 = true; // if the expected result is not found, flag a problem
        }
        if(!(uso.get(2).getId().equals("@US08I5@"))) {
            anomaly3 = true; // if the expected result is not found, flag a problem
        }
        if(!(uso.get(3).getId().equals("@US08I6@"))) {
            anomaly4 = true; // if the expected result is not found, flag a problem
        }
	assertFalse(anomaly1);
        assertFalse(anomaly2);
        assertFalse(anomaly3);
        assertFalse(anomaly4);
    }
}
