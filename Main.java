//I pledge my honor that I have abided by the Stevens Honor System
//Jose Joaquin Talon

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

public class Main {
    private static BufferedReader reader;
    private static ArrayList<String> lines;
    private static ArrayList<String[]> toParse;
    private static String[] zerotags = {"HEAD", "TRLR", "INDI", "FAM", "NOTE"};
    private static String[] onetags = {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"};
    private static String[] twotags = {"DATE"};
    private static ArrayList<String[]> validtags;
    private static ArrayList<Tag> parsedTags;


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
        for(Tag t : parsedTags){
            System.out.println(t);
        }
        //Test to see if my constructors work right
        //storeIndividuals();
        //storeFamilies();

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
