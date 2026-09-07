package validator;

import java.util.Scanner;

/**
 * Lớp tiện ích kiểm tra dữ liệu đầu vào: Chỉ gồm 3 hàm cơ bản: getInt,
 * getDouble và getString (kiểm tra theo REGEX)
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Validator() {
    }

    /**
     *
     */
    public static int getInt(String messInfor, String messOutOfRange, String errorNum, int min, int max) {
        while (true) {
            try {
                System.out.println(messInfor);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messOutOfRange);
            } catch (Exception e) {
                System.out.println(errorNum);
            }
        }
    }

    public static double getDouble(String messInfor, String messOutOfRange, String errorNum, double min, double max) {
        while (true) {
            try {
                System.out.println(messInfor);
                double number = Double.parseDouble(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messOutOfRange);
            } catch (Exception e) {
                System.out.println(errorNum);
            }
        }
    }

    public static String getString(String messInfor, String messError, String REGEX) {
        while (true) {
            System.out.println(messError);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messError);
        }
    }
}
