import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test05 {
    @Test
    public void Test_US05(){
        Main main = new Main();
        main.readData("UserStory05.ged");

        USOutput uso = US05.deadMarriage(main.getFamilies());
        for(Family f : main.getFamilies()){
            Individual hub = (Individual) Main.getById(f.getHusbandId());
            Individual wif = (Individual) Main.getById(f.getWifeId());

            if(uso.getIndi().contains(hub)){
                assertTrue(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(hub.getDeathDate())) < 0);
            }else{
                assertFalse(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(hub.getDeathDate())) < 0);
            }

            if(uso.getIndi().contains(wif)){
                assertTrue(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(wif.getDeathDate())) < 0);
            }else{
                assertFalse(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(wif.getDeathDate())) < 0);
            }
        }
    }
}
