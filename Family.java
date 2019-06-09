//JMJ

import java.util.*;

/**
 * The Family class is a representation of a family stored in a GEDCOM file
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

    /**
     * Empty constructor
     * Use this when you plan on filling in the rest of the data later
     */
    public Family(){
        super();
        marriageDate = "-";
        divorceDate = "NA";
        husbandId = "";
        husbandName = "!!!";
        wifeId = "";
        wifeName = "!!!";
    }

    /**
     * @return Date of Marriage
     */
    public String getMarriageDate(){
        return marriageDate;
    }
    /**
     * @param marriageDate Date of Marriage
     */
    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate.trim();
    }

    /**
     * @return Date of Divorce
     */
    public String getDivorceDate(){
        return divorceDate;
    }
    /**
     * @param divorceDate Date of Divorce
     */
    public void setDivorceDate(String divorceDate) {
        this.divorceDate = divorceDate.trim();
    }

    /**
     * @return Individual Id of Husband
     */
    public String getHusbandId(){
        return husbandId;
    }
    /**
     * @param husbandId Individual Id of Husband
     */
    public void setHusbandId(String husbandId) {
        this.husbandId = husbandId.trim();
    }

    /**
     * @return Name of Husband
     */
    public String getHusbandName(){
        return husbandName;
    }
    /**
     * @param husbandName Name of Husband. There are currently no checks to make sure that it matches the name attached to the ID
     */
    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName.trim();
    }

    /**
     * @return Individual Id of Wife
     */
    public String getWifeId() {
        return wifeId;
    }
    /**
     * @param wifeId Individual Id of Husband
     */
    public void setWifeId(String wifeId) {
        this.wifeId = wifeId.trim();
    }

    /**
     * @return Name of Wife
     */
    public String getWifeName() {
        return wifeName;
    }
    /**
     * @param wifeName Name of Wife. There are currently no checks to make sure that it matches the name attached to the ID
     */
    public void setWifeName(String wifeName) {
        this.wifeName = wifeName.trim();
    }

    /**
     * @return ArrayList containing the Individual IDs of all children in this family
     */
    public ArrayList<String> getChildrenIds() {
        return ChildrenIds;
    }
    /**
     * @param childrenIds ArrayList containing the Individual IDs of all children in this family
     */
    public void setChildrenIds(ArrayList<String> childrenIds) {
        ChildrenIds = childrenIds;
    }

    /**
     *  Creates a Family object based on input 
     * 
     * @param id Id read directly from GEDCOM file
     * @param marriageDate Date of Marriage 
     * @param divorceDate Date of Divorce
     * @param husbandId Individual Id of Husband
     * @param husbandName Name of Husband. There are currently no checks to make sure that it matches the name attached to the ID
     * @param wifeId Individual Id of Wife
     * @param wifeName Name of Wife. There are currently no checks to make sure that it matches the name attached to the ID
     * @param ChildrenIds ArrayList containing the Individual IDs of all children in this family
     */
    public Family(String id, String marriageDate, String divorceDate, String husbandId, String husbandName, String wifeId, String wifeName, ArrayList<String> ChildrenIds){
        super(id);
        this.marriageDate = marriageDate;
        this.divorceDate = divorceDate;
        this.husbandId = husbandId;
        this.husbandName = husbandName;
        this.wifeId = wifeId;
        this.wifeName = wifeName;
        this.ChildrenIds = ChildrenIds;
    }
    
    /**
     * Returns a string representation of the Family 
     * @return Id, Marriage Date, Divorce Date, Husband Id, Husband Name, Wife Id, Wife Name, and Children IDs separated by tabs
     */
    @Override
    public String toString() {
        return super.toString() + "\t" + marriageDate + "\t" + divorceDate + "\t" + husbandId + "\t" + husbandName + "\t" + wifeId + "\t" + wifeName + "\t" + ChildrenIds.toString() ;
    }
}
