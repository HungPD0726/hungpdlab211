package utils;

import java.util.Scanner;

/**
 * Lớp tiện ích kiểm tra và chuẩn hóa dữ liệu đầu vào từ bàn phím.
 *
 * @author Administrator
 */
public final class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {
    }

    /**
     * Nhập số nguyên trong khoảng từ minimumValue đến maximumValue.
     *
     * @param promptMessage Thông điệp nhắc người dùng nhập liệu
     * @param outOfRangeErrorMessage Thông báo lỗi khi giá trị nằm ngoài khoảng
     * @param invalidNumberErrorMessage Thông báo lỗi khi định dạng không phải số
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
                }
                System.out.println(outOfRangeErrorMessage);
            } catch (NumberFormatException exception) {
                System.out.println(invalidNumberErrorMessage);
            }
        } while (true);
    }

    /**
     * Nhập chuỗi khớp với biểu thức chính quy (regularExpression).
     *
     * @param promptMessage Thông điệp nhắc người dùng nhập liệu
     * @param errorMessage Thông báo lỗi khi chuỗi không khớp regex
     * @param regularExpression Chuỗi regex dùng để kiểm tra tính hợp lệ
     * @return Chuỗi hợp lệ đã loại bỏ khoảng trắng thừa
     */
    public static String getString(String promptMessage, String errorMessage,
            String regularExpression) {
        do {
            System.out.print(promptMessage);
            String inputString = SCANNER.nextLine().trim();
            if (inputString.matches(regularExpression)) {
                return inputString;
            }
            System.out.println(errorMessage);
        } while (true);
    }
}
