import java.util.ArrayList;

/**
 * Abstract class that hodls information pertaining to a Degree
 */
public abstract class Degree {

    protected double credits;
    private String degreeTitle;

    protected CourseCatalog catalog = new CourseCatalog();
    protected ArrayList<Course> requiredCourses;

    /**
     * Class constructor
     */
    Degree(){
        degreeTitle = "";
        requiredCourses = new ArrayList<Course>();
        catalog.initializeCatalog("COURSECATALOG");
    }

    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        Degree o = (Degree)object;

        if(requiredCourses.size() != o.getRequiredCourses().size()){
            return false;
        }
        for(Course c: requiredCourses){
            if(!o.getRequiredCourses().contains(c)){
                return false;
            }
        }

        if(!o.getDegreeTitle().equals(degreeTitle)){
            return false;
        }

        return true;
    }

    /**
     * Class constructor which includes degree title
     * @param title String to sets title field in Degree
     */
    Degree(String title){
        degreeTitle = title;
        requiredCourses = new ArrayList<Course>();
    }

    @Override
    public String toString(){
        String s = new String();
        s = degreeTitle + "\n";
        for(Course c: requiredCourses){
            s += c.getCourseCode() +":\n";
        }
        return s;
    }

    /**
     * Returns degree title String variable
     * @return Returns degree title String variable
     */
    String getDegreeTitle(){
        return degreeTitle;
    }

    /**
     * Sets Degree Title
     * @param title String to set Degree Title
     */
    void setDegreeTitle(String title){
        degreeTitle = title;
    }

    /**
     * Arralist of String that represent CourseCodes and used to find the Course Objects in CourseCatalog. That are then added to reqruried Courses arraylist
     * @param listOfRequiredCourseCodes Arralist of String that represent CourseCodes.
     */
   void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes){
        for(String s : listOfRequiredCourseCodes){
            if(catalog.findCourse(s) != null){
                requiredCourses.add(catalog.findCourse(s));
            }
        }
   }

    /**
     * adds a Course Object to required Courses.
     * @param c Course Object to add.
     */
   void addToRequired(Course c){
        if(c != null){
            requiredCourses.add(c);
        }
   }

    /**
     * Returns amount of credits required
     * @return Returns amount of credits required
     */
    double getCredits(){
        return credits;
    }

    /**
     * Sets credits required
     * @param c double to set the requried amount of credits to.
     */
    void setCredits(double c){
        credits = c;
    }

    /**
     * Return CourseCatalog object Degree is linked to
     * @return Return CourseCatalog object Degree is linked to
     */
    CourseCatalog getCatalog(){
        return catalog;
    }

    /**
     * Returns ArrayList of Courses that are required by degree
     * @return Returns ArrayList of Courses that are required by degree
     */
    ArrayList<Course> getRequiredCourses(){
        return requiredCourses;
    }

    /**
     * Returns Credits remaining
     * @param thePlan PlanOfStudy object that is being evalutated
     * @return Returns Credits remaining
     */
    abstract double numberOfCreditsRemaining(PlanOfStudy thePlan);

    /**
     * Returns boolean based on if the PlanOfStudy holds courses that meet specified requirements
     * @param thePlan PlanOfStudy object that is being evalutated
     * @return Returns boolean based on if the PlanOfStudy holds courses that meet specified requirements
     */
    abstract boolean meetsRequirements(PlanOfStudy thePlan);

    /**
     * Returns Arraylist of Courses planOfStudy need to add to meet required Courses.
     * @param thePlan PlanOfStudy object that is being evalutated
     * @return Returns Arraylist of Courses planOfStudy need to add to meet required Courses.
     */
    abstract ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan);


}
