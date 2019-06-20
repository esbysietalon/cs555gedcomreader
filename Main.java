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
    Tag parent;

    public Tag(){
        children = new ArrayList<>();
    }

    @Override
    public String toString(){
        String output = tag + " ";
        for(int i = 0; i < arguments.length; i++){
            output += arguments[i] + " ";
        }
        output += '\n';
        for(int i = 0; i < children.size(); i++){
            output += '\t' + children.get(i).toString() + '\n';
        }
        return output;
    }
}

class SortById implements Comparator<GedcomObject>{
    @Override
    public int compare(GedcomObject a, GedcomObject b){
        return a.getId().compareTo(b.getId());
    }
}

/**
 * 
 * The Assignment
 * 
 * @author Jose Joaquin Talon
 * @author Nick Marzullo
 * @author Eli Weinberger
 */
public class Main {
    private static final String[][] VALID_TAGS = {{"HEAD", "TRLR", "INDI", "FAM", "NOTE"}, 
                                                {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"}, 
                                                {"DATE"}};
    
    private static BufferedReader reader;
    private static ArrayList<String> lines;
    private static ArrayList<String[]> toParse;
    private static ArrayList<Tag> parsedTags;
    private static ArrayList<Individual> individuals;
    private static ArrayList<Family> families;

    public static void main(String[] args) {

        //Check to see if a GEGCOM File was provided
        if (args.length < 1) {
            System.err.println("Error: please specify a file name/path.");
            return;
        }

        //Read data from GEDCOM file
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            lines = new ArrayList<>();
            toParse = new ArrayList<>();
            parsedTags = new ArrayList<>();
            individuals = new ArrayList<>();
            families = new ArrayList<>();

            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                lines.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //Filter useless data
        checkLines(false);
        //Store useful data from the GEDCOM file
        addPeople();

        Collections.sort(individuals, new SortById());
        Collections.sort(families, new SortById());

        printPeople(individuals);
        printFamilies(families);
        
        //Call User stories here
        //US01
        /*System.out.print("US01: ");
        ArrayList<Tag> invalidDates = US01.futureDates(parsedTags);
        if(invalidDates.size() > 0) {
            System.out.println("WARNING: The following dates/events happen in the future");
            for (Tag t : invalidDates) {
                System.out.println(t + " from  " + t.parent + " in " + t.parent.parent);
            }
        }else{
            System.out.println("No Problems");
        }*/
        //US02
        //etc.
        
        //US17
        System.out.print("US17: ");
        ArrayList<Individual> creeps = US17.findParentsMarriedToChildren(individuals, families);
        if (creeps.size() > 0) {
            System.out.println("WARNING: The following individuals married one of their own children");
            printPeople(creeps);
        } else {
            System.out.println("No Problems");
        }
        //US22
        System.out.print("US22: ");
        ArrayList<GedcomObject> duplicates = US22.uniqueIDs(new ArrayList<>(individuals));
        boolean duplicateIndividuals = false;
        if(duplicates.size() > 0){
            System.out.println("ERROR: The following individuals have duplicate IDs");
            duplicateIndividuals = true;
            ArrayList<Individual> iDuplicates = new ArrayList<>();
            for(int i = 0; i < duplicates.size(); i++) {
                for(int j = 0; j < individuals.size(); j++) {
                    if(duplicates.get(i).equals(individuals.get(j))) {
                        iDuplicates.add(individuals.get(j));
                    }
                }
            }
            printPeople(iDuplicates);
        } 
        duplicates = US22.uniqueIDs(new ArrayList<>(families));
        if(duplicates.size() > 0) {
            System.out.println("ERROR: The following Families have duplicate IDs");
            ArrayList<Family> fDuplicates = new ArrayList<>();
            for(int i = 0; i < duplicates.size(); i++) {
                for(int j = 0; j < families.size(); j++) {
                    if(duplicates.get(i).equals(families.get(j))) {
                        fDuplicates.add(families.get(j));
                    }
                }
            }
            printFamilies(fDuplicates);
        } else if (!duplicateIndividuals) {
            System.out.println("No Problems");
        }
        
    }

    /**
     * Converts dates from format used in GEDCOM files to YYYY-MM-DD
     * @param date Date as stored in GEDCOM file
     * @return String containing date in YYYY-MM-DD format
     */
    public static String convertDateYMD(String date){
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        String[] args = date.split(" ", -1);
        
        if(args.length != 3){
            return date;
        }
        
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
        }else{
            args[1] = Integer.toString(month);
        }

        if(args[0].length() < 2){
            args[0] = "0" + args[0];
        }

        
        for(int i = 0; i < args.length; i++){
            out[i] = args[args.length - 1 - i];
        }
        return String.join("-", out);
    }
    public static double getDateDistance(String date1, String date2){
        String[] thenData = date1.split("-", -1);
        String[] nowData = date2.split("-", -1);

        int y1 = Integer.parseInt(thenData[0]);
        int y2 = Integer.parseInt(nowData[0]);
        int m1 = Integer.parseInt(thenData[1]);
        int m2 = Integer.parseInt(nowData[1]);
        int d1 = Integer.parseInt(thenData[2]);
        int d2 = Integer.parseInt(nowData[2]);

        double out = y2 - y1 + (m2 - m1) / 12.0 + (d2 - d1) / 365.0;

        return out;
    }

    private static void printPeople(ArrayList<Individual> indis){
        System.out.println("Individuals");
        String[] headers = {"ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse"};
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        int[] longestEntry = new int[headers.length];
        for(int i = 0; i < headers.length; i++){
            columns.add(new ArrayList<>());
            longestEntry[i] = headers[i].length();
        }
        for(Individual i : indis){
            columns.get(0).add(i.getId());
            if(i.getId().length() > longestEntry[0]){
                longestEntry[0] = i.getId().length();
            }
            columns.get(1).add(i.getName());
            if(i.getName().length() > longestEntry[1]){
                longestEntry[1] = i.getName().length();
            }
            columns.get(2).add(i.getSex());
            if(i.getSex().length() > longestEntry[2]){
                longestEntry[2] = i.getSex().length();
            }
            columns.get(3).add(convertDateYMD(i.getBirthDate()));
            if(convertDateYMD(i.getBirthDate()).length() > longestEntry[3]){
                longestEntry[3] = convertDateYMD(i.getBirthDate()).length();
            }
            columns.get(4).add(Integer.toString(i.getAge()));
            if(Integer.toString(i.getAge()).length() > longestEntry[4]){
                longestEntry[4] = Integer.toString(i.getAge()).length();
            }
            columns.get(5).add((i.isAlive() ? "True" : "False"));
            if((i.isAlive() ? "True" : "False").length() > longestEntry[5]){
                longestEntry[5] = (i.isAlive() ? "True" : "False").length();
            }
            columns.get(6).add(convertDateYMD(i.getDeathDate()));
            if(convertDateYMD(i.getDeathDate()).length() > longestEntry[6]){
                longestEntry[6] = convertDateYMD(i.getDeathDate()).length();
            }

            String childrenstr;
            if(i.getFAMCs().isEmpty()){
                childrenstr = "NA";
            } else {
                childrenstr = i.getFAMCs().toString();
            }
            columns.get(7).add(childrenstr);
            if(childrenstr.length() > longestEntry[7]){
                longestEntry[7] = childrenstr.length();
            }

            String spousestr;
            if(i.getFAMSs().size() == 0){
                spousestr = "NA";
            }else {
                spousestr = i.getFAMSs().toString();
            }
            columns.get(8).add(spousestr);
            if(spousestr.length() > longestEntry[7]){
                longestEntry[8] = spousestr.length();
            }
        }

        for(int i = 0; i < longestEntry.length; i++){
            longestEntry[i] += 4;
        }

        for(int i = 0; i < headers.length; i++){
            String padded = headers[i];
            while(padded.length() < longestEntry[i]){
                padded += " ";
                if(padded.length() == longestEntry[i])
                    break;
                padded = " " + padded;
            }
            System.out.print(padded);
        }
        System.out.println();
        for(int j = 0; j < indis.size(); j++) {
            for (int i = 0; i < headers.length; i++) {
                String padded = columns.get(i).get(j);
                while(padded.length() < longestEntry[i]){
                    padded += " ";
                    if(padded.length() == longestEntry[i])
                        break;
                    padded = " " + padded;
                }
                System.out.print(padded);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static void printFamilies(ArrayList<Family> fams) {
        System.out.println("Families");
        String[] headers = {"ID", "Married", "Divorced", "Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children"};
        ArrayList<ArrayList<String>>columns = new ArrayList<>();
        int[] longestEntry = new int[headers.length];
        for(int i = 0; i < headers.length; i++){
            columns.add(new ArrayList<>());
            longestEntry[i] = headers[i].length();
        }
        for(Family f : fams){
            columns.get(0).add(f.getId());
            if(f.getId().length() > longestEntry[0]){
                longestEntry[0] = f.getId().length();
            }

            columns.get(1).add(convertDateYMD(f.getMarriageDate()));
            if(convertDateYMD(f.getMarriageDate()).length() > longestEntry[1]){
                longestEntry[1] = convertDateYMD(f.getMarriageDate()).length();
            }

            columns.get(2).add(convertDateYMD(f.getDivorceDate()));
            if(convertDateYMD(f.getDivorceDate()).length() > longestEntry[2]){
                longestEntry[2] = convertDateYMD(f.getDivorceDate()).length();
            }

            columns.get(3).add(f.getHusbandId());
            if(f.getHusbandId().length() > longestEntry[3]){
                longestEntry[3] = f.getHusbandId().length();
            }

            columns.get(4).add(f.getHusbandName());
            if(f.getHusbandName().length() > longestEntry[4]){
                longestEntry[4] = f.getHusbandName().length();
            }

            columns.get(5).add(f.getWifeId());
            if(f.getWifeId().length() > longestEntry[5]){
                longestEntry[5] = f.getWifeId().length();
            }

            columns.get(6).add(f.getWifeName());
            if(f.getWifeName().length() > longestEntry[6]){
                longestEntry[6] = f.getWifeName().length();
            }

            String childrenstr;
            if(f.getChildrenIds().isEmpty()){
                childrenstr = "NA";
            } else {
                childrenstr = f.getChildrenIds().toString();
            }
            columns.get(7).add(childrenstr);
            if(childrenstr.length() > longestEntry[7]){
                longestEntry[7] = childrenstr.length();
            }
        }

        for(int i = 0; i < longestEntry.length; i++){
            longestEntry[i] += 4;
        }

        for(int i = 0; i < headers.length; i++){
            String padded = headers[i];
            while(padded.length() < longestEntry[i]){
                padded += " ";
                if(padded.length() == longestEntry[i])
                    break;
                padded = " " + padded;
            }
            System.out.print(padded);
        }
        System.out.println();
        for(int j = 0; j < fams.size(); j++) {
            for (int i = 0; i < headers.length; i++) {
                String padded = columns.get(i).get(j);
                while(padded.length() < longestEntry[i]){
                    padded += " ";
                    if(padded.length() == longestEntry[i])
                        break;
                    padded = " " + padded;
                }
                System.out.print(padded);
            }
            System.out.println();
        }
        System.out.println();
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
                            indi.getFAMCs().add(c.arguments[0]);
                            break;
                        case "FAMS":
                            indi.getFAMSs().add(c.arguments[0]);
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

                        case "MARR":
                            if(c.children.size() > 0)
                                fam.setMarriageDate(String.join(" ", c.children.get(0).arguments));
                            else
                                fam.setMarriageDate("NA");
                            break;
                        case "DIV":
                            if(c.children.size() > 0)
                                fam.setDivorceDate(String.join(" ", c.children.get(0).arguments));
                            else
                                fam.setDivorceDate("NA");
                            break;
                        case "HUSB":
                            fam.setHusbandId(String.join(" ", c.arguments));
                            for(Tag rec : parsedTags){
                                if(rec.arguments[0].equals(fam.getHusbandId())){
                                    for(Tag cc : rec.children){
                                        //System.out.println(cc.tag);
                                        if(cc.tag.equals("NAME")){
                                            fam.setHusbandName(String.join(" ", cc.arguments));
                                        }
                                    }
                                }
                            }
                            break;
                        case "WIFE":
                            fam.setWifeId(String.join(" ", c.arguments));
                            for(Tag rec : parsedTags){
                                if(rec.arguments[0].equals(fam.getWifeId())){
                                    for(Tag cc : rec.children){
                                        //System.out.println(cc.tag);
                                        if(cc.tag.equals("NAME")){
                                            fam.setWifeName(String.join(" ", cc.arguments));
                                        }
                                    }
                                }
                            }
                            break;
                        case "CHIL":
                            fam.getChildrenIds().add(String.join(" ", c.arguments).trim());
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
                for (String s : VALID_TAGS[level]) {
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
                newtag.tag = tag.trim();
                newtag.parent = null;
                newtag.arguments = arguments.split(" ", -1);
                if(level > 0){
                    if(lastTag[level-1] != null){
                        lastTag[level-1].children.add(newtag);
                        newtag.parent = lastTag[level-1];
                    }
                }
                lastTag[level] = newtag;
                parsedTags.add(newtag);
            }
        }
    }

    
    /*private static void storeIndividuals(){
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
    }*/
}
