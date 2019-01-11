import java.io.*;
import java.util.ArrayList;

public class CourseCatalog {

    private ArrayList<Course> coursesAvailible;


    CourseCatalog(){
        coursesAvailible = new ArrayList<Course>();
    }

    CourseCatalog(ArrayList<Course> Availible){
        coursesAvailible = Availible;
    }


    // iterate though file adding Courses to course Catalogue;
    void initializeCatalog(String filename){
        File file = new File(filename);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String stringLine = "";
            String splitArray[];
            String splitPrereq[];

            while((stringLine = br.readLine()) != null){
                //TODO remove this after testing.
                System.out.println("line is "+stringLine);
                splitPrereq = null;

                splitArray = stringLine.split(",");
                if(splitArray.length >3){
                    splitPrereq = splitArray[3].split(":");
                }
                Course temp = new Course(splitArray[0],Double.parseDouble(splitArray[1]), splitArray[2], splitPrereq);
                coursesAvailible.add(temp);
            }
            fr.close();
            //TODO changes catch stuff
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void addCourse(Course toAdd){
        coursesAvailible.add(toAdd);
    }

    void removeCourse(Course toRemove){
        coursesAvailible.remove(toRemove);
    }
    //Should Save with specified file name?
    void saveCatalog(){
        File file;
        file = new File("Catalog.txt");
        String courseString;
        try{
            FileWriter fw = new FileWriter(file);

            for(Course temp: coursesAvailible){
                courseString = temp.toString();
                fw.write(courseString);
            }


            fw.close();
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Course findCourse(String courseCode){
        for (Course temp: coursesAvailible) {
            if(temp.getCourseCode().equals(courseCode)){
                return temp;
            }
        }
        System.out.println("course not availible");
        return null;
    }

}
