import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Boostrap {


    public static void main(String [ ] args) {
        int choice = -1;
        String input;
        HashMap<String, Course> mapCatalog = new HashMap<String, Course>();

        Scanner scanner = new Scanner(System.in);


        while(choice != 0){
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                //USE HASHMAP SO EASY REPLACE OF EXISTING.
                //Loads list of availible courses,replace any existing
                case 1:
                    System.out.println("enter file name");
                    input = scanner.nextLine();
                    File file = new File(input);

                    try {
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);

                        String stringLine = "";
                        String splitArray[];
                        String splitPrereq[];

                        while((stringLine = br.readLine()) != null){
                            System.out.println("line is "+stringLine);
                            splitPrereq = null;

                            splitArray = stringLine.split(",");

//                            would be part of badly formatted input
//                            for(int i=0; i<splitArray.length; i++){
//                                splitArray[i].trim();
//                            }

                            if(checkValidInput(splitArray)){
                                if(splitArray.length >3){
                                    splitPrereq = splitArray[3].split(":");
                                }
                                //TODO will be writing to file as object, need to have courses link together properly.
                                Course temp = new Course(splitArray[0],Double.parseDouble(splitArray[1]), splitArray[2], splitPrereq);
                                //HashMap will automatically overwrite previous instance of course code.


                                mapCatalog.put(splitArray[0],temp);
                            }
                            else{
                                System.out.println("Line \"" + stringLine + "\" was not added");
                            }

                        }
                        fr.close();
                    }catch (FileNotFoundException e) {
                        System.out.println("File Not Found");
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                 //Loads list of required courses, replace any existing representations of degree
                case 2:



                    break;
                 // Save current state.
                case 3:
                   // mapCatalog.forEach(());

                    break;

                case 4:
                    System.exit(0);

            }

        }
    }

    //capitalize coursecode
    public static boolean checkValidInput(String in[]){

        if(!in[0].matches("\\D{3,4}\\*\\d{4}")){
            System.out.println("Course Code is Invalid");
            return false;
        }
        if(Double.parseDouble(in[1]) > 1 || Double.parseDouble(in[1]) <= 0){
            System.out.println("Credit is out of bounds");
            return false;
        }

        return true;
    }

    public static void printMenu(){
        System.out.println("1: Read File");
        System.out.println("2: Read Require Courses for Requested Degree");
        System.out.println("3: Save");
        System.out.println("4: Exit");
    }


}
