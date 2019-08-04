import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test08 {
    @Test
    public void Test_US08(){
        Main main = new Main();
        main.readData("UserStory08.ged");

        USOutput uso = US08.outOfWedlock(main.getFamilies());
        for(Family f : main.getFamilies()){
            ArrayList<String> childrenIds = f.getChildrenIds();
            for(String id : childrenIds){
                Individual child = (Individual) Main.getById(id);
                if ((!f.getDivorceDate().equals("NA") && Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(child.getBirthDate())) > 0.75) || Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(child.getBirthDate())) < 0) {
                    assertTrue(uso.getIndi().contains(child));
                }else{
                    assertFalse(uso.getIndi().contains(child));
                }
            }
        }
    }
}