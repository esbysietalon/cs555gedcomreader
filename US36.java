import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class US36 {

  public static ArrayList<Individual> listRecentDeceased(ArrayList<Individual> individuals){
    ArrayList<Individual> recentDeadPeople = new ArrayList<>();
    ArrayList<Individual> allDeadPeople = US29.listDeceased(individuals);
    for(Individual i : allDeadPeople){
      if(isRecentDeath(i)){
        recentDeadPeople.add(i);
      }
    }
    return recentDeadPeople;
  }

  private static boolean isRecentDeath(Individual i){
    String newDate = Main.convertDateYMD(i.getDeathDate());
    String[] diedate = newDate.split("-", -1);
    int dieyear = Integer.parseInt(diedate[0]);
    int diemonth = Integer.parseInt(diedate[1]);
    int dieday = Integer.parseInt(diedate[2]);

    //Calculate total amount of days from year 0 to individuals birthdate;
    int dieDays = dieday + (diemonth * 30) + (dieyear * 365);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String nowDate = dateFormat.format(date);
    String[] nowdate = nowDate.split("-", -1);
    int nowyear = Integer.parseInt(nowdate[0]);
    int nowmonth = Integer.parseInt(nowdate[1]);
    int nowday = Integer.parseInt(nowdate[2]);

    //Calculate total amount of days from year 0 to nowday
    int nowDays = nowday + (nowmonth * 30) + (nowyear * 365);

    int daysSinceDeath = nowDays - dieDays;

    if (daysSinceDeath < 30){
      return true;
    }

    return false;
  }
}
