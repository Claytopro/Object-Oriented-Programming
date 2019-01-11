public abstract class GeneralDegree extends Degree{

    private double credits;

    public GeneralDegree(){
        credits =0;
    }

    public GeneralDegree(double in){
        credits = in;
    }

    //Will need 15 credits
    double numberOfCreditsRemaining(PlanOfStudy thePlan){
        return 0.0;
    }

}
