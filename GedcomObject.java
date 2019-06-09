
/**
 * Represents an entity created from a GGEDCOM file.
 * Currently extended by Individual and Family
 * @author Jose Talon
 */

public abstract class GedcomObject{

    /**
     * Id of this entity as stored in the GEDCOM file
     */
    protected String id;

    /**
     * Default constructor creates GEDCOM Object with no ID. 
     * Use this when you want to supply the data later.
     */
    protected GedcomObject(){
        id = "";
    }

    /**
     * Creates a new GEDCOM object with an ID
     * @param id String read from GEDCOM File representing the ID of this object
     */
    protected GedcomObject(String id){
        this.id = id;
    }
    
    /**
     * @return Id of this entity as stored in the GEDCOM file
     */
    public String getId(){
        return id;
    }
    /**
     * @param id String read from GEDCOM File representing the ID of this object
     */
    public void setId(String id){
        this.id = id.trim();
    }
    
    /**
     * Returns a string representation of this object
     * @return id
     */
    @Override
    public String toString() {
        return id;
    }
}