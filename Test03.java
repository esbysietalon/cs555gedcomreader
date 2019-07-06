import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test03 {
        @Test
        public void Test_US03() {
            Main main = new Main();
            main.readData("UserStory03.ged");

            USOutput uso = US03.unbornDead(main.getIndividuals());
            ArrayList<Individual> usoI = uso.getIndi();

            ArrayList<Individual> allI = main.getIndividuals();

            for(Individual i : allI) {
                if (usoI.contains(i)) {
                    assertFalse(i.getDeathDate().equals("NA"));
                    assertTrue(Main.getDateDistance(Main.convertDateYMD(i.getBirthDate()), Main.convertDateYMD(i.getDeathDate())) < 0);
                }else{
                    assertTrue(i.getDeathDate().equals("NA") || Main.getDateDistance(Main.convertDateYMD(i.getBirthDate()), Main.convertDateYMD(i.getDeathDate())) >= 0);
                }
            }

        }

}
