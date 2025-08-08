package Lib;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

public class Lib {

    /**
     * Kiểm tra object có rỗng không (null, chuỗi trống, danh sách trống, mảng trống, map trống).
     *  @author Thang
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).toString().trim().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        return obj.toString().trim().isEmpty();
    }

    /**
     * Chuyển một chuỗi thành dạng viết hoa chữ cái đầu
     *  @author Thang
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Tạo một chuỗi UUID ngẫu nhiên
     *  @author Thang
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Chuyển đổi danh sách thành chuỗi ngăn cách bởi dấu phẩy
     *  @author Thang
     */
    public static String joinList(List<String> list, String delimiter) {
        if (list == null || list.isEmpty()) return "";
        return String.join(delimiter, list);
    }

    /**
     * Lấy ngày giờ hiện tại theo định dạng
     *  @author Thang
     */
    public static String getCurrentDateTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * Kiểm tra xem chuỗi có phải là email hợp lệ không
     *  @author Thang
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) return false;
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    /**
     * Kiểm tra xem chuỗi có phải là email hợp lệ không
     * @author Thang
     */
    public static boolean isValidPhoneNumber(String phone) {
        if (isEmpty(phone)) return false;
        return phone.matches("^0\\d{9}$"); // Bắt đầu bằng số 0 và có tổng cộng 10 chữ số
    }

    /**
     * Kiểm tra danh sách có chứa phần tử cụ thể không
     * @author Thang
     */
    public static boolean containsIgnoreCase(List<String> list, String item) {
        if (list == null || list.isEmpty() || item == null) return false;
        for (String str : list) {
            if (str.equalsIgnoreCase(item)) return true;
        }
        return false;
    }

    /**
     * Kiểm tra xem một chuỗi có chứa chỉ số hay không
     * @author Thang
     */
    public static boolean containsOnlyNumbers(String str) {
        return str.matches("\\d+");
    }

}
