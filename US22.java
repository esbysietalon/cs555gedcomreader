//JMJ
import java.util.*;
import java.util.stream.Collectors;

/**
 * All individual IDs should be unique and all family IDs should be unique
 * @author Nick Marzullo
 */
public class US22 {

    /**
     * Checks to make sure that there no duplicate IDs
     * @param entities ArrayList of GedcomObjects to test
     * @return ArrayList containing GedcomObjects with the same ID. Empty if there are no problems
     */
    public static ArrayList<GedcomObject> uniqueIDs (ArrayList<GedcomObject> entities) {
        ArrayList<GedcomObject> failures = new ArrayList<>();
        for (int i = 0; i < entities.size();) {
            if(Collections.frequency(entities, entities.get(i)) > 1) {
                failures.add(entities.get(i));
                entities.remove(i);
            } else {
                i++;
            }
        }
        return (ArrayList<GedcomObject>)failures.stream().distinct().collect(Collectors.toList());
    }
}
