import java.util.ArrayList;

public class SEng extends HonorsDegree {
    /**
     * Class Constructor
     */
    public SEng(){
     this.setDegreeTitle("Software Engineering");
    }

    /**
     * Tests to see if the PlanOfStudy contains the courses to meet requirements
     * base on uoguelph B.Comp Honors
     * fronm: https://www.uoguelph.ca/registrar/calendars/undergraduate/2018-2019/c10/sec_d0e66001.shtml
     * @param thePlan PlanOfStudy being evaluated
     * @return boolean based on if requirements are met
     */
    boolean meetsRequirements(PlanOfStudy thePlan){
        //used to ensure all required courses are part of PlanOfStudy Courses.
        int requiredCounter = 0;
        //Used to keep track of course level completed.
        double required3k =0;
        double required4k =0;
        double totalCisCredits =0;
        //used to calcualte average of cis courses. must be above 70
        double cisAverage = 0;
        int numberOfCisCourses =0;
        //used to keep track of electives
        double electiveCredits =0;
        //to track Minor information
        double minor3k = 0;
        double minorCredits =0;
        //holds grades from all other courses
        double culmunatingAverage =0;
        //used when parsing Course information
        int courseNum;
        String prefix;

        if(this.numberOfCreditsRemaining(thePlan) > 0){
            //if plan doesnt have enough credits automatically wont meet requirements.
            return false;
        }

        for(Course c : thePlan.getCoursesAdded()){
            prefix = c.getCourseCode().split("\\*")[0];
            courseNum =  Integer.parseInt(c.getCourseCode().split("\\*")[1]);
            culmunatingAverage += Double.parseDouble(c.getCourseGrade());
            if(requiredCourses.contains(c)){
                //to check if all required Courses are part of plan of study.
                totalCisCredits += c.getCourseCredit();
                requiredCounter++;
            } else if(prefix.equals("CIS") && courseNum >= 4000 ){
                required4k +=c.getCourseCredit();
                totalCisCredits += c.getCourseCredit();
            }else if(prefix.equals("CIS") && courseNum >= 3000 ){
                required3k += c.getCourseCredit();
                totalCisCredits += c.getCourseCredit();
            }else if(c.getType().equals("Elective")){
                electiveCredits += c.getCourseCredit();
            } else if(c.getType().equals("Minor")){
                if(courseNum > 3000){
                    minor3k += c.getCourseCredit();
                }else{
                    minorCredits += c.getCourseCredit();
                }
            }

            if(prefix.equals("CIS")){
                cisAverage +=  Double.parseDouble(c.getCourseGrade());
                numberOfCisCourses++;
            }
        }

        if(requiredCounter != requiredCourses.size() || (required3k + required4k) < 6.00 || required4k < 2.00 || totalCisCredits < 11.25 ||
                (cisAverage/numberOfCisCourses) <70 || electiveCredits < 4.75 || minor3k < 1.00 || (minor3k + minorCredits) < 4.00 || culmunatingAverage < 60){
            //does not meet specified requirements but will meet area of application/ elective required courses
            //because of number of credits remaining is zero or less
            return false;
        }

        return true;
    }
    /**
     * Goes Through courses and return an ArrayList of Course Objects that are still required to add to PlanOfStudy
     * @param thePlan PlanOfStudy Object used to inspect information and determin courses needed
     * @return Returns ArrayList of Courses that are required but not in PlanOfStudy
     */
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
