import java.util.ArrayList;

public class US05 {
    public static USOutput deadMarriage(ArrayList<Family> families){
        ArrayList<Individual> usI = new ArrayList<>();
        ArrayList<Family> usF = new ArrayList<>();
        for(Family f : families){
            if(!f.getMarriageDate().equals("-")){
                Individual hub = (Individual)Main.getById(f.getHusbandId());
                Individual wif = (Individual)Main.getById(f.getWifeId());
                if(!hub.getDeathDate().equals("NA"))
                    if(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(hub.getDeathDate())) < 0){
                        if(!usI.contains(hub))
                            usI.add(hub);
                        if(!usF.contains(f))
                            usF.add(f);
                    }
                if(!wif.getDeathDate().equals("NA"))
                    if(Main.getDateDistance(Main.convertDateYMD(f.getMarriageDate()), Main.convertDateYMD(wif.getDeathDate())) < 0){
                        if(!usI.contains(wif))
                            usI.add(wif);
                        if(!usF.contains(f))
                            usF.add(f);
                    }
            }
        }
        USOutput uso = new USOutput(usI, usF);
        return uso;
    }
}
