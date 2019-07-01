import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test30{

  @Test
  public void Test_US30(){
    Main main = new Main();
		main.readData("UserStory30.ged");

    ArrayList<Individual> deadPeople = US30.listLivingMarried(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I2@");
    required.add("@I3@");
    required.add("@I10@");
    required.add("@I11@");
    required.add("@I6@");
    required.add("@I7@");
    required.add("@I9@");
    required.add("@I15@");
    required.add("@I19@");

    main.compareIDs(deadPeople, required);

  }


}
