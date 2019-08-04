import java.util.ArrayList;
public class US07 {
    public static USOutput superOld(ArrayList<Individual> individuals){
        ArrayList<Individual> usI = new ArrayList<>();
        for(Individual i : individuals){
            if(i.getAge() >= 150){
                usI.add(i);
            }
        }
        return new USOutput(usI, new ArrayList<Family>());
    }
}
