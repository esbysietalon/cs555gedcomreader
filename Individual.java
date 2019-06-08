//JMJ

/**
 *
 * @author Nick Marzullo
 */
public class Individual {
    public String id;
    public String name;
    public String sex;
    public String birthDate;
    public String deathDate;
    public String childIn;
    public String spouseIn;

    public Individual(String newId, String newName, String newSex, String newBirthDate, String newDeathDate, String newChildIn, String newSpouseIn){
      id = newId;
      name = newName;
      sex = newSex;
      birthDate = newBirthDate;
      deathDate = newDeathDate;
      childIn = newChildIn;
      spouseIn = newSpouseIn;
    }
}
