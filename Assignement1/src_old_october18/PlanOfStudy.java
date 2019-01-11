import java.io.*;
import java.util.ArrayList;

public class PlanOfStudy {

    private Degree degree;

    PlanOfStudy(){
        degree = null;
    }




    void importData(String filename){
        File file = new File(filename);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);



            fr.close();
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void saveState(){

    }

    void setDegreeProgram(Degree deg){
        degree = deg;
    }


    void addCourse(String courseCode, String semester){


    }

    void removeCourse(String courseCode, String semester){


    }

    void setCourseStatus(String courseCode, String semester, String courseStatus){

    }


    void setCourseGrade(String courseCode, String semester, String grade){


    }

    Course getCourse(String courseCode, String semester){
        return null;
    }

    Degree getDegreeProgram(){
        return degree;
    }


}
