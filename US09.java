//JMJ
import java.util.*;
import java.util.stream.Collectors;

/**
 * checks to make sure no one was born before the death of their parents
 * @author Nick Marzullo
 */
public class US09 {
    public static ArrayList<Individual> findZombiePregnancy(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Individual> bornFromZombies = new ArrayList<>();
        for (Individual i : individuals) {
            if(!i.getBirthDate().equals("NA")) {
                if(!i.getFAMCs().isEmpty()) {
                    Family f  = (Family)Main.getById(i.getFAMCs().get(0));
                    Individual dad = (Individual)Main.getById(f.getHusbandId());
                    Individual mom = (Individual)Main.getById(f.getWifeId());
                    if(!dad.getDeathDate().equals("NA") && Main.getDateDistance(Main.convertDateYMD(dad.getDeathDate()), Main.convertDateYMD(i.getBirthDate())) < (2/3)) {
                        bornFromZombies.add(i);
                    }
                    if(!mom.getDeathDate().equals("NA") && Main.getDateDistance(Main.convertDateYMD(mom.getDeathDate()), Main.convertDateYMD(i.getBirthDate())) < 0) {
                        bornFromZombies.add(i);
                    }
                }
            }
        }
        return (ArrayList<Individual>)bornFromZombies.stream().distinct().collect(Collectors.toList());
    }
}
