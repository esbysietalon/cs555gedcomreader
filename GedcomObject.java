
import java.util.Objects;


/**
 * Represents an entity created from a GGEDCOM file.
 * Currently extended by Individual and Family
 * @author Jose Talon
 */

public abstract class GedcomObject{

    private String id;

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
    
    /**
     * Checks to see if two GedcomObjects have the same ID
     * @param obj Object to compare against this object
     * @return true if obj is a GedcomObject with the same ID
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof GedcomObject && ((GedcomObject)obj).getId().equals(this.id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }
}