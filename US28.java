//JMJ
import java.util.*;

/**
 * 
 * @author Nick Marzullo
 */
public class US28 {
    
    /**
     * This function orders any given list of individuals by age from oldest to youngest.
     * @param individuals Individual objects to order
     * @return ArrayList containing individuals ordered from youngest to oldest
     */
    public static ArrayList<Individual> orderByAge(ArrayList<Individual> individuals) {
        individuals.sort(new ReverseSortByAge());
        return individuals;
    }
}

/**
 * Note: this comparator imposes orderings that are inconsistent with equals.
 * @author Nick Marzullo
 */
class ReverseSortByAge implements Comparator<Individual> {
    @Override
    /**
     * This compare function sorts backwards from the standard
     */
    public int compare(Individual a, Individual b) {
        return b.getAgeInDays()-a.getAgeInDays();
    }
}