import java.util.*;

public class US13 {
  public static ArrayList<Individual> siblingsTooClose(ArrayList<Individual> individuals){
    ArrayList<Individual> closeSiblings = new ArrayList<>();
    int ageDifference;
    for(Individual i : individuals){
      for(Individual sibling : i.getSiblings()){
        ageDifference = Math.abs(sibling.getAgeInDays() - i.getAgeInDays());
        if(ageDifference > 2 && ageDifference < 243){
          if(!closeSiblings.contains(i)){
            closeSiblings.add(i);
          }
        }
      }
    }
    return closeSiblings;
    
  }
}
