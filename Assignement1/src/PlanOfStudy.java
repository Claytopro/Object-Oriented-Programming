import java.io.*;
import java.util.ArrayList;

/**
 * Class PlanOfStudy is the root of the planner application.
 * All data is manipulated and stored through this object.
 * Allows for the tranmutation of the @Course objects fields.
 *
 * @see Degree
 * @see Course
 * @author Clayton Provan
 * @version 1.0
 */
public class PlanOfStudy {

    private Degree degree;
    private ArrayList<Course> coursesAdded;
    private Student student;
    private String fileName;

    /**
     * Constructor setting fields to null.
     */
    PlanOfStudy(){
        degree = null;
        coursesAdded = new ArrayList<Course>();
        student = new Student();
        fileName = "";
    }

    @Override
    public String toString(){
        String s = new String();
        for(Course c: coursesAdded){
            s += c.getCourseCode()+","+c.getCourseStatus()+","+c.getCourseGrade()+","+c.getSemesterTaken()+","+c.getType()+"\n";
        }

        return s;
    }

    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }

        PlanOfStudy plan = (PlanOfStudy) object;
        if(!plan.getStudent().equals(student)){
            return false;
        }

        if(!plan.getDegreeProgram().equals(degree)){
            return false;
        }

        //compare courses added
        if(coursesAdded.size() != plan.getCoursesAdded().size()){
            return false;
        }
        for(Course c: plan.getCoursesAdded()){
            if(!coursesAdded.contains(c)){
                return false;
            }
        }


        return true;
    }

    /**
     * imports data containing the courses taken based on the toString representation of
     * the PlanOfStudy.
     * @param filename file to be read from
     */
    void importData(String filename){
        fileName = filename;
        File file = new File(fileName);
        try{
            file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String stringLine;
            String lineArray[];
            //TODO fix bug  where if a course has the same course code and semester other paramters are messed up.
            while ((stringLine = br.readLine()) != null){
                lineArray = stringLine.split(",");
                this.addCourse(lineArray[0], lineArray[3]);
                this.setCourseGrade(lineArray[0], lineArray[3],lineArray[2]);
                this.setCourseStatus(lineArray[0], lineArray[3],lineArray[1]);
                this.setCourseType(lineArray[0], lineArray[3],lineArray[4]);
            }

            fr.close();
        }catch (FileNotFoundException e ){
            System.out.println("FILE NOT FOUND");
        }catch (IOException e){
            System.out.println("ERROR READING FILE");
        }

    }

    /**
     * Saves the state of the PlanOfStudy added Courses into a readable file containing text.
     */
    void saveState(){

        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            fw.write(this.toString());
            fw.close();
            System.out.println("File saved");
        }catch (IOException e){
            System.out.println("File couldnt be found/saved");
        }

    }

    /**
     * Sets the Degree used by the plan of study to verify required courses.
     * @param deg Degree object
     */
    void setDegreeProgram(Degree deg){
        degree = deg;
    }

    /**
     * Creates a deep copy of the Course found from within the Catalog.
     * @param courseCode String to help extract @Course object from @CourseCatalog based on matching Course objects CourseCode Value
     * @param semester String to help extract @Course object from @CourseCatalog based on matching Course objects Semester Value
     */
    void addCourse(String courseCode, String semester){
        if(degree.getCatalog().findCourse(courseCode) != null){
            try {
                //creates deep copy of Course
                Course toAdd = new Course((degree.getCatalog().findCourse(courseCode)));
                toAdd.setSemesterTaken(semester);
                coursesAdded.add(toAdd);
            } catch (NullPointerException e){
                System.out.println("Course does not exits.");
            }
        }


    }

    /**
     * Finds Course that is part of the PlanOfStudy based on matching courseCode and Semester
     * @param courseCode used to find match of Course in Catalog
     * @param semester used to find match of Course in Catalog
     * @param type String to set found objects Type
     */
    void setCourseType(String courseCode, String semester, String type){
        for(Course c: coursesAdded){
            if(c.getCourseCode().equals(courseCode) && c.getSemesterTaken().equals(semester)){
                c.setCourseType(type);
                return;
            }
        }
        System.out.println("Course is not part of plan of study.");
    }

    /**
     * Searchs Currently held Courses in PlanOfStudy and removes if it matches both parameters
     * @param courseCode Used to find a match to CourseCodes of Courses in the Courses contained in PlanOfStudy.
     * @param semester Used to find a match to Semester of semester in the Courses contained in PlanOfStudy.
     */
    void removeCourse(String courseCode, String semester){
        for(Course c: coursesAdded){
            if(c.getCourseCode().equals(courseCode) && c.getSemesterTaken().equals(semester)){
                System.out.println("course removed");
                return;
            }
        }

    }

    /**
     * Searches Through held Courses in PlanOfStudy and sets the Status of the found Course object.
     * @param courseCode Used to find a match to CourseCodes of Courses in the Courses contained in PlanOfStudy.
     * @param semester Used to find a match to Semester of semester in the Courses contained in PlanOfStudy.
     * @param courseStatus String to set Course's Status parameter to
     */
    void setCourseStatus(String courseCode, String semester, String courseStatus){
        for(Course c: coursesAdded){
            if(c.getCourseCode().equals(courseCode) && c.getSemesterTaken().equals(semester)){
                c.setCourseStatus(courseStatus);
                return;
            }
        }
        System.out.println("Course is not part of plan of study.");
    }

    /**
     * Searches Through held Courses in PlanOfStudy and sets the Grade of the found Course object.
     * @param courseCode Used to find a match to CourseCodes of Courses in the Courses contained in PlanOfStudy.
     * @param semester Used to find a match to Semester of semester in the Courses contained in PlanOfStudy.
     * @param grade String to set Course's grade parameter to
     */
    void setCourseGrade(String courseCode, String semester, String grade){
        for(Course c: coursesAdded){
            if(c.getCourseCode().equals(courseCode) && c.getSemesterTaken().equals(semester)){
               c.setCourseGrade(grade);
               return;
            }
        }
        System.out.println("Course is not part of plan of study.");
    }

    /**
     * Returns the arraylist full of Courses that are part of the plan of study
     * @return
     */
    ArrayList<Course> getCoursesAdded(){
        return coursesAdded;
    }

    /**
     *  Searchs through the held Courses in PlanOfStudy and returns the Object of the matching Course
     * @param courseCode Used to find a match to CourseCodes of Courses in the Courses contained in PlanOfStudy.
     * @param semester Used to find a match to Semester of semester in the Courses contained in PlanOfStudy.
     * @return returns Course Object that matches the input paramters, if none are found returns null
     */
    Course getCourse(String courseCode, String semester){

        for(Course c: coursesAdded){
            if(c.getCourseCode().equals(courseCode) && c.getSemesterTaken().equals(semester)){
                return c;
            }
        }

        return null;
    }

    /**
     * Searches Through the held Courses in PlanOfStudy and returns the Object of the matching Course
     * @param courseCode String used to find matching Course Object in PlanOfStudy
     * @return Return Course Object that matches courseCode, if none are found returns null
     */
    Course getCourse(String courseCode){
        for(Course c: coursesAdded){
            if(c.getCourseCode().equals(courseCode)){
                return c;
            }
        }

        return null;
    }

    /**
     * Sets the name of the file used for persistent Storage.
     * @param fn String the name of the file PlanOfStudy will write to.
     */
    void setFileName(String fn){
        fileName = fn;
    }

    /**
     * Returns the currently held file name used for persistent storage
     * @return Returns the currently held file name used for persistant storage
     */
    String getFileName(){
        return fileName;
    }

    /**
     * Returns the Degree Object Currently being used by PlanOfStudy
     * @return Returns the Degree Object Currently being used by PlanOfStudy
     */
    Degree getDegreeProgram(){
        return degree;
    }

    /**
     * Returns the Student Object Currently Being used by PlanOfStudy
     * @return Returns the Student Object Currently Being used by PlanOfStudy
     */
    Student getStudent(){
        return student;
    }

    /**
     * Sets the current Student Object held in PlanOfStudy to the input Student Object
     * @param sdt Student Object the PlanOfStudy will now link to.
     */
    void setStudent(Student sdt){
        student = sdt;
    }
}
