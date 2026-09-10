/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Lớp tiện ích kiểm tra và chuẩn hóa dữ liệu đầu vào từ bàn phím.
 * Đảm bảo mọi tên biến, tham số không viết tắt và tuân thủ Java Naming Conventions.
 * 
 * @author win
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {
    }

    /**
     * Nhập số nguyên trong khoảng từ minimumValue đến maximumValue.
     * 
     * @param promptMessage Thông điệp nhắc người dùng nhập liệu
     * @param outOfRangeErrorMessage Thông báo lỗi khi giá trị nằm ngoài khoảng cho phép
     * @param invalidNumberErrorMessage Thông báo lỗi khi định dạng không phải là số nguyên
     * @param minimumValue Giá trị nhỏ nhất cho phép
     * @param maximumValue Giá trị lớn nhất cho phép
     * @return Số nguyên hợp lệ
     */
    public static int getInt(String promptMessage, String outOfRangeErrorMessage,
            String invalidNumberErrorMessage, int minimumValue, int maximumValue) {
        do {
            try {
                System.out.print(promptMessage);
                int parsedNumber = Integer.parseInt(SCANNER.nextLine().trim());
                if (parsedNumber >= minimumValue && parsedNumber <= maximumValue) {
                    return parsedNumber;
                } else {
                    System.out.println(outOfRangeErrorMessage);
                }
            } catch (NumberFormatException exception) {
                System.out.println(invalidNumberErrorMessage);
            }
        } while (true);
    }

    /**
     * Nhập số thực trong khoảng từ minimumValue đến maximumValue.
     * 
     * @param promptMessage Thông điệp nhắc người dùng nhập liệu
     * @param outOfRangeErrorMessage Thông báo lỗi khi giá trị nằm ngoài khoảng cho phép
     * @param invalidNumberErrorMessage Thông báo lỗi khi định dạng không phải là số thực
     * @param minimumValue Giá trị nhỏ nhất cho phép
     * @param maximumValue Giá trị lớn nhất cho phép
     * @return Số thực hợp lệ
     */
    public static double getDouble(String promptMessage, String outOfRangeErrorMessage,
            String invalidNumberErrorMessage, double minimumValue, double maximumValue) {
        do {
            try {
                System.out.print(promptMessage);
                double parsedNumber = Double.parseDouble(SCANNER.nextLine().trim());
                if (parsedNumber >= minimumValue && parsedNumber <= maximumValue) {
                    return parsedNumber;
                } else {
                    System.out.println(outOfRangeErrorMessage);
                }
            } catch (NumberFormatException exception) {
                System.out.println(invalidNumberErrorMessage);
            }
        } while (true);
    }

    /**
     * Nhập chuỗi ký tự khớp với biểu thức chính quy (regularExpression).
     * 
     * @param promptMessage Thông điệp nhắc người dùng nhập liệu
     * @param errorMessage Thông báo lỗi khi chuỗi nhập vào không khớp biểu thức chính quy
     * @param regularExpression Chuỗi biểu thức chính quy dùng để kiểm tra tính hợp lệ
     * @return Chuỗi ký tự hợp lệ đã loại bỏ khoảng trắng thừa ở hai đầu
     */
    public static String getString(String promptMessage, String errorMessage, final String regularExpression) {
        do {
            System.out.print(promptMessage);
            String inputString = SCANNER.nextLine().trim();
            if (inputString.matches(regularExpression)) {
                return inputString;
            }
            System.out.println(errorMessage);
        } while (true);
    }

    /**
     * Nhập ngày tháng theo định dạng datePattern và nằm trong khoảng từ minimumDate đến maximumDate.
     * 
     * @param promptMessage Thông điệp nhắc người dùng nhập liệu
     * @param outOfRangeErrorMessage Thông báo lỗi khi ngày nằm ngoài khoảng cho phép
     * @param invalidDateErrorMessage Thông báo lỗi khi định dạng ngày không hợp lệ
     * @param datePattern Định dạng ngày (ví dụ: dd/MM/yyyy)
     * @param minimumDate Ngày nhỏ nhất cho phép
     * @param maximumDate Ngày lớn nhất cho phép
     * @return Đối tượng Date hợp lệ
     */
    public static Date getDate(String promptMessage, String outOfRangeErrorMessage,
            String invalidDateErrorMessage, final String datePattern,
            Date minimumDate, Date maximumDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
        do {
            System.out.print(promptMessage);
            try {
                Date parsedDate = dateFormat.parse(SCANNER.nextLine().trim());
                if (parsedDate.compareTo(minimumDate) >= 0 && parsedDate.compareTo(maximumDate) <= 0) {
                    return parsedDate;
                }
                System.out.println(outOfRangeErrorMessage);
            } catch (ParseException exception) {
                System.out.println(invalidDateErrorMessage);
            }
        } while (true);
    }
}