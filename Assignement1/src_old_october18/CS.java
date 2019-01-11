import java.util.ArrayList;

public class CS extends HonorsDegree {

    public CS(){
        this.setDegreeTitle("Computer Science");
    }

    boolean meetsRequirements(PlanOfStudy thePlan){
        return true;
    }

    ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan){
        return null;
    }

}
