import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test29{

  @Test
  public void Test_US29(){
    Main main = new Main();
		main.readData("UserStory29.ged");

    ArrayList<Individual> deadPeople = US29.listDeceased(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I13@");
    required.add("@I14@");
    required.add("@I16@");
    required.add("@I5@");
    required.add("@I8@");


    main.compareIDs(deadPeople, required);

  }


}
