import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            HashMap<String,Course> temp = (HashMap<String,Course>) objectIn.readObject();
            Collection<Course> temp1 = temp.values();
            coursesAvailible = new ArrayList<Course>(temp1);
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
                courseString = temp.toString() + "\n";
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
        return null;
    }

}
