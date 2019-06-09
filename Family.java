//JMJ


import java.util.*;

/**
 * This this class stores the data for an individual family
 * @author Nick Marzullo
 */
public class Family extends GedcomObject{
    private String marriageDate;
    private String divorceDate;
    private String husbandId;
    private String husbandName;
    private String wifeId;
    private String wifeName;
    private ArrayList<String> ChildrenIds = new ArrayList<>();

    public Family(){
        super();
        marriageDate = "-";
        divorceDate = "NA";
        husbandId = "";
        husbandName = "!!!";
        wifeId = "";
        wifeName = "!!!";
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
        this.divorceDate = divorceDate.trim();
    }

    public void setHusbandId(String husbandId) {
        this.husbandId = husbandId.trim();
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName.trim();
    }


    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate.trim();
    }

    public void setWifeId(String wifeId) {
        this.wifeId = wifeId.trim();
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName.trim();
    }

    public Family(String newId, String newMarriageDate, String newDivorceDate, String newHusbandId, String newHusbandName, String newWifeId, String newWifeName, ArrayList<String> newChildrenIds){
        super(newId);
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
        return getId() + " " + marriageDate + " " + divorceDate + " " + husbandId + " " + husbandName + " " + wifeId + " " + wifeName + " " + ChildrenIds.toString() ;
    }
}
