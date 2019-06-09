//JMJ

/**
 * This class stores the data for an individual person
 * @author Nick Marzullo
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Individual extends GedcomObject{
    private String name;
    private String sex;
    private String birthDate;
    private String deathDate;
    private String FAMC;
    private String FAMS;

    /**
     * Empty constructor
     * Use this when you plan on filling in the rest of the data later
     */
    public Individual(){
        super();
        name = "";
        sex = "";
        birthDate = "NA";
        deathDate = "NA";
        FAMC = "NA";
        FAMS = "NA";
    }

    /**
     * @return Name of individual
     */
    public String getName(){
        return name;
    }
    /**
     * @param name Name of individual
     */
    public void setName(String name){
        this.name = name.trim();
    }
    
    /**
     * @return "M" or "F" representing the individual's sex
     */
    public String getSex(){
        return sex;
    }
    /**
     * @param sex "M" or "F" representing the individual's sex
     */
    public void setSex(String sex){
        this.sex = sex.trim();
    }

    /**
     * @return Birth Date as stored in the GEDCOM file
     */
    public String getBirthDate(){
        return birthDate;
    }
    /**
     * @param birthDate Birth Date read directly from GEDCOM file
     */
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate.trim();
    }

    /**
     * @return Date of Death as stored in the GEDCOM file
     */
    public String getDeathDate(){
        return deathDate;
    }
    /**
     * @param deathDate Date of Death read directly from GEDCOM file
     */
    public void setDeathDate(String deathDate){
        this.deathDate = deathDate.trim();
    }

    /**
     * @return The Id of the Family that contains this Individual as a child
     */
    public String getFAMC(){
        return FAMC;
    }
    /**
     * @param FAMC The Id of the Family that contains this Individual as a child 
     */
    public void setFAMC(String FAMC){
        this.FAMC = FAMC.trim();
    }

    /**
     * @return The Id of the Family that contains this Individual as either a Husband or Wife
     */
    public String getFAMS(){
        return FAMS;
    }
    /**
     * @param FAMS The Id of the Family that contains this Individual as either a Husband or Wife
     */
    public void setFAMS(String FAMS){
        this.FAMS = FAMS.trim();
    }


    /**
     * Calculates the age of the Individual in years. 
     * If the Individual is deceased, the age is calculated based on date of death, otherwise it is based on the current date
     * @return age in years 
     */
    public int getAge(){

        String newDate = Main.convertDateYMD(birthDate);
        String[] thendate = newDate.split("-", -1);
        int thenyear = Integer.parseInt(thendate[0]);
        int thenmonth = Integer.parseInt(thendate[1]);
        int thenday = Integer.parseInt(thendate[2]);

        int nowyear;
        int nowmonth;
        int nowday;
        if(isAlive()) {
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

    /**
     * Is the Individual alive?
     * @return true if there is no Death Date
     */
    public boolean isAlive(){
        return (deathDate.equals("NA"));
    }

    /**
     * Creates Individual based on input
     * 
     * @param id Id read directly from GEDCOM file
     * @param name Name of individual
     * @param sex "M" or "F" representing the individual's sex
     * @param birthDate Birth Date read directly from GEDCOM file
     * @param deathDate Death Date read directly from GEDCOM file
     * @param childIn The Id of the Family that contains this Individual as a child
     * @param spouseIn The Id of the Family that contains this Individual as either a Husband or Wife
     */
    public Individual(String id, String name, String sex, String birthDate, String deathDate, String childIn, String spouseIn){
      super(id);
      this.name = name;
      this.sex = sex;
      this.birthDate = birthDate;
      this.deathDate = deathDate;
      this.FAMC = childIn;
      this.FAMS = spouseIn;
    }
    
    /**
     * Returns a string representation of the Individual
     * @return id, name, sex, birthDate, deathDate, FAMC, and FAMS separated by tabs
     */
    @Override
    public String toString(){
        return super.toString() + "\t" + name + "\t" + sex + "\t" + birthDate + "\t" + deathDate + "\t" + FAMC + "\t" + FAMS;
    }
}
