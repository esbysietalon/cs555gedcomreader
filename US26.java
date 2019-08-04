//JMJ
import java.util.*;

/**
 *
 * @author Nick Marzullo
 */
public class US26 {
    public static ArrayList<Individual> checkIndividualSpouses(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Individual> outliers = new ArrayList<>();
        for(Individual i : individuals) {
            if(!i.getFAMSs().isEmpty()) {
                boolean spouse = false;
                findSpouse:for(Family s : families) {
                    for(String sid: i.getFAMSs()) {
                        if(s.getId().equals(sid)) {
                            spouse = true;
                            break findSpouse;
                        }
                    }
                }
                if(!spouse) {
                    outliers.add(i);
                }
            }
        }
        return outliers;
    }
    public static ArrayList<Individual> checkIndividualChildren(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Individual> outliers = new ArrayList<>();
        for(Individual i : individuals) {
            if(!i.getFAMCs().isEmpty()) {
                boolean child = false;
                findChild:for(Family c : families) {
                    for(String cid: i.getFAMCs()) {
                        if(c.getId().equals(cid)) {
                            child = true;
                            break findChild;
                        }
                    }
                }
                if(!child) {
                    outliers.add(i);
                }
            }
        }
        return outliers;
    }
    
    public static ArrayList<Family> checkFamilySpouses(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Family> outliers = new ArrayList<>();
        for(Family f : families) {
            boolean husband = false;
            findHusband:for(Individual h : individuals) {
                if(f.getHusbandId().equals(h.getId())) {
                    for(String fid : h.getFAMSs()) {
                        if(f.getId().equals(fid)) {
                            husband = true;
                            break findHusband;
                        }
                    }
                }
            }
            boolean wife = false;
            findWife:for(Individual w : individuals) {
                if(f.getWifeId().equals(w.getId())) {
                    for(String fid : w.getFAMSs()) {
                        if(f.getId().equals(fid)) {
                            wife = true;
                            break findWife;
                        }
                    }
                }
            }
            if(husband == false || wife == false) {
                outliers.add(f);
            }
        }
        return outliers;
    }
    
    public static ArrayList<Family> checkFamilyChildren(ArrayList<Individual> individuals, ArrayList<Family> families) {
        ArrayList<Family> outliers = new ArrayList<>();
        for(Family f : families) {
            ArrayList<Boolean> children = new ArrayList<>(f.getChildrenIds().size());
            for(int i = 0; i < f.getChildrenIds().size(); i++) {
                children.add(Boolean.FALSE);
                findChildren:for(Individual c : individuals) {
                    if(f.getChildrenIds().get(i).equals(c.getId())) {
                        for(String fid : c.getFAMCs()) {
                            if(f.getId().equals(fid)) {
                                children.set(i, Boolean.TRUE);
                                break findChildren;
                            }
                        }
                    }
                }
            }
            for(Boolean b : children) {
                if(b.booleanValue()==false) {
                    outliers.add(f);
                    break;
                }
            }
        }
        return outliers;
    }
    
}
