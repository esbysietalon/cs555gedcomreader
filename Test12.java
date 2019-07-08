
import java.util.ArrayList;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test12{

  @Test
  public void Test_US12(){
    Main main = new Main();
		main.readData("UserStory12.ged");

    ArrayList<Individual> oldParents = US12.parentsTooOld(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I1@");
    required.add("@I4@");

    main.compareIDs(oldParents, required);

  }
}
