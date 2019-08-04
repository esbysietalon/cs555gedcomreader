import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String nowDate = dateFormat.format(date);
    for(Family f : families){

     if(!f.getDivorceDate().equals("NA") && Main.getDateDistance(Main.convertDateYMD(f.getDivorceDate()), Main.convertDateYMD(nowDate)) > 0)
       continue;
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
