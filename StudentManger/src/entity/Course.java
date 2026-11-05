/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Enum định nghĩa 3 khóa học hợp lệ theo yêu cầu đề bài:
 * <ul>
 *   <li>1. Java</li>
 *   <li>2. .Net</li>
 *   <li>3. C/C++</li>
 * </ul>
 * 
 * @author Administrator
 */
public enum Course {
    /**
     * Khóa học lập trình Java.
     */
    JAVA("Java"),

    /**
     * Khóa học lập trình nền tảng .Net.
     */
    DOT_NET(".Net"),

    /**
     * Khóa học lập trình ngôn ngữ C/C++.
     */
    CPP("C/C++");

    private final String language;

    /**
     * Khởi tạo một phần tử Course với tên hiển thị tương ứng.
     * 
     * @param language Tên hiển thị chuẩn của khóa học (ví dụ: "Java", ".Net", "C/C++")
     */
    Course(String language) {
        this.language = language;
    }

    /**
     * Chuyển đổi mã số kiểu khóa học được người dùng chọn sang hằng số Course tương ứng.
     * 
     * @param courseType Mã số tương ứng với khóa học (1: Java, 2: .Net, 3: C/C++)
     * @return Đối tượng Course tương ứng với mã số
     * @throws AssertionError Nếu mã số truyền vào không hợp lệ (không phải 1, 2, 3)
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
                throw new AssertionError("Invalid course type: " + courseType);
        }
    }

    /**
     * Lấy đối tượng Course dựa theo mã số khóa học (phương thức tiện ích gọi tới getCourseByType).
     * 
     * @param courseType Mã số khóa học (1: Java, 2: .Net, 3: C/C++)
     * @return Đối tượng Course tương ứng
     */
    public static Course getCourse(int courseType) {
        return getCourseByType(courseType);
    }

    /**
     * Lấy tên hiển thị của khóa học.
     * 
     * @return Chuỗi ký tự biểu diễn tên môn học (String)
     */
    public String getLanguage() {
        return language;
    }
}
