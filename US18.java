//JMJ
import java.util.*;

/**
 * 
 * @author Nick Marzullo
 */
public class US18 {
    public static ArrayList<Family> checkIncest(ArrayList<Family> families) {
        ArrayList<Family> incestousFamilies = new ArrayList<>();
        ArrayList<String> spouses = new ArrayList<>();
        for(int i = 0 ; i < families.size(); i++) {
            for(int j = 0; j < families.size() && j!=i; j++) {
                spouses.add(families.get(j).getHusbandId());
                spouses.add(families.get(j).getWifeId());
                if(families.get(i).getChildrenIds().containsAll(spouses)) {
                    incestousFamilies.add(families.get(i));
                }
                spouses.clear();
            }
        }
        return incestousFamilies;
    }
}
