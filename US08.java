import java.util.ArrayList;
public class US08 {
    public static USOutput outOfWedlock(ArrayList<Family> families){
        ArrayList<Individual> usI = new ArrayList<>();
        for(Family f : families){
            ArrayList<String> childrenIds = f.getChildrenIds();
            for(String id : childrenIds){
                Individual child = (Individual) Main.getById(id);
                if ((!f.getDivorceDate().equals("NA") && Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate().trim()), Main.convertDateYMD(child.getBirthDate().trim())) > 0.75) || (!f.getMarriageDate().equals("-") && Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate().trim()), Main.convertDateYMD(child.getBirthDate().trim())) < 0)) {
                    System.out.println("time between divorced and born: " + Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate().trim()), Main.convertDateYMD(child.getBirthDate().trim())));
                    usI.add(child);
                }
            }
        }
        return new USOutput(usI, new ArrayList<Family>());
    }
}
