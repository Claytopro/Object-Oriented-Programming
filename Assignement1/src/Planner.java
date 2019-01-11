import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Application that allows user to create a planned study path for their univerisity Career
 */
public class Planner {

    public static void main(String[] args) {
        int choice = -1;
        String input = "";
        String input2 = "";
        String input3 = "";
        boolean validEntry = true;
        Scanner scanner = new Scanner(System.in);
        PlanOfStudy planOfStudy = new PlanOfStudy();
        ArrayList<String> requiredCourses = new ArrayList<String>();
        File file;

        Student s = new Student();

        while(true){
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){

                case 1:
                    System.out.println("enter file's name");
                    input = scanner.nextLine();
                    if(planOfStudy.getDegreeProgram() == null){
                        //TODO change to make it have degree saved into FIlE? .. not part of assignement info.
                        System.out.println("Please Select Degree First.");
                    }else{
                        planOfStudy.importData(input);
                    }
                    break;
                case 2:
                    System.out.println("Select (1)BComp Honors or (2)BComp General");
                    choice = Integer.parseInt(scanner.nextLine());

                    switch (choice){
                        case 1:
                            System.out.println("Select Major (1)Computer Science or (2) Software Engineering");
                            choice = Integer.parseInt(scanner.nextLine());
                            switch (choice){
                                case 1:
                                    CS Cdeg = new CS();
                                    planOfStudy.setDegreeProgram(Cdeg);
                                    input = "CSarraylist";
                                    validEntry = true;
                                    break;
                                case 2:
                                    SEng Sdeg = new SEng();
                                    planOfStudy.setDegreeProgram(Sdeg);
                                    input ="SENGarraylist";
                                    validEntry = true;
                                    break;
                            }
                            break;
                        case 2:
                            BCG deg = new BCG();
                            planOfStudy.setDegreeProgram(deg);
                            input = "BCGarraylist";
                            break;


                            default:
                                System.out.println("Invalid input");
                                validEntry = false;
                            break;
                    }
                    if(validEntry){
                        file  = new File(input);
                        try {
                            FileInputStream fileIn = new FileInputStream(file);
                            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                            requiredCourses = (ArrayList<String>) objectIn.readObject();
                            objectIn.close();
                            fileIn.close();

                        }catch (FileNotFoundException e) {
                            System.out.println("File Not Found");
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }catch (ClassNotFoundException e){
                            e.printStackTrace();
                        }
//                         check if all are read in properly
//                        for(String ss: requiredCourses){
//                            System.out.println(ss);
//                        }
                        planOfStudy.getDegreeProgram().setRequiredCourses(requiredCourses);
                    }
                    break;
                case 3:
                    //Save plan of study.
                    if(planOfStudy.getFileName().equals("")){
                        System.out.println("Enter file name");
                        input = scanner.nextLine();
                        planOfStudy.setFileName(input);
                    }
                    planOfStudy.saveState();
                    break;
                case 4:
                    System.out.println("enter Course Code.");
                    input = scanner.nextLine();
                    System.out.println("enter Semester.");
                    input2 = scanner.nextLine();
                    planOfStudy.addCourse(input,input2);
                    System.out.println("Is Course (1)In-progress,(2) Completed,or (3)Planned");
                    input3 = scanner.nextLine();
                    switch(input3){
                        case "1":
                            input3 = "In-Progress";
                            break;
                        case "2":
                            input3 = "Completed";
                            break;
                        case "3":
                            input3 = "Planned";
                            break;

                            default:
                                System.out.println("Invalid Input, default set to Planned");
                                input3 = "Planned";
                                break;
                    }
                    planOfStudy.setCourseStatus(input,input2,input3);
                    System.out.println("Is course (1) Required,(2) Elective,or (3) Minor");
                    input3 = scanner.nextLine();
                    switch(input3){
                        case "1":
                            input3 = "Required";
                            break;
                        case "2":
                            input3 = "Elective";
                            break;
                        case "3":
                            input3 = "Minor";
                            break;

                        default:
                            System.out.println("Invalid Input, default set to Elective");
                            input3 = "Elective";
                            break;
                    }
                    planOfStudy.setCourseType(input,input2,input3);



                    break;
                case 5:
                    //TODO remove this as it has been done in a lower case.
                    //adds course to required courses arraylist.
//                    System.out.println("enter Course Code.");
//                    input = scanner.nextLine();
//                    Course toAdd = planOfStudy.getCourse(input);
//                    if(toAdd != null) {
//                        planOfStudy.getDegreeProgram().addToRequired(toAdd);
//                    }

                    break;

                case 6:
                    System.out.println("Enter Course Code you wish to be removed.");
                    input = scanner.nextLine();
                    System.out.println("Enter Semester Course was taken");
                    input2 = scanner.nextLine();
                    planOfStudy.removeCourse(input,input2);
                    break;
                case 7:
                    System.out.println("Enter Course Code");
                    input = scanner.nextLine();
                    System.out.println("Enter Semester");
                    input2 = scanner.nextLine();
                    System.out.println("Enter New Grade");
                    input3 = scanner.nextLine();

                    planOfStudy.setCourseGrade(input,input2,input3);
                    break;
                case 8:
                    //goes through courses added to plan and prints any that dont show up in required Courses of Major
                    //TODO change output to look better
                    if(planOfStudy.getDegreeProgram()!=null){
                        System.out.println("Remaing Required courses are:" );
                        for(Course c:planOfStudy.getDegreeProgram().remainingRequiredCourses(planOfStudy)){
                            System.out.println(c);
                        }
                    }

                    break;
                case 9:
                    //view a list of the prerequisite courses for any required course for my degree and major
                    //also any other courses.
                    System.out.println("Enter Course code you wish to know the prerequisites for");
                    input = scanner.nextLine();
                    //TODO change output to look better
                    if(planOfStudy.getDegreeProgram().getCatalog().findCourse(input).getPrerequisites() == null){
                        System.out.println("Has no Prerequisistes");
                    }else{
                        System.out.println("Prerequisites are:");
                        for(Course c: planOfStudy.getDegreeProgram().getCatalog().findCourse(input).getPrerequisites()){
                            System.out.println(c.getCourseCode()+",");
                        }
                    }
                    break;
                case 10:
                   System.out.println(planOfStudy);
                    break;
                case 11:
                    if(planOfStudy.getDegreeProgram() != null){
                        double credits = planOfStudy.getDegreeProgram().getCredits();
                        double creditsRemaining =  planOfStudy.getDegreeProgram().numberOfCreditsRemaining(planOfStudy);
                        System.out.println("Credits Remaining:" + creditsRemaining);
                        System.out.println("Credits Completed:" + (creditsRemaining - credits)*(-1));
                    }else {
                        System.out.println("Degree has not been selected.");
                    }

                    break;

                case 12:
                    System.out.println("enter Course you wish to change.");
                    input = scanner.nextLine();
                    System.out.println("Enter Semester Course was taken");
                    input2 = scanner.nextLine();
                    System.out.println("Is course (1) Required,(2) Elective,or (3) Minor");
                    input3 = scanner.nextLine();
                    switch(input3){
                        case "1":
                            input3 = "Required";
                            break;
                        case "2":
                            input3 = "Elective";
                            break;
                        case "3":
                            input3 = "Minor";
                            break;
                        default:
                            System.out.println("Invalid Input.default set to Elective");
                            input3 = "Elective";
                            break;
                    }
                    planOfStudy.setCourseType(input,input2,input3);
                    break;
                case 13:
                    if(planOfStudy.getDegreeProgram() != null){
                        if(planOfStudy.getDegreeProgram().meetsRequirements(planOfStudy)){
                            System.out.println("Plan of Study meet Requirements!");
                        }else {
                            System.out.println("Plan of Study does not meet Requirements!");
                        }
                    }

                    break;
                case 0:
                    System.exit(0);
            }
        }

    }


    public static void printMenu(){
        System.out.println("1:Load Study Plan");
        System.out.println("2:Select Degree and Select Major");
        System.out.println("3:Save Plan of study");
        System.out.println("4:Add course to plan of study");
        System.out.println("5:Mark course as required");
        System.out.println("6:Remove course from plan of study");
        System.out.println("7:Change grade");
        System.out.println("8:View Required Courses Needed");
        System.out.println("9:Get Prerequisistes for a Course");
        System.out.println("10:Print Plan of study");
        System.out.println("11:Print Number of credits remaining and number of credits finished");
        System.out.println("12:Change Type of Course in Plan");
        System.out.println("13:Met degree requirement?");
        System.out.println("0:Exit Program Without Saving.");
    }


}
