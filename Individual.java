//JMJ

/**
 * This class stores the data for an individual person
 * @author Nick Marzullo
 */
public class Individual {
    public String id;
    public String name;
    public String sex;
    public String birthDate;
    public String deathDate;
    public String childIn; //FAMC
    public String spouseIn; //FAMS

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
