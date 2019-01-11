import java.util.ArrayList;

public class SEng extends HonorsDegree {

    public SEng(){
     this.setDegreeTitle("Software Engineering");
    }

    boolean meetsRequirements(PlanOfStudy thePlan){
        return true;
    }

    ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan){
        return null;
    }


}
