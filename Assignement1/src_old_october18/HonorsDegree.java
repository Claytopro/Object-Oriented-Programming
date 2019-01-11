

public abstract class HonorsDegree extends Degree {


    public HonorsDegree(){
        this.setCredits(0);
    }

    public HonorsDegree(double input)
    {
        this.setCredits(input);
    }

    //Will need 20 credits.
    double numberOfCreditsRemaining(PlanOfStudy thePlan)
    {
        return 0.0;
    }


}
