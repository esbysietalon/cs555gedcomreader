
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test33{

  @Test
  public void Test_US33(){
    Main main = new Main();
    main.readData("UserStory33.ged");

    ArrayList<Individual> orphans = US33.listOrphans(main.getIndividuals());
    ArrayList<String> required = new ArrayList<String>();
    required.add("@I1@");
    required.add("@I4@");


    main.compareIDs(orphans, required);

  }


}
