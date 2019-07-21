//JMJ
import java.util.*;
import java.util.stream.Collectors;

/**
 * Ensures that everyone who is married was married after the age of 14
 * @author Nick Marzullo
 */
public class US10 {
    public static ArrayList<Individual> checkMarriageAges(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Individual> childSpouses = new ArrayList<>();
        for(Family f : families) {
            if(!f.getMarriageDate().equals("-")) {
                Individual husb = (Individual)Main.getById(f.getHusbandId());
                Individual wife = (Individual)Main.getById(f.getWifeId());
                if(!husb.getBirthDate().equals("NA")) {
                    if(Main.getDateDistance(Main.convertDateYMD(husb.getBirthDate()), Main.convertDateYMD(f.getMarriageDate())) < 14){
                        childSpouses.add(husb);
                    }
                }
                if(!wife.getBirthDate().equals("NA")) {
                    if(Main.getDateDistance(Main.convertDateYMD(wife.getBirthDate()), Main.convertDateYMD(f.getMarriageDate())) < 14){
                        childSpouses.add(wife);
                    }
                }
            }
        }
        return (ArrayList<Individual>)childSpouses.stream().distinct().collect(Collectors.toList());
    }
}
