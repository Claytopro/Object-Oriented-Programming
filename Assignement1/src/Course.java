
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Course holds information about Course.
 * includes course code, title, grade, status, semester taken
 * type of course (elective,minor,required), credit amount ,and list of Course objects of prerequisites.
 * @implSpec implemnts Serializable
 * @see Serializable
 *
 * @author Clayton Provan
 * @version 1.0
 */
public class Course implements Serializable {

    private String CourseCode;
    private String CourseTitle;
    private String Grade;
    private String CourseStatus;
    private String Semester;
    private String Type; //required/elective/minor
    private double CourseCredit;
    private ArrayList<Course> Prerequisites;

    /**
     * Construct new Course Object with default values of all variables empty.
     */
    Course(){
        Prerequisites = new ArrayList<Course>();
        CourseCredit = 0.0;
        CourseTitle = "";
        CourseCode = "";
        CourseCredit = -1;
        Grade = "";
        CourseStatus = "";
        Semester = "";
    }

    /**
     * Constructs new object with
     * @param code String to set coursecode to
     * @param credit value to set credit to
     * @param title String to set Title to
     */
    Course(String code,double credit,String title){
        Prerequisites = new ArrayList<Course>();
        CourseTitle = title;
        CourseCode = code;
        CourseCredit = credit;
        Grade = "";
        CourseStatus = "";
        Semester = "";
    }

    /**
     * Creates deep copy of course
     * @param course
     */

    Course(Course course){
        this(course.getCourseCode(),course.getCourseCredit(),course.getCourseTitle());
    }

    @Override
    public String toString() {
        String s;
        if(CourseCredit == .50){
            s = CourseCode + "," + CourseCredit + "0," + CourseTitle + ",";
        }else{
            s = CourseCode + "," + CourseCredit + "," + CourseTitle + ",";
        }

        if(Prerequisites !=null){
            for (Course c: Prerequisites) {
                s = s + c.getCourseCode() + ":";
            }
            s = s.substring(0,s.length()-1);
        }


        return s;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        Course c = (Course) o;

        if(!c.toString().equals(this.toString())){
            return false;
        }

        if(c.getSemesterTaken() != Semester || c.getCourseStatus() != CourseStatus || c.getCourseGrade() != Grade){
            return false;
        }

        return true;
    }

    /**
     *
     * @return String holding coursecode
     */
    String getCourseCode(){
        return CourseCode;
    }

    /**
     *
     * @return String holding course's title
     */
    String getCourseTitle(){
        return CourseTitle;
    }

    /**
     *
     * @return String holding grade of course.
     */
    String getCourseGrade(){
        return Grade;
    }

    /**
     *
     * @return String holding Status of course
     */
    String getCourseStatus(){
        return CourseStatus;
    }

    /**
     *
     * @return String holding semester course was taken
     */
    String getSemesterTaken(){return Semester;}

    /**
     * Holds Requried,Elective or Minor
     * indicating what kind of course it is
     * @return String holding what kind of course
     */
    String getType(){return Type;};

    /**
     *
     * @return ArrayList</Course> with the courses prerequisite course objects
     */
    ArrayList<Course> getPrerequisites(){
        return Prerequisites;
    }

    /**
     *
     * @return Courses credit value
     */
    double getCourseCredit(){
        return CourseCredit;
    }

    /**
     *
     * @param courseCode String to set coursecode to
     */
    void setCourseCode(String courseCode){
        CourseCode = courseCode;
    }

    /**
     *
     * @param courseTitle String to set courseTitle to
     */
    void setCourseTitle(String courseTitle){
        CourseTitle = courseTitle;
    }

    /**
     *
     * @param grade String to set course grade to
     */
    void setCourseGrade(String grade){
        Grade = grade;
    }

    /**
     *
     * @param credit double to set course credit to
     */
    void setCourseCredit(double credit){
        CourseCredit = credit;
    }

    /**
     *
     * @param status String to set course Status to
     */
    void setCourseStatus(String status){
        CourseStatus = status;
    }

    /**
     *
     * @param type String to set the courses Type.
     */
    void setCourseType(String type){Type = type;}

    /**
     *
     * @param preReqList ArrayList of Courses to set prerequisistes to
     */
    //TODO figure out if you have to create new Course objects for the list or can get courses so that can move down prereqs like linked list.
    void setPrerequisites(ArrayList<Course> preReqList){
        Prerequisites = preReqList;
    }

    /**
     *
     * @param semester String to set the Courses semester field to
     */
    void setSemesterTaken(String semester){
        Semester = semester;
    }

    /**
     *
     * @param toAdd Course object to add to Arraylist of prerequisistes
     * @return boolean based on if the course was added
     */
    boolean addPrerequisite(Course toAdd){
        if(toAdd != null){
            Prerequisites.add(toAdd);
            return true;
        }else{
            return false;
        }

    }


}
