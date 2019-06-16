//JMJ
import java.util.*;

/**
 * Parents should not marry any of their children
 * @author Nick Marzullo
 */
public class US17 {

    /**
     * Checks to see if anyone is married to one of their children. 
     * Assumes that all individuals correctly store the Family IDs of their marriages and of their parents
     * @param individuals ArrayList of Individual objects to test
     * @param families ArrayList of Family objects to test
     */
    public static ArrayList<Individual> findParentsMarriedToChildren(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Individual> creeps = new ArrayList<>();
        for (Individual individual : individuals) {
            ArrayList<String> spouseIDs = new ArrayList<>();
            for (String FAMS : individual.getFAMSs()) {
                for(Family family: families) {
                    if(FAMS.equals(family.getId())) {
                        if(individual.getId().equals(family.getHusbandId())) {
                            spouseIDs.add(family.getWifeId());
                        }
                        else if(individual.getId().equals(family.getWifeId())) {
                            spouseIDs.add(family.getHusbandId());
                        }
                    }
                }
            }
            for (String FAMS : individual.getFAMSs()) {
                for (Family family : families) {
                    if(FAMS.equals(family.getId())) {
                        if(!Collections.disjoint(family.getChildrenIds(), spouseIDs)) {
                            creeps.add(individual);
                        }
                    }
                }
            }
        }
        return creeps;
    }
    
    
}
