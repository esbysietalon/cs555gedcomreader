
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test35{

  @Test
  public void Test_US35(){
    Main main = new Main();
		main.readData("UserStory35.ged");

    ArrayList<Individual> recentDeadPeople = US35.listRecentDeceased(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I13@");
    required.add("@I5@");
    required.add("@I8@");


    main.compareIDs(recentDeadPeople, required);

  }


}
