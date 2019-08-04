import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Test07 {
    @Test
    public void Test_US07(){
        Main main = new Main();
        main.readData("UserStory07.ged");

        USOutput uso = US07.superOld(main.getIndividuals());
        for(Individual i : main.getIndividuals()){
            if(i.isAlive()){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String nowDate = dateFormat.format(date);
                double age = Main.getDateDistance(Main.convertDateYMD(i.getBirthDate()), nowDate);
                if(age >= 150){
                    assertTrue(uso.getIndi().contains(i));
                }else{
                    assertFalse(uso.getIndi().contains(i));
                }
            }else{
                double age = Main.getDateDistance(Main.convertDateYMD(i.getBirthDate()), Main.convertDateYMD(i.getDeathDate()));
                if(age >= 150){
                    assertTrue(uso.getIndi().contains(i));
                }else{
                    assertFalse(uso.getIndi().contains(i));
                }
            }
        }
    }
}