//JMJ
import java.util.*;

/**
 *
 * @author Nick Marzullo
 */
public class US21 {
    public static ArrayList<Family> correctGenderForRole(ArrayList<Family> families, ArrayList<Individual> individuals) {
        ArrayList<Family> anomalousFamilies = new ArrayList<>();
        family: for(Family f: families) {
            husband: for (Individual h: individuals) {
                if (h.getId().equals(f.getHusbandId())) {
                    if(!(h.getSex().equals("M"))) {
                        anomalousFamilies.add(f);
                        break family;
                    }
                    break husband;
                }
            }
            wife: for (Individual w: individuals) {
                if (w.getId().equals(f.getWifeId())) {
                    if(!(w.getSex().equals("F"))) {
                        anomalousFamilies.add(f);
                        break family;
                    }
                    break wife;
                }
            }
        }
        return anomalousFamilies;
    }
}
