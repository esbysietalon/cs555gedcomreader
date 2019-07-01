import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test17 {

	@Test
	public void Test_US17() {
		Main main = new Main();
		main.readData("UserStory17.ged");

		ArrayList<Individual> uso = US17.findParentsMarriedToChildren(main.getIndividuals(), main.getFamilies());

		boolean creep = true;

		for (Individual individual : uso) {
			ArrayList<String> spouseIDs = new ArrayList<>();
			for (String FAMS : individual.getFAMSs()) {
				for(Family family: main.getFamilies()) {
					if(FAMS.equals(family.getId())) {
						if(individual.getId().equals(family.getHusbandId())) {
							spouseIDs.add(family.getWifeId());
						}
						else if(individual.getId().equals(family.getWifeId())) {
							spouseIDs.add(family.getHusbandId());
						}
					}
				}
			}
			for (String FAMS : individual.getFAMSs()) {
				for (Family family : main.getFamilies()) {
					creep = false;
					if(FAMS.equals(family.getId())) {
						if(!Collections.disjoint(family.getChildrenIds(), spouseIDs)) {
							creep = true;
						}
					}
				}
			}
		}

		assertTrue(creep);
	}
}
