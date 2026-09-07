package entity;

/**
 * Lớp định nghĩa hằng số cho các khóa học hợp lệ theo đặc tả:
 * "There are only three courses: Java, .Net, C/C++"
 */
public class Course {
    public static final String JAVA = "Java";
    public static final String NET = ".Net";
    public static final String CPP = "C/C++";

    public static final String[] VALID_COURSES = {JAVA, NET, CPP};
}
