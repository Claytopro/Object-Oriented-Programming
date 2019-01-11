import java.util.ArrayList;

public class BCG extends GeneralDegree {


    BCG(){
        this.setDegreeTitle("Bachelor of Computing");
    }

    boolean meetsRequirements(PlanOfStudy thePlan){
        return true;
    }


    ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan){
        return null;
    }

}
