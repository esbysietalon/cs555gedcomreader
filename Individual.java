//JMJ

/**
 * This class stores the data for an individual person
 * @author Nick Marzullo
 */
public class Individual {
    private String id;
    private String name;
    private String sex;
    private String birthDate;
    private String deathDate;
    private String childIn; //FAMC
    private String spouseIn; //FAMS

    public Individual(){
        id = "";
        name = "";
        sex = "";
        birthDate = "N/A";
        deathDate = "N/A";
        childIn = "N/A";
        spouseIn = "N/A";
    }

    public String getId(){
        return id;
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
    public String getChildIn(){
        return childIn;
    }
    public String getSpouseIn(){
        return spouseIn;
    }

    public void setId(String str){
        id = str;
    }
    public void setName(String str){
        name = str;
    }
    public void setSex(String str){
        sex = str;
    }
    public void setBirthDate(String str){
        birthDate = str;
    }
    public void setDeathDate(String str){
        deathDate = str;
    }
    public void setChildIn(String str){
        childIn = str;
    }
    public void setSpouseIn(String str){
        spouseIn = str;
    }


    public Individual(String newId, String newName, String newSex, String newBirthDate, String newDeathDate, String newChildIn, String newSpouseIn){
      id = newId;
      name = newName;
      sex = newSex;
      birthDate = newBirthDate;
      deathDate = newDeathDate;
      childIn = newChildIn;
      spouseIn = newSpouseIn;
    }
    
    @Override
    public String toString(){
        return id + " " + name + " " + sex + " " + birthDate + " " + deathDate + " " + childIn + " " + spouseIn;
    }
}
