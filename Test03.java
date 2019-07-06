import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
                    assertTrue(Main.getDateDistance(i.getBirthDate(), i.getDeathDate()) < 0);
                }else{
                    assertTrue(i.getDeathDate().equals("NA") || Main.getDateDistance(i.getBirthDate(), i.getDeathDate()) < 0);
                }
            }

        }

}
