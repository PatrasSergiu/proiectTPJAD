package p;


public class AppUser implements Serializable {

    private String username = "";
    private String password = "";
    private Collection<Course> enrolledCourses = (Collection<Course>)(new ArrayList<Course>());


}
