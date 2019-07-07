import java.util.*;

public class US12 {
  public static ArrayList<Individual> parentsTooOld(ArrayList<Individual> individuals, ArrayList<Family> families){
    ArrayList<Individual> oldParents = new ArrayList<>();
    Individual father;
    Individual mother;
    for(Individual i : individuals){
      for(String famStr : i.getFAMCs()){
        Family f = (Family) Main.getById(famStr);
        father = (Individual) Main.getById(f.getHusbandId());
        mother = (Individual) Main.getById(f.getWifeId());
        if((father.getAge() - i.getAge()) >= 80){
          if(!oldParents.contains(i)){
              oldParents.add(i);
            }
        }
        if((mother.getAge() - i.getAge()) >= 60){
          if(!oldParents.contains(i)){
              oldParents.add(i);
            }
        }
      }
    }
    return oldParents;
  }
}
