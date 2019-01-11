public class main {

    public static void main(String [ ] args)
    {

        CourseCatalog catalog = new CourseCatalog();
        Student s = new Student();

        //TEST STUDENT FUNCTION
        s.setFirstName("Clayton");
        s.setLastName("Provan");
        System.out.println(s.getFullName());

        //TEST COURSE CATALOGUE FUNCTIONS.
        catalog.initializeCatalog("Catalog.txt");
        Course temp = catalog.findCourse("CIS*1910");
        System.out.println(temp.getCourseTitle());
        temp = catalog.findCourse("CIS*1250");
        System.out.println(temp.getCourseTitle());
        temp = catalog.findCourse("CIS*4650");
        System.out.println(temp.getCourseTitle());
        catalog.saveCatalog();


        //TEST PLAN OF STUDY POOP

    }

}
