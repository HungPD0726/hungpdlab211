package validator;

import java.util.Scanner;

/**
 * Lớp tiện ích kiểm tra dữ liệu đầu vào:
 * Chỉ gồm 3 hàm cơ bản: getInt, getDouble và getString (kiểm tra theo REGEX)
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Validator() {
    }

    /**
     * Nhập số nguyên trong khoảng [min, max]
     */
    public static int getInt(String messageInfo, String messageOutOfRange, String errorNumber, int min, int max) {
        while (true) {
            try {
                System.out.println(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(errorNumber);
            }
        }
    }

    /**
     * Nhập số thực trong khoảng [min, max]
     */
    public static double getDouble(String messageInfo, String messageOutOfRange, String errorNumber, double min, double max) {
        while (true) {
            try {
                System.out.println(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(errorNumber);
            }
        }
    }

    /**
     * Nhập chuỗi khớp theo biểu thức chính quy (REGEX)
     * Dùng hàm này để kiểm tra cho tất cả dữ liệu chuỗi (môn học, yes/no, update/delete...)
     */
    public static String getString(String messageInfo, String messageError, final String REGEX) {
        while (true) {
            System.out.println(messageInfo);
            String str = SCANNER.nextLine().trim();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        }
    }
}
