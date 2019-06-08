//JMJ


import java.util.*;

/**
 * This this class stores the data for an individual family
 * @author Nick Marzullo
 */
public class Family {
    public String id;
    public String marriageDate;
    public String divorceDate;
    public String husbandId;
    public String husbandName;
    public String wifeId;
    public String wifeName;
    public ArrayList<String> ChildrenIds = new ArrayList<>();

    public Family(String newId, String newMarriageDate, String newDivorceDate, String newHusbandId, String newHusbandName, String newWifeId, String newWifeName, ArrayList<String> newChildrenIds){
      id = newId;
      marriageDate = newMarriageDate;
      divorceDate = newDivorceDate;
      husbandId = newHusbandId;
      husbandName = newHusbandName;
      wifeId = newWifeId;
      wifeName = newWifeName;
      ChildrenIds = newChildrenIds;
    }

    @Override
    public String toString() {
        return id + " " + marriageDate + " " + divorceDate + " " + husbandId + " " + husbandName + " " + wifeId + " " + wifeName + " " + ChildrenIds.toString() ;
    }
}
