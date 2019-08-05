
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test34{

  @Test
  public void Test_US34(){
    Main main = new Main();
    main.readData("UserStory34.ged");

    ArrayList<Individual> largeAgesDiffs = US34.listLargeAgeDiffs(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I2@");


    main.compareIDs(largeAgesDiffs, required);

  }


}
