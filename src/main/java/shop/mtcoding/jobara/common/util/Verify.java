package shop.mtcoding.jobara.common.util;

import org.springframework.http.HttpStatus;

import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.user.vo.UserVo;

public class Verify {

    public static void validateString(String target, String msg) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg);
        }
    }

    public static void validateString(String target, String msg, HttpStatus status) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg, status);
        }
    }

    public static void validateString(String target, String msg, HttpStatus status, String location) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg, status, location);
        }
    }

    public static void validateObject(Object target, String msg) {
        if (target == null) {
            throw new CustomException(msg);
        }
    }

    public static void validateObject(Object target, String msg, HttpStatus status) {
        if (target == null) {
            throw new CustomException(msg, status);
        }
    }

    public static void validateObject(Object target, String msg, HttpStatus status, String location) {
        if (target == null) {
            throw new CustomException(msg, status, location);
        }
    }

    public static void validateApiString(String target, String msg) {
        if (target == null || target.isEmpty()) {
            throw new CustomApiException(msg);
        }
    }

    public static void validateApiString(String target, String msg, HttpStatus status) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg, status);
        }
    }

    public static void validateApiObject(Object target, String msg) {
        if (target == null) {
            throw new CustomApiException(msg);
        }
    }

    public static void validateApiObject(Object target, String msg, HttpStatus status) {
        if (target == null) {
            throw new CustomApiException(msg, status);
        }
    }

    public static void checkRole(UserVo principal, String role) {
        if (!principal.getRole().equals(role)) {
            throw new CustomException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }

    public static void checkRoleApi(UserVo principal, String role) {
        if (!principal.getRole().equals(role)) {
            throw new CustomApiException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }

    public static void isNotEqual(Object target1, Object target2, String msg, HttpStatus state) {
        if (target1 != target2) {
            throw new CustomException(msg, state);
        }
    }

    public static void isNotEqualApi(Object target1, Object target2, String msg, HttpStatus state) {
        if (target1 != target2) {
            throw new CustomApiException(msg, state);
        }
    }

    public static void isEqual(Object target1, Object target2, String msg, HttpStatus state) {
        if (target1 == target2) {
            throw new CustomException(msg, state);
        }
    }

    public static void isEqualApi(Object target1, Object target2, String msg, HttpStatus state) {
        if (target1 == target2) {
            throw new CustomApiException(msg, state);
        }
    }

    public static void isStringEquals(String target1, String target2, String msg, HttpStatus state) {
        if (target1.equals(target2)) {
            throw new CustomException(msg, state);
        }
    }

    public static void isStringEqualsApi(String target1, String target2, String msg, HttpStatus state) {
        if (target1.equals(target2)) {
            throw new CustomApiException(msg, state);
        }
    }
}
