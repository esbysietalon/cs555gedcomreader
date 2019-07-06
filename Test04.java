import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test04 {
    @Test
    public void Test_US04() {
        Main main = new Main();
        main.readData("UserStory04.ged");

        USOutput uso = US04.unmarriedDivorce(main.getFamilies());
        ArrayList<Individual> usoI = uso.getIndi();
        ArrayList<Family> usoF = uso.getFam();

        ArrayList<Individual> allI = main.getIndividuals();
        ArrayList<Family> allF = main.getFamilies();
        for(Family f : allF){
            if(usoF.contains(f)){
                assertFalse(f.getDivorceDate().equals("NA"));
                assertTrue(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(f.getDivorceDate())) < 0);
                boolean goodIs = false;
                boolean badIs = false;
                for(Individual i : allI){
                    if(usoI.contains(i)){
                        if(f.getHusbandId().equals(i.getId()) || f.getWifeId().equals(i.getId())){
                            goodIs = true;
                        }
                    }else{
                        if(f.getHusbandId().equals(i.getId()) || f.getWifeId().equals(i.getId())){
                            badIs = true;
                        }
                    }
                }
                assertTrue(goodIs);
                assertFalse(badIs);
            }else{
                assertTrue((f.getDivorceDate().equals("NA")) || Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(f.getDivorceDate())) >= 0);
            }
        }
    }

}
