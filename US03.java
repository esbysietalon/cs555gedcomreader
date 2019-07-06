import java.util.ArrayList;

public class US03 {
    public static USOutput unbornDead(ArrayList<Individual> individuals){
        ArrayList<Individual> uD = new ArrayList<Individual>();
        for(Individual i : individuals){
            if(!i.getDeathDate().equals("NA")){
                if(Main.getDateDistance(Main.convertDateYMD(i.getBirthDate()), Main.convertDateYMD(i.getDeathDate())) < 0){
                    uD.add(i);
                }
            }
        }
        USOutput uso = new USOutput(uD, new ArrayList<Family>());
        return uso;
    }
}
