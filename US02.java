import java.util.ArrayList;

public class US02{
    public static ArrayList<Individual> unbornMarriage(ArrayList<Family> marriages){
        ArrayList<Individual> output = new ArrayList<Individual>();
        for(Family f : marriages){
            String badFormatDate = f.getMarriageDate();
            String marrDate = Main.convertDateYMD(badFormatDate);

            Individual husb = (Individual) Main.getById(f.getHusbandId());
            Individual wife = (Individual) Main.getById(f.getWifeId());

            String hbirthBadFormat = husb.getBirthDate();
            String wbirthBadFormat = wife.getBirthDate();

            String hbirth = Main.convertDateYMD(hbirthBadFormat);
            String wbirth = Main.convertDateYMD(wbirthBadFormat);

            if(Main.getDateDistance(hbirth, marrDate) < 0){
                output.add(husb);
            }
            if(Main.getDateDistance(wbirth, marrDate) < 0){
                output.add(wife);
            }
        }
        return output;
    }
}