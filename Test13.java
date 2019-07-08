
import java.util.ArrayList;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test13{

  @Test
  public void Test_US13(){
    Main main = new Main();
		main.readData("UserStory13.ged");

    ArrayList<Individual> closeSiblings = US13.siblingsTooClose(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I1@");
    required.add("@I4@");

    main.compareIDs(closeSiblings, required);

  }
}
