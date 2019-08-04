import java.util.ArrayList;
public class US08 {
    public static USOutput outOfWedlock(ArrayList<Family> families){
        ArrayList<Individual> usI = new ArrayList<>();
        for(Family f : families){
            ArrayList<String> childrenIds = f.getChildrenIds();
            for(String id : childrenIds){
                Individual child = (Individual) Main.getById(id);
                if ((!f.getDivorceDate().equals("NA") && Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(child.getBirthDate())) > 0.75) || Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(child.getBirthDate())) < 0) {
                    usI.add(child);
                }
            }
        }
        return new USOutput(usI, new ArrayList<Family>());
    }
}
