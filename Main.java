//I pledge my honor that I have abided by the Stevens Honor System
//Jose Joaquin Talon

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static BufferedReader reader;
    private static ArrayList<String> lines;
    private static ArrayList<String[]> toParse;
    private static String[] zerotags = {"HEAD", "TRLR", "INDI", "FAM", "NOTE"};
    private static String[] onetags = {"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"};
    private static String[] twotags = {"DATE"};
    private static ArrayList<String[]> tags;

    public static void main(String[] args) {


        if(args.length < 1){
            System.out.println("Error: please specify a file name/path.");
            return;
        }

	    try{
            reader = new BufferedReader(new FileReader(args[0]));
            lines = new ArrayList<>();
            toParse = new ArrayList<>();
            tags = new ArrayList<>();
            tags.add(zerotags);
            tags.add(onetags);
            tags.add(twotags);

            String nextLine;
            while((nextLine = reader.readLine()) != null){
                lines.add(nextLine);
            }
	    }catch(IOException e){
	        e.printStackTrace();
	        return;
        }

	    checkLines();
	    // write your code here
    }
    private static void checkLines(){
        for(int i = 0; i < lines.size(); i++){
            System.out.println("--> "+lines.get(i));
            toParse.add(lines.get(i).split(" ", -1));
            String findings = "<-- ";
            int level = -1;
            boolean valid = true;
            for(int j = 0; j < toParse.get(toParse.size() - 1).length; j++){
                String word = toParse.get(toParse.size() - 1)[j];
                switch(j) {
                    case 0:
                        //level
                        int prelevel;
                        try{
                            prelevel = Integer.parseInt(word);
                        }catch(NumberFormatException nfe){
                            prelevel = -1;
                        }
                        if (prelevel < 0 || prelevel > 2) {
                            valid = false;
                            level = -1;
                        }else{
                            level = prelevel;
                        }
                        findings += word + "|";
                        break;
                    case 1:
                        //tag or id for indi/fam
                        if(valid){
                            boolean inTags = false;
                            for(String s : tags.get(level)){
                                if(s.equals(word)){
                                    if(s.equals("INDI") || s.equals("FAM")){
                                        valid = false;
                                    }
                                    inTags = true;
                                    break;
                                }
                            }
                            if(!inTags){
                                if(level == 0){
                                    if(j + 1 < toParse.get(toParse.size() - 1).length) {
                                        String nextTag = toParse.get(toParse.size() - 1)[j + 1];
                                        if(!(nextTag.equals("INDI") || nextTag.equals("FAM"))){
                                            valid = false;
                                        }
                                    }else{
                                        valid = false;
                                    }
                                }else{
                                    valid = false;
                                }
                            }
                        }
                        findings += word + "|" + (valid ? "Y" : "N") + "|";
                        break;
                    default:
                        findings += word + " ";
                        break;
                }

            }
            System.out.println(findings);
        }
    }
}
