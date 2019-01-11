import java.util.ArrayList;

public class BCG extends GeneralDegree {



    BCG(){
        this.setDegreeTitle("Bachelor of Computing");
    }

    boolean meetsRequirements(PlanOfStudy thePlan){
        int requiredCounter = 0;
        double requiredAdditionCourses =0;
        double requiredHigherCourses =0;
        double scienceCredits =0;
        int courseNum;
        String prefix;
        SCIENCECODES sciencecodes = null;

        if(this.numberOfCreditsRemaining(thePlan) > 0){
            //if plan doesnt have enough credits automatically wont meet requirements.
            return false;
        }

        for(Course c : thePlan.getCoursesAdded()){
            prefix = c.getCourseCode().split("\\*")[0];
            courseNum =  Integer.parseInt(c.getCourseCode().split("\\*")[1]);
            if(requiredCourses.contains(c)){
                //to check if all required Courses are part of plan of study.
                requiredCounter++;
            } else if(prefix.equals("CIS") && courseNum>=3000){
                //needs 1.00 additional CIS credits at 3000 level or higher
                requiredHigherCourses += c.getCourseCredit();
            }else if((prefix.equals("STAT") || prefix.equals("CIS")) && courseNum>=2000){
                //needs 0.50 additional CIS or STAT credits at the 2000 level or higher
                courseNum += c.getCourseCredit();
            }else {
                try{
                    sciencecodes = SCIENCECODES.valueOf(prefix);
                    scienceCredits += c.getCourseCredit();
                }catch (Exception e){

                }
            }
        }

        if(requiredCounter == requiredCourses.size() && (requiredAdditionCourses + requiredHigherCourses)>= 1.5 && requiredHigherCourses >= 1.0 && scienceCredits >=2.00){
            return true;
        }

        return false;
    }

    ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan)
    {
        ArrayList<Course> remainingCourses = new ArrayList<Course>();

        for(Course c : requiredCourses){
            if(!thePlan.getCoursesAdded().contains(c)){
                remainingCourses.add(c);
            }
        }
        return remainingCourses;
    }

}
