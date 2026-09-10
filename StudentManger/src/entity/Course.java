/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Enum định nghĩa 3 khóa học hợp lệ theo đề bài: Java, .Net, C/C++
 * 
 * @author Administrator
 */
public enum Course {
    JAVA("Java"),
    DOT_NET(".Net"),
    CPP("C/C++");

    private final String language;

    Course(String language) {
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
                throw new AssertionError("Invalid course type: " + courseType);
        }
    }

    public static Course getCourse(int courseType) {
        return getCourseByType(courseType);
    }

    public String getLanguage() {
        return language;
    }
}