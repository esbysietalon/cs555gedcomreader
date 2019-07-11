import java.util.*;

public class US35{
    public static ArrayList<Individual> listRecentBirths(ArrayList<Individual> individuals){
      ArrayList<Individual> recentBirths = new ArrayList<>();
      for(Individual i : individuals){
        if(i.getAgeInDays() < 30 && i.getAgeInDays() >= 0){
          recentBirths.add(i);
        }
      }
      return recentBirths;
    }
}
