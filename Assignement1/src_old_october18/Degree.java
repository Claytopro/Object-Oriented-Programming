import java.util.ArrayList;

public abstract class Degree {

    private double credits;
    private String degreeTitle;


    private ArrayList<Course> requiredCourses;

    Degree(){
        degreeTitle = "";
        requiredCourses = new ArrayList<Course>();
    }

    Degree(String title){
        degreeTitle = title;
        requiredCourses = new ArrayList<Course>();
    }

    String getDegreeTitle(){
        return degreeTitle;
    }

    void setDegreeTitle(String title){
        degreeTitle = title;
    }

   void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes){

   }

    double getCredits(){
        return credits;
    }

    void setCredits(double c){
        credits = c;
    }

    ArrayList<Course> getRequiredCourses(){
        return null;
    }

    abstract double numberOfCreditsRemaining(PlanOfStudy thePlan);

    abstract boolean meetsRequirements(PlanOfStudy thePlan);

    abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);

}
