package com.dochub.utils;
import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Utils {

    public enum ReceiveOtpMethod {
        EMAIL(1, "Email"),
        SMS(2, "Sms"),
        NONE(-1, "Không có");

        private final int code;
        private final String description;

        ReceiveOtpMethod(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }
        public String getDescription() {
            return description;
        }

        // Tìm enum theo code
        public static ReceiveOtpMethod fromCode(int code) {
            for (ReceiveOtpMethod method : values()) {
                if (method.code == code) {
                    return method;
                }
            }
            throw new IllegalArgumentException("Không tìm thấy phương thức OTP với code: " + code);
        }

        @Override
        public String toString() {
            return description;
        }
    }


    public enum ResultCode {
        DEFAULT(0),
        NEED_OTP_CONFIRMATION(100),
        UNAUTHORIZED(401);

        private final long code;

        ResultCode(long code) {
            this.code = code;
        }

        public long getCode() {
            return code;
        }

        public static ResultCode fromCode(int code) {
            for (ResultCode result : ResultCode.values()) {
                if (result.code == code) {
                    return result;
                }
            }
            return DEFAULT; // Giá trị mặc định
        }
    }

    public enum ConsoleColor {
        RESET("\033[0m"),
        YELLOW("\033[0;33m"),
        RED("\033[0;31m"),
        GREEN("\033[0;32m"),
        CYAN("\033[0;36m"),
        BLUE("\033[0;34m");  // Thêm màu xanh da trời

        private final String code;

        ConsoleColor(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
    public static void ConsoleWriteLine(String message, ConsoleColor color) {
        System.out.print(color.getCode());
        System.out.println(message);
        System.out.print(ConsoleColor.RESET.getCode());

        try {
            TimeUnit.SECONDS.sleep(1); // Dừng chương trình 1 giây
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("Please enter information OTP (finish entering with [ENTER]): ");
            input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Value cannot be empty. Please re-enter OTP.");
            }

        } while (input.trim().isEmpty());

        return input;
    }
}