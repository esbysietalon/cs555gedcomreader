import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test06 {
    @Test
    public static void Test_US06(){
        Main main = new Main();
        main.readData("UserStory06.ged");

        USOutput uso = US06.deadDivorce(main.getFamilies());
        for(Family f : main.getFamilies()){
            Individual hub = (Individual) Main.getById(f.getHusbandId());
            Individual wif = (Individual) Main.getById(f.getWifeId());

            if(uso.getIndi().contains(hub)){
                assertTrue(Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(hub.getDeathDate())) < 0);
            }else{
                assertFalse(Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(hub.getDeathDate())) < 0);
            }

            if(uso.getIndi().contains(wif)){
                assertTrue(Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(wif.getDeathDate())) < 0);
            }else{
                assertFalse(Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(wif.getDeathDate())) < 0);
            }
        }
    }
}
