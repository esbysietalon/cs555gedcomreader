import java.util.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test21 {

	@Test
	public void Test_US21() {
		Main main = new Main();
		Main.readData("UserStory21.ged");

		ArrayList<Family> uso = US21.correctGenderForRole(main.getFamilies(), main.getIndividuals());

		boolean anomaly = false;

                //Check that the output from US21 is the expected result, that is: 1 result with id @F1@
		if(!(uso.size()==1 && uso.get(0).getId().equals("@F1@"))) {
                    anomaly = true; // if the expected result is not found, flag a problem
                }

		assertFalse(anomaly);
	}
}
