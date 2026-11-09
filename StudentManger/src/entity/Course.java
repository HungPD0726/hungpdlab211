package entity;

/**
 * Enumeration representing available programming courses in the system.
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

    /**
     * Resolves a Course enum instance by its 1-based numeric type index.
     *
     * @param courseType Numeric course selector (1 for Java, 2 for .Net, 3 for C/C++).
     * @return Corresponding {@link Course} enum instance.
     * @throws AssertionError if courseType is not in the range [1, 3].
     */
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

    /**
     * Alias for {@link #getCourseByType(int)}.
     *
     * @param courseType Numeric course selector.
     * @return Corresponding {@link Course} enum instance.
     */
    public static Course getCourse(int courseType) {
        return getCourseByType(courseType);
    }

    /**
     * Returns the human-readable language name of this course.
     *
     * @return Display name of the course.
     */
    public String getLanguage() {
        return language;
    }

}
