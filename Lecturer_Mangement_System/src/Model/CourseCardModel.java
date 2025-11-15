package Model;

public class CourseCardModel {

    private String CourseTitle;
    private String LecturerTitle;
    private String CourseInfo;

    // Constructors
    public CourseCardModel() {
        // Default constructor
    }

    public CourseCardModel(String courseTitle, String lecturerTitle, String courseInfo) {
    this.CourseTitle = courseTitle;
    this.LecturerTitle = lecturerTitle;
    this.CourseInfo = courseInfo;
    }

    //Getters
    public String getCourseTitle() {
        return CourseTitle;
    }

    public String getLecturerTitle() {
        return LecturerTitle;
    }

    public String getCourseInfo() {
        return CourseInfo;
    }

     //Setters
public void setCourseTitle(String courseTitle) {
        this.CourseTitle = courseTitle;
    }

    public void setLecturerTitle(String lecturerTitle) {
        this.LecturerTitle = lecturerTitle;
    }

    public void setCourseInfo(String courseInfo) {
        this.CourseInfo = courseInfo;
    }
    
}