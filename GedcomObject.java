//GEDCOM Object Class 

public class GedcomObject{
    private String id;

    public GedcomObject(){
        id = "";
    }
    public GedcomObject(String str){
        id = str;
    }

    public String getId(){
        return id;
    }
    public void setId(String str){
        id = str.trim();
    }
}