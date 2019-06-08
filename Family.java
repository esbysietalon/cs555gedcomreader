//JMJ


import java.util.*;

/**
 * This this class stores the data for an individual family
 * @author Nick Marzullo
 */
public class Family {
    private String id;
    private String marriageDate;
    private String divorceDate;
    private String husbandId;
    private String husbandName;
    private String wifeId;
    private String wifeName;
    private ArrayList<String> ChildrenIds = new ArrayList<>();

    public Family(){

    }

    public String getId(){
        return id;
    }
    public String getMarriageDate(){
        return marriageDate;
    }
    public String getDivorceDate(){
        return divorceDate;
    }
    public String getHusbandId(){
        return husbandId;
    }
    public String getHusbandName(){
        return husbandName;
    }

    public String getWifeId() {
        return wifeId;
    }

    public String getWifeName() {
        return wifeName;
    }

    public ArrayList<String> getChildrenIds() {
        return ChildrenIds;
    }

    public void setChildrenIds(ArrayList<String> childrenIds) {
        ChildrenIds = childrenIds;
    }

    public void setDivorceDate(String divorceDate) {
        this.divorceDate = divorceDate;
    }

    public void setHusbandId(String husbandId) {
        this.husbandId = husbandId;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate;
    }

    public void setWifeId(String wifeId) {
        this.wifeId = wifeId;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

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
