package entity;

/**
 *
 * @author HP
 */
public enum Course {
    JAVA("Java"),
    DOT_NET(".Net"),
    CPP("C/C++");

    private final String language;

    private Course(String language) {
        this.language = language;
    }

    public static Course getCourseByType(int courseType) {
        switch (courseType) {
            case 1:
                return JAVA;
            case 2:
                return DOT_NET;
            case 3:
                return CPP;
            default:
                throw new AssertionError("Invalid: " + courseType);
        }
    }

    public static Course getCourse(int courseType) {
        return getCourseByType(courseType);
    }

    public String getLanguage() {
        return language;
    }

}
