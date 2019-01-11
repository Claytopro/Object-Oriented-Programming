

public abstract class HonorsDegree extends Degree {


    public HonorsDegree(){
        credits =20;
    }

    public HonorsDegree(double input)
    {
       credits = input;
    }

    //Will need 20 credits.
    double numberOfCreditsRemaining(PlanOfStudy thePlan)
    {
        double remaining = credits;
        for(Course c:thePlan.getCoursesAdded()){
            remaining -= c.getCourseCredit();
        }

        return remaining;
    }


}
