import java.util.ArrayList;

public class Course {

    private String CourseCode;
    private String CourseTitle;
    private String Grade;
    private String CourseStatus;
    private double CourseCredit;
    private ArrayList<Course> Prerequisites;
    Course(){
        Prerequisites = new ArrayList<Course>();
        CourseCredit = 0.0;
        CourseTitle = "";
        CourseCode = "";
        CourseCredit = -1;
        Grade = "";
        CourseStatus = "";
    }

    Course(String code,double credit,String title,String[] prereq){
        Prerequisites = new ArrayList<Course>();
        CourseTitle = title;
        CourseCode = code;
        CourseCredit = credit;
        Grade = "";
        CourseStatus = "";

        if(prereq != null){
            for (String s: prereq) {
                Course temp = new Course();
                temp.setCourseCode(s);
                Prerequisites.add(temp);
            }
        }else{
            Prerequisites = null;
        }

    }

    @Override
    public String toString() {
        String s;
        if(CourseCredit == .50){
            s = CourseCode + "," + CourseCredit + "0" + "," + CourseTitle + ",";
        }else{
            s = CourseCode + "," + CourseCredit + "," + CourseTitle + ",";
        }

        if(Prerequisites !=null){
            for (Course c: Prerequisites) {
                s = s + c.getCourseCode() + ":";
            }
            s = s.substring(0,s.length()-1);

        }

        s = s + "\n";
        return s;
    }


    String getCourseCode(){
        return CourseCode;
    }

    String getCourseTitle(){
        return CourseTitle;
    }

    String getCourseGrade(){
        return Grade;
    }

    String getCourseStatus(){
        return CourseStatus;
    }
    ArrayList<Course> getPrerequisites(){
        return Prerequisites;
    }

    double getCourseCredit(){
        return CourseCredit;
    }

    void setCourseCode(String courseCode){
        CourseCode = courseCode;
    }

    void setCourseTitle(String courseTitle){
        CourseTitle = courseTitle;
    }

    void setCourseCredit(double credit){
        CourseCredit = credit;
    }
    //TODO figure out if you have to create new Course objects for the list or can get courses so that can move down prereqs like linked list.
    void setPrerequisites(ArrayList<Course> preReqList){
        Prerequisites = preReqList;
    }

    void addPrerequisite(Course toAdd){
        Prerequisites.add(toAdd);
    }


}
