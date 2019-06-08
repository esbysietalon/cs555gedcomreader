//I pledge my honor that I have abided by the Stevens Honor System
//Jose Joaquin Talon

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Tag{
    String tag;
    String[] arguments;
    ArrayList<Tag> children;
    public Tag(){
        children = new ArrayList<Tag>();
    }
    public String toString(){
        String output = tag + " ";
        for(int i = 0; i < arguments.length; i++){
            output += arguments[i] + " ";
        }
        output += '\n';
        for(int i = 0; i < children.size(); i++){
            output += '\t' + children.get(i).toString() + '\n';
        }
        //output += '\n';
        return output;
    }
}

class SortById implements Comparator<GedcomObject>{
    public int compare(GedcomObject a, GedcomObject b){
        return a.getId().compareTo(b.getId());
    }
}

public class Main {
    private static BufferedReader reader;
    private static ArrayList<String> lines;
    private static ArrayList<String[]> toParse;
    private static String[] zerotags = {"HEAD", "TRLR", "INDI", "FAM", "NOTE"};
    private static String[] onetags = {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"};
    private static String[] twotags = {"DATE"};
    private static ArrayList<String[]> validtags;
    private static ArrayList<Tag> parsedTags;
    private static ArrayList<Individual> individuals;
    private static ArrayList<Family> families;

    public static void main(String[] args) {


        if (args.length < 1) {
            System.err.println("Error: please specify a file name/path.");
            return;
        }

        try {
            reader = new BufferedReader(new FileReader(args[0]));
            lines = new ArrayList<>();
            toParse = new ArrayList<>();
            validtags = new ArrayList<>();
            parsedTags = new ArrayList<>();
            individuals = new ArrayList<>();
            families = new ArrayList<>();

            validtags.add(zerotags);
            validtags.add(onetags);
            validtags.add(twotags);

            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                lines.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        checkLines(false);
        // write your code here
        //Test to see if my constructors work right
        //storeIndividuals();
        //storeFamilies();
        addPeople();

        Collections.sort(individuals, new SortById());
        Collections.sort(families, new SortById());

        printPeople();
    }

    private static String convertDateYMD(String date){
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"}
        String[] args = date.split(" ", -1);
        String[] out = new String[3];

        int month = -1;
        for(int i = 0; i < months.length; i++){
            if(args[1].equals(months[i])){
                month = i+1;
                break;
            }
        }

        if(month < 10){
            args[1] = "0" + month;
        }

        if(args[0].length() < 2){
            args[0] = "0" + args[0];
        }


        for(int i = 0; i < args.length; i++){
            out[i] = args[args.length - 1 - i];
        }
        return String.join("-", out);
    }

    private static void printPeople(){
        System.out.println("Individuals");
        ArrayList<String>[] columns = new ArrayList<>[9];
        for(int i = 0; i < columns.length; i++){
            columns[i] = new ArrayList<>();
        }
        for(Individual i : individuals){
            columns[0].add(i.getId());
            columns[1].add(i.getName());
            columns[2].add(i.getSex());
            columns[3].add(i.getBirthDate());
            columns[4].add(i.getAge());
            columns[5].add(i.getAlive());
        }
    }

    private static void addPeople(){
        for(Tag t : parsedTags){
            if(t.tag.equals("INDI")){
                Individual indi = new Individual();
                indi.setId(t.arguments[0]);
                for(Tag c : t.children){
                    switch(c.tag){
                        case "NAME":
                           indi.setName(String.join(" ", c.arguments));
                            break;
                        case "SEX":
                            indi.setSex(c.arguments[0]);
                            break;
                        case "DEAT":
                            indi.setDeathDate(String.join(" ", c.children.get(0).arguments));
                            break;
                        case "BIRT":
                            indi.setBirthDate(String.join(" ", c.children.get(0).arguments));
                            break;
                        case "FAMC":
                            indi.setChildIn(c.arguments[0]);
                            break;
                        case "FAMS":
                            indi.setSpouseIn(c.arguments[0]);
                            break;
                    }
                }
                individuals.add(indi);
            }
            if(t.tag.equals("FAM")){
                Family fam = new Family();
                fam.setId(t.arguments[0]);
                for(Tag c : t.children){
                    switch(c.tag){
                        /*
                        * private String marriageDate;
                          private String divorceDate;
                          private String husbandId;
                          private String husbandName;
                          private String wifeId;
                          private String wifeName;
                          private ArrayList<String> ChildrenIds = new ArrayList<>();
                        * */
                        case "MARR":
                            if(c.children.size() > 0)
                                fam.setMarriageDate(String.join(" ", c.children.get(0).arguments));
                            else
                                fam.setMarriageDate("N/A");
                            break;
                        case "DIV":
                            if(c.children.size() > 0)
                                fam.setDivorceDate(String.join(" ", c.children.get(0).arguments));
                            else
                                fam.setDivorceDate("N/A");
                            break;
                        case "HUSB":
                            fam.setHusbandId(String.join(" ", c.arguments));
                            for(Tag cc : c.children){
                                if(cc.tag.equals("NAME")){
                                    fam.setHusbandName(String.join(" ", cc.arguments));
                                }
                            }
                            break;
                        case "WIFE":
                            fam.setWifeId(String.join(" ", c.arguments));
                            for(Tag cc : c.children){
                                if(cc.tag.equals("NAME")){
                                    fam.setWifeName(String.join(" ", cc.arguments));
                                }
                            }
                            break;
                        case "CHIL":
                            fam.getChildrenIds().add(String.join(" ", c.arguments));
                            break;
                    }
                }
                families.add(fam);
            }

        }
    }

    private static void checkLines(boolean withPrinting) {
        Tag[] lastTag = new Tag[3];
        for(int i = 0; i < 3; i++){
            lastTag[i] = null;
        }

        for (int i = 0; i < lines.size(); i++) {
            if(withPrinting)
               System.out.println("--> " + lines.get(i));
            toParse.add(lines.get(i).split(" ", -1));
            String arguments = "";
            int level = -1;
            boolean valid = true;
            String prelevel = toParse.get(toParse.size() - 1)[0];

            try {
                level = Integer.parseInt(prelevel);
                if (level < 0 || level > 2) {
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                level = -1;
                valid = false;
            }

            String tag = toParse.get(toParse.size() - 1)[1];
            int argStart = 2;

            if(tag.equals("INDI") || tag.equals("FAM")){
                valid = false;
            }

            if (toParse.get(toParse.size() - 1).length > 2) {
                if (toParse.get(toParse.size() - 1)[2].equals("INDI") || toParse.get(toParse.size() - 1)[2].equals("FAM")) {
                    tag = toParse.get(toParse.size() - 1)[2];
                    arguments = toParse.get(toParse.size() - 1)[1] + " ";
                    argStart = 3;
                    if (toParse.get(toParse.size() - 1).length > 3) {
                        valid = false;
                    }
                }
                for (int j = argStart; j < toParse.get(toParse.size() - 1).length; j++) {
                    arguments += toParse.get(toParse.size() - 1)[j] + " ";
                }
            }

            if (valid) {
                boolean inTags = false;
                for (String s : validtags.get(level)) {
                    if (s.equals(tag))
                        inTags = true;
                }
                if (!inTags)
                    valid = false;
            }

            if(withPrinting)
                System.out.println("<-- " + level + "|" + tag + "|" + (valid ? "Y" : "N") + "|" + arguments);

            if(valid){
                Tag newtag = new Tag();
                newtag.tag = tag;
                newtag.arguments = arguments.split(" ", -1);
                if(level > 0){
                    if(lastTag[level-1] != null){
                        lastTag[level-1].children.add(newtag);
                    }
                }
                lastTag[level] = newtag;
                parsedTags.add(newtag);
            }
        }
    }

    private static void storeIndividuals(){
      //Test to see if my constructors work right
      Individual testIndi = new Individual("test", "test", "test", "test", "test", "test", "test");
      System.out.println(testIndi);
    }

    private static void storeFamilies(){
      //Test to see if my constructors work right
      ArrayList<String> children = new ArrayList<>();
      children.add("test");
      Family testFam = new Family("test", "test", "test", "test", "test", "test", "test", children);
      System.out.println(testFam);
    }
}
