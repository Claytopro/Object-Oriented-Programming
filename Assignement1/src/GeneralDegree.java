public abstract class GeneralDegree extends Degree{
    /**
     * Class Constructor setting default amount of credits required to 15.
     */
    public GeneralDegree(){
        credits = 15;
    }

    /**
     * Constructor taking in required credits
     * @param in double to set required amount of credits
     */
    public GeneralDegree(double in){
        credits = in;
    }

    /**
     * Returns number of credits that the PlanOfStudy needs to meet required amount
     * @param thePlan PlanOfStudy Object used to see amount of course credits.
     * @return Returns number of credits that the PlanOfStudy needs to meet required amount
     */
    double numberOfCreditsRemaining(PlanOfStudy thePlan)
    {
        double remaining = credits;
        for(Course c:thePlan.getCoursesAdded()){
            remaining -= c.getCourseCredit();
        }

        return remaining;
    }


}
