public class Student {
    private String FirstName;
    private String LastName;
    private Integer StudentNumber;

    Student() {
        FirstName = "";
        LastName = "";
        StudentNumber = -1;
    }

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

    String getFirstName(){
        return FirstName;
    }

    String getLastName(){
        return LastName;

    }

    String getFullName(){
        return FirstName + " " + LastName;
    }

    void setFirstName(String first){
        FirstName = first;
    }

    void setLastName(String last){
        LastName = last;
    }

    void setStudentNumber(Integer studentNum){
        StudentNumber = studentNum;
    }

    Integer getStudentNumber(){
        return StudentNumber;
    }

}


