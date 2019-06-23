import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test01 {

	@Test
	public void Test_US01() {
		Main main = new Main();
		main.readData("UserStory01.ged");
		
		USOutput uso = US01.futureDates(main.getTags());
		ArrayList<Family> usoF = uso.getFam();
		ArrayList<Individual> usoI = uso.getIndi();
		
		ArrayList<Family> allF = main.getFamilies();
		ArrayList<Individual> allI = main.getIndividuals();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    String nowDate = dateFormat.format(date);
	    
	    for(Family f : allF) {
	    	boolean goodDate = true;
	    	if(!f.getDivorceDate().equals("NA"))
    			if(main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), nowDate) < 0)
    				goodDate = false;
    		if(!f.getMarriageDate().equals("-"))
    			if(main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), nowDate) < 0)
    				goodDate = false;
	    	if(!usoF.contains(f)) {
	    		assertTrue(goodDate);
	    	}else {
	    		assertFalse(goodDate);
	    	}
	    }
	    
	    for(Individual i : allI) {
	    	boolean goodDate = true;
	    	if(!i.getDeathDate().equals("NA"))
    			if(main.getDateDistance(Main.convertDateYMD(i.getDeathDate()), nowDate) < 0)
    				goodDate = false;
    		if(!i.getBirthDate().equals("NA"))
    			if(main.getDateDistance(Main.convertDateYMD(i.getBirthDate()), nowDate) < 0)
    				goodDate = false;
	    	if(!usoI.contains(i)) {    		
	    		assertTrue(goodDate);
	    	}else {
	    		assertFalse(goodDate);
	    	}
	    }
	    
	}
}
