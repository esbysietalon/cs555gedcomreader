import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class US01{
    public static ArrayList<Tag> futureDates(ArrayList<Tag> tags){
        ArrayList<Tag> output = new ArrayList<Tag>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowDate = dateFormat.format(date);
        for(Tag t:tags){
            if(!t.tag.equals("DATE")){
                continue;
            }
            String thenDate = Main.convertDateYMD(String.join(" ", t.arguments).trim());
            //System.out.println(thenDate);
            if(Main.getDateDistance(thenDate, nowDate) <= 0){
                output.add(t);
            }
        }
        return output;
    }
}