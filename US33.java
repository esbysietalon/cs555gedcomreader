import java.util.*;

public class US33{

  public static ArrayList<Individual> listOrphans(ArrayList<Individual> individuals, ArrayList<Family> families){
    ArrayList<Individual> orphans = new ArrayList<>();
    for (Individual i : individuals){
      ArrayList<Individual> parents = i.getParents();
      if (i.getAge() < 18 && parentsDead(parents)){
        orphans.add(i);
      }
    }
    return orphans;
  }

  public static boolean parentsDead(ArrayList<Individual> parents){
    for (Individual i : parents){
      if(i.isAlive()){
        return false;
      }
    }
    return true;
  }

}
