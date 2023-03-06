package shop.mtcoding.jobara.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.http.HttpStatus;

import shop.mtcoding.jobara.common.ex.CustomException;

public class Hash {
      public static String encode(String password) {
            try {
                  MessageDigest digest = MessageDigest.getInstance("SHA-256");
                  byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                  StringBuilder hexString = new StringBuilder();
                  for (byte b : hash) {
                        String hex = Integer.toHexString(0xff & b);
                        if (hex.length() == 1)
                              hexString.append('0');
                        hexString.append(hex);
                  }
                  return hexString.toString();
            } catch (Exception e) {
                  throw new CustomException("서버 오류 : 암호화 실패", HttpStatus.INTERNAL_SERVER_ERROR);
            }
      }

      public static String makeSalt() {
            try {
                  SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                  byte[] bytes = new byte[16];
                  random.nextBytes(bytes);
                  String salt = new String(Base64.getEncoder().encode(bytes));
                  return salt;
            } catch (Exception e) {
                  throw new CustomException("서버 오류 : 암호화 실패", HttpStatus.INTERNAL_SERVER_ERROR);
            }
      }
}