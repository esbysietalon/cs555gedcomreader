import java.util.*;

public class US30{
  /**
  * Returns the individuals that are married and still alive. It does not check if the spouse is dead to decide if they are still married.
  */
  public static ArrayList<Individual> listLivingMarried(ArrayList<Individual> individuals, ArrayList<Family> families){
    ArrayList<Individual> livingMarried = new ArrayList<>();
    String hID;
    String wID;
  // Nick, put a comment here
   for(Family f : families){
     hID = f.getHusbandId();
     wID = f.getWifeId();
     for(Individual i : individuals){
       if(hID.equals(i.getId()) || wID.equals(i.getId())){
         if(i.isAlive()){
           if(!livingMarried.contains(i)){
             livingMarried.add(i);
           }
         }
       }
     }
   }
   return livingMarried;
  }
}