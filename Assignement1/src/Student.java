/**
 * Object that stores basic Student information such as
 * students first and last name, as well as their student number
 * @author Clayton Provan
 *
 */

public class Student {
    private String FirstName;
    private String LastName;
    private Integer StudentNumber;

    /**
     * Class constructor.
     */
    Student() {
        FirstName = "";
        LastName = "";
        StudentNumber = -1;
    }

    /**
     * Class constructor specifying all required fields
     * @param fName sets student first name
     * @param lName sets student last name
     * @param sNum sets student number
     */
    Student(String fName, String lName, Integer sNum) {
        FirstName = fName;
        LastName = lName;
        StudentNumber = sNum;
    }


    @Override
    public String toString(){
        String s = new String();
        s =FirstName + " " + LastName + " " + StudentNumber;
        return s;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        Student student = (Student) o;
        //compares First and Last names, as well as student number;
        if(this.toString().equals(o.toString())){
            return true;
        }
        return false;
    }

    /**
     *
     * @return Students First Name
     */
    String getFirstName(){
        return FirstName;
    }

    /**
     *
     * @return Students Last Name
     */
    String getLastName(){
        return LastName;

    }

    /**
     *
     * @return Students First and Last Name
     */
    String getFullName(){
        return FirstName + " " + LastName;
    }

    /**
     *
     * @param first String user wants Student Name to be set to.
     */
    void setFirstName(String first){
        FirstName = first;
    }

    /**
     *
     * @param last String user wants Last Name of student object to be set to.
     */
    void setLastName(String last){
        LastName = last;
    }

    /**
     *
     * @param studentNum sets the student number of the student object
     */
    void setStudentNumber(Integer studentNum){
        StudentNumber = studentNum;
    }

    /**
     *
     * @return the student number of the Student object.
     */
    Integer getStudentNumber(){
        return StudentNumber;
    }

}


