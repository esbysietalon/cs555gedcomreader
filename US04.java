import java.util.ArrayList;

public class US04 {
    public static USOutput unmarriedDivorce(ArrayList<Family> families){
        ArrayList<Individual> uD = new ArrayList<Individual>();
        ArrayList<Family> uDF = new ArrayList<Family>();
        for(Family f : families){
            if(!f.getDivorceDate().equals("NA")){
                if(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(f.getDivorceDate())) < 0){
                    uDF.add(f);
                    Individual hub = (Individual)Main.getById(f.getHusbandId());
                    Individual wif = (Individual)Main.getById(f.getWifeId());
                    if(!uD.contains(hub))
                        uD.add(hub);
                    if(!uD.contains(wif))
                        uD.add(wif);
                }
            }
        }
        USOutput uso = new USOutput(uD, uDF);
        return uso;
    }
}
