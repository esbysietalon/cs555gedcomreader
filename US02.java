import java.util.ArrayList;

public class US02{
    public static USOutput unbornMarriage(ArrayList<Family> marriages){
        ArrayList<Individual> output = new ArrayList<Individual>();
        ArrayList<Family> outputF = new ArrayList<Family>();
        for(Family f : marriages){
            String badFormatDate = f.getMarriageDate();
            if(badFormatDate.equals("-")){
                continue;
            }
            String marrDate = Main.convertDateYMD(badFormatDate);

            Individual husb = (Individual) Main.getById(f.getHusbandId());
            Individual wife = (Individual) Main.getById(f.getWifeId());

            String hbirthBadFormat = husb.getBirthDate();
            String wbirthBadFormat = wife.getBirthDate();

            String hbirth = Main.convertDateYMD(hbirthBadFormat);
            String wbirth = Main.convertDateYMD(wbirthBadFormat);

            if(Main.getDateDistance(hbirth, marrDate) <= 0){
                output.add(husb);
                if(!outputF.contains(f))
                	outputF.add(f);
            }
            if(Main.getDateDistance(wbirth, marrDate) <= 0){
                output.add(wife);
                if(!outputF.contains(f))
                	outputF.add(f);
            }
        }
        USOutput realOutput = new USOutput(output, outputF);
        return realOutput;
    }
}