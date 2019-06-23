import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Test02{
	@Test
	public void Test_US02(){
		Main main = new Main();
		main.readData("UserStory02.ged");
		
		ArrayList<Family> all = main.getFamilies();
		ArrayList<Family> families = US02.unbornMarriage(main.getFamilies()).getFam();
		

		for(Family f : families) {

			if(((Individual)Main.getById(f.getHusbandId())).getBirthDate().equals("NA"))
				continue;
			if(((Individual)Main.getById(f.getWifeId())).getBirthDate().equals("NA"))
				continue;
			if(f.getMarriageDate().equals("-"))
				continue;
			assertTrue((Main.getDateDistance(Main.convertDateYMD(((Individual)Main.getById(f.getHusbandId())).getBirthDate()), Main.convertDateYMD(f.getMarriageDate())) <= 0) || (Main.getDateDistance(Main.convertDateYMD(((Individual)Main.getById(f.getWifeId())).getBirthDate()), Main.convertDateYMD(f.getMarriageDate())) <= 0));
		}
		for(Family f : all) {
			if(families.contains(f) || f.getMarriageDate().equals("-"))
				continue;
			if(((Individual)Main.getById(f.getHusbandId())).getBirthDate().equals("NA"))
				continue;
			if(((Individual)Main.getById(f.getWifeId())).getBirthDate().equals("NA"))
				continue;
			if(f.getMarriageDate().equals("-"))
				continue;
			assertTrue((Main.getDateDistance(Main.convertDateYMD(((Individual)Main.getById(f.getHusbandId())).getBirthDate()), Main.convertDateYMD(f.getMarriageDate())) > 0) || (Main.getDateDistance(Main.convertDateYMD(((Individual)Main.getById(f.getWifeId())).getBirthDate()), Main.convertDateYMD(f.getMarriageDate())) > 0));
		}
	}
}