import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Test22{
	@Test
	public void Test_US22(){
		Main main = new Main();
		main.readData("UserStory22.ged");

		ArrayList<GedcomObject> uso = new ArrayList<GedcomObject>();
		US22.uniqueIDs(uso);

		for(int i = 0; i < uso.size(); i++){
			int idCount = 0;
			for(int j = 0; j < main.getIndividuals().size(); j++){
				if(uso.get(i).getId().equals(main.getIndividuals().get(j).getId()))
					idCount++;
			}
			for(int j = 0; j < main.getFamilies().size(); j++){
				if(uso.get(i).getId().equals(main.getFamilies().get(j).getId()))
					idCount++;
			}
			assertTrue(idCount > 1);
		}
	}
}