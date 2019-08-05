import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class US34{
  public static ArrayList<Individual> listLargeAgeDiffs(ArrayList<Family> families){
    ArrayList<Individual> largeAgesDiffs = new ArrayList<>();
    Individual husband;
    Individual wife;
    int husbandAge;
    int wifeAge;
    int husbandAgeWhenMarried;
    int wifeAgeWhenMarried;

    for (Family f : families){
      if(f.getMarriageDate() != "-"){
        husband = (Individual) Main.getById(f.getHusbandId());
        wife = (Individual) Main.getById(f.getWifeId());
        husbandAge = husband.getAgeInDays();
        wifeAge = wife.getAgeInDays();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowDate = dateFormat.format(date);
        String[] nowdate = nowDate.split("-", -1);
        int nowyear = Integer.parseInt(nowdate[0]);
        int nowmonth = Integer.parseInt(nowdate[1]);
        int nowday = Integer.parseInt(nowdate[2]);
        int nowDays = nowday + (nowmonth * 30) + (nowyear * 365);

        String marriedDate = f.getMarriageDate();
        String marriedDateFormatted = Main.convertDateYMD(marriedDate);
        String[] marryDate = marriedDateFormatted.split("-", -1);
        int marryyear = Integer.parseInt(marryDate[0]);
        int marrymonth = Integer.parseInt(marryDate[1]);
        int marryday = Integer.parseInt(marryDate[2]);
        int marrydays = marryday + (marrymonth * 30) + (marryyear * 365);

        husbandAgeWhenMarried = husband.getAgeInDays() - (nowDays - marrydays);
        wifeAgeWhenMarried = wife.getAgeInDays() - (nowDays - marrydays);


        if((wifeAgeWhenMarried * 2 < husbandAgeWhenMarried) && !largeAgesDiffs.contains(husband)){
          largeAgesDiffs.add(husband);
        }else if((husbandAgeWhenMarried * 2 < wifeAgeWhenMarried) && !largeAgesDiffs.contains(wife)){
          largeAgesDiffs.add(wife);
        }
      }
    }
    return largeAgesDiffs;
  }
}
