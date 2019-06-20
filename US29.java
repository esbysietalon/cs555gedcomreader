import java.util.*;

public class US29 {

  public static ArrayList<Individual> listDeceased(ArrayList<Individual> individuals){
    ArrayList<Individual> deadPeople = new ArrayList<>();
    for(Individual i : individuals){
      if(!i.isAlive()){
        deadPeople.add(i);
      }
    }
    return deadPeople;
  }
}
