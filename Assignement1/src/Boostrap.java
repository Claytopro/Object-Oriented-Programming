import java.io.*;
import java.util.*;

/**
 * A helper Application that parses in data from files and writes them so they may be used by planner application.
 */
public class Boostrap {


    public static void main(String[] args) {
        int choice = 0;
        String input;
        HashMap<String, Course> mapCatalog = new HashMap<String, Course>();
        HashMap<Course, ArrayList<String>> holdMap = new HashMap<Course, ArrayList<String>>();
        ArrayList<String> BCGcourses = new ArrayList<String>();
        ArrayList<String> CScourses = new ArrayList<String>();
        ArrayList<String> SEngcourses = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String stringLine = "";
        File file;

        while (true) {
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                //USE HASHMAP SO EASY REPLACE OF EXISTING.
                //Loads list of availible courses,replace any existing
                // assumation: the prerequisistes listed in file will exist. only have to deal with invalid data.
                //but still deal with invalud course codes in prequisistes list
                case 1:
                    System.out.println("enter file name");
                    input = scanner.nextLine();
                    file = new File(input);

                    try {
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);

                        stringLine = "";
                        String splitArray[];
                        boolean addToMap = true;

                        while ((stringLine = br.readLine()) != null) {

                            ArrayList<String> splitPrereq = new ArrayList<String>();
                            addToMap = true;

                            splitArray = stringLine.split(",");

                            if (splitArray.length > 3) {
                                Collections.addAll(splitPrereq, splitArray[3].split(":"));
                            }

                            if (checkValidInput(splitArray)) {
                                //TODO will be writing to file as object, need to have courses link together properly.
                                Course temp = new Course(splitArray[0], Double.parseDouble(splitArray[1]), splitArray[2]);
                                //HashMap will automatically overwrite previous instance of course code.
                                if (splitPrereq != null) {
                                    for (String s : splitPrereq) {
                                        if (mapCatalog.get(s) != null) {
                                            temp.addPrerequisite(mapCatalog.get(s));
                                        } else {
                                           // System.out.println("preReq " + s + " not availible.");
                                            holdMap.put(temp, splitPrereq);
                                            addToMap = false;
                                        }
                                    }
                                }
                                if (addToMap) {
                                    mapCatalog.put(splitArray[0], temp);
                                } else {
                                    //System.out.println("Line \"" + stringLine + "\" was added to HOLDMAP");
                                }
                            }else{
                                System.out.println("not adde splitArray:" + Arrays.toString(splitArray));
                            }
                        }

                        System.out.println("\n");
                        ArrayList<String> preReqs;
                        while (!holdMap.isEmpty()) {
                            for (Iterator<Map.Entry<Course, ArrayList<String>>> it = holdMap.entrySet().iterator(); it.hasNext(); ) {

                                Map.Entry<Course, ArrayList<String>> node = it.next();
                                Iterator<String> stringIterator = node.getValue().iterator();

                                //System.out.format("key: %s, value: %s%n", node.getKey(), node.getValue());
                                //adds prequisite to course in hold, if all prerequisites are added, move to mapCatalog.
                                while (stringIterator.hasNext()) {
                                    String s = stringIterator.next();
                                    if (node.getKey().addPrerequisite(mapCatalog.get(s))) {
                                       // System.out.println("successfully added " + s + " to " + node.getKey().getCourseCode());
                                        stringIterator.remove();

                                    } else {
                                       // System.out.println("could not find " + s);
                                    }
                                    //if Arraylist of prerequisites is empty then the object has all its prerequisites and is removed from holdMap.
                                    if (node.getValue().isEmpty()) {
                                     //   System.out.println(node.getKey() + " is empty adding to mapCatalog");
                                        mapCatalog.put(node.getKey().getCourseCode(), node.getKey());
                                        it.remove();
                                    }
                                }

                            }
                        }
                        System.out.println("\n");
                        for (Map.Entry<String, Course> node : mapCatalog.entrySet()) {
                            System.out.println(node.getValue());
                        }

                        fr.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File Not Found");
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                //Loads list of required courses, replace any existing representations of degree
                case 2:
                    System.out.println("enter file name");
                    input = scanner.nextLine();
                    file = new File(input);
                    try {
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);

                        stringLine = br.readLine();
                        String requiredCoures[] = stringLine.split(",");

                        if ((requiredCoures[0].trim()).equals("BCG")) {
                            BCGcourses.clear();
                            for (int i = 0; i < requiredCoures.length; i++) {
                                if(validCourseCode(requiredCoures[i].trim())){
                                    System.out.println("add " + requiredCoures[i]);
                                    BCGcourses.add(requiredCoures[i].trim());
                                }
                            }
                        }

                        if ((requiredCoures[0].trim()).equals("CS")) {
                            CScourses.clear();
                            for (int i = 0; i < requiredCoures.length; i++) {
                                if(validCourseCode(requiredCoures[i].trim())){
                                    System.out.println("add " + requiredCoures[i]);
                                    CScourses.add(requiredCoures[i].trim());
                                }
                            }
                        }

                        if ((requiredCoures[0].trim()).equals("SEng")) {
                            SEngcourses.clear();
                            for (int i = 0; i < requiredCoures.length; i++) {
                                if(validCourseCode(requiredCoures[i].trim())){
                                    System.out.println("add " + requiredCoures[i]);
                                    SEngcourses.add(requiredCoures[i].trim());
                                }
                            }
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("File Not Found");
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                // Save current state.
                case 3:
                    try {
                        //Dont have to use filewriter for this class!
                        FileOutputStream fileOut = new FileOutputStream("COURSECATALOG");
                        ObjectOutputStream ObjectOut = new ObjectOutputStream(fileOut);
                        //Course Catalog will turn hashmap into ArrayList
                        ObjectOut.writeObject(mapCatalog);

                        ObjectOut.close();
                        fileOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                    FileOutputStream fileOut = new FileOutputStream("CSarraylist");
                    ObjectOutputStream ObjectOut = new ObjectOutputStream(fileOut);
                    ObjectOut.writeObject(CScourses);
                    ObjectOut.close();
                    fileOut.close();
                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    } catch (IOException e) {
                    e.printStackTrace();
                    }

                    try {
                        FileOutputStream fileOut = new FileOutputStream("SENGarraylist");
                        ObjectOutputStream ObjectOut = new ObjectOutputStream(fileOut);
                        ObjectOut.writeObject(SEngcourses);
                        ObjectOut.close();
                        fileOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        FileOutputStream fileOut = new FileOutputStream("BCGarraylist");
                        ObjectOutputStream ObjectOut = new ObjectOutputStream(fileOut);
                        ObjectOut.writeObject(BCGcourses);
                        ObjectOut.close();
                        fileOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;

                case 4:
                    System.exit(0);

            }

        }
    }

    //TODO change to use enum for course code stuff
    public static boolean checkValidInput(String in[]) {
        if (!validCourseCode(in[0])) {
            System.out.println("Course Code:" + in[0] + ": is Invalid");
            return false;
        }
        if (Double.parseDouble(in[1]) > 1 || Double.parseDouble(in[1]) <= 0) {
            System.out.println("Credit is out of bounds");
            return false;
        }
        if (in.length > 3) {
            String preReq[] = in[3].split(":");
            for (int i = 0; i < preReq.length; i++) {
                if (!validCourseCode(preReq[i])) {
                    System.out.println("PreReq Course Code:" + preReq[i] + ": is Invalid");
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean validCourseCode(String in){
        COURSECODES coursecodes = null;
        String codePartition[] = in.split("\\*");
        if(codePartition.length <2){
           //System.out.println("length is bad"+codePartition.length);
            return false;
        }
        String code = codePartition[0];
        String num = codePartition[1];

        //check course code
        try{
            coursecodes = COURSECODES.valueOf(code);
        }catch (Exception e){
            System.out.println("invalid code :" + code);
            return false;
        }

        if(!num.matches("\\d{4}")){
            //System.out.println("num:"+num);
            return false;
        }
        return true;
    }

    public static void printMenu() {
        System.out.println("1: Read File");
        System.out.println("2: Read Require Courses for Requested Degree");
        System.out.println("3: Save");
        System.out.println("4: Exit");
    }


}
