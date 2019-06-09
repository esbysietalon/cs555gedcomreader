//JMJ

/**
 * This class stores the data for an individual person
 * @author Nick Marzullo
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Individual extends GedcomObject{
    private String name;
    private String sex;
    private String birthDate;
    private String deathDate;
    private ArrayList<String> childIn; //FAMC
    private ArrayList<String> spouseIn; //FAMS

    public Individual(){
        super();
        name = "";
        sex = "";
        birthDate = "NA";
        deathDate = "NA";
        childIn = new ArrayList<String>();
        spouseIn = new ArrayList<String>();
    }

    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public String getBirthDate(){
        return birthDate;
    }
    public String getDeathDate(){
        return deathDate;
    }
    public ArrayList<String> getChildIn(){
        return childIn;
    }
    public ArrayList<String> getSpouseIn(){
        return spouseIn;
    }

    public int getAge(){

        String newDate = Main.convertDateYMD(birthDate);
        String[] thendate = newDate.split("-", -1);
        int thenyear = Integer.parseInt(thendate[0]);
        int thenmonth = Integer.parseInt(thendate[1]);
        int thenday = Integer.parseInt(thendate[2]);

        int nowyear;
        int nowmonth;
        int nowday;
        if(getAlive()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
            Date date = new Date();
            String today = dateFormat.format(date);
            String[] nowdate = today.split(" ", -1);
            nowyear = Integer.parseInt(nowdate[0]);
            nowmonth = Integer.parseInt(nowdate[1]);
            nowday = Integer.parseInt(nowdate[2]);
        }else{
            String preDate = Main.convertDateYMD(deathDate);
            String[] nowdate = preDate.split("-", -1);
            nowyear = Integer.parseInt(nowdate[0]);
            nowmonth = Integer.parseInt(nowdate[1]);
            nowday = Integer.parseInt(nowdate[2]);
        }
        int age = nowyear - thenyear;
        if(thenmonth > nowmonth){
            age--;
        }else{
            if(thenday > nowday){
                age--;
            }
        }

        return age;
    }
    public boolean getAlive(){
        return (deathDate.equals("NA"));
    }

    public void setName(String str){
        name = str.trim();
    }
    public void setSex(String str){
        sex = str.trim();
    }
    public void setBirthDate(String str){
        birthDate = str.trim();
    }
    public void setDeathDate(String str){
        deathDate = str.trim();
    }
    public void setChildIn(ArrayList<String> arr){
        childIn = arr;
    }
    public void setSpouseIn(ArrayList<String> arr){
        spouseIn = arr;
    }


    public Individual(String newId, String newName, String newSex, String newBirthDate, String newDeathDate, ArrayList<String> newChildIn, ArrayList<String> newSpouseIn){
      super(newId);
      name = newName;
      sex = newSex;
      birthDate = newBirthDate;
      deathDate = newDeathDate;
      childIn = newChildIn;
      spouseIn = newSpouseIn;
    }
    
    @Override
    public String toString(){
        return getId() + " " + name + " " + sex + " " + birthDate + " " + deathDate + " " + childIn + " " + spouseIn;
    }
}
