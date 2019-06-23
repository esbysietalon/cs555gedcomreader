//50 mins, solo

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class US01{
    public static USOutput futureDates(ArrayList<Tag> tags){
        ArrayList<Individual> outputIndi = new ArrayList<>();
        ArrayList<Family> outputFam = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowDate = dateFormat.format(date);
        for(Tag t:tags){
            if(!t.tag.equals("DATE")){
                continue;
            }
            String thenDate = Main.convertDateYMD(String.join(" ", t.arguments).trim());
            if(Main.getDateDistance(thenDate, nowDate) <= 0){
                if(t.parent != null && t.parent.parent != null)
                    if(t.parent.parent.tag.equals("FAM"))    
                        outputFam.add((Family)Main.getById(t.parent.parent.arguments[0]));
                    else if(t.parent.parent.tag.equals("INDI"))
                        outputIndi.add((Individual)Main.getById(t.parent.parent.arguments[0]));
            }
        }
        USOutput output = new USOutput(outputIndi, outputFam);
        return output;
    }
}