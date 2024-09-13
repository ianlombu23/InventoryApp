package com.woyo.ms_inventory.utils;

import com.woyo.ms_inventory.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
public class CommonUtil {

    public CommonUtil() {
    }

    public static String generateSerialNumber(String productName) {
        ZoneOffset zoneOffset = ZoneOffset.of("+07:00");
        long epochSecond = LocalDateTime.now().toEpochSecond(zoneOffset);

        String prefix = productName.substring(0, Math.min(productName.length(), 3)).toUpperCase();
        if (prefix.length() < 2) {
            prefix += "AB";
        } else if (prefix.length() < 3) {
            prefix += "A";
        }

        return prefix + epochSecond;
    }

    public static boolean isValidMimeType(MultipartFile file) {
        List<String> allowedMimeTypes = Arrays.asList("image/jpeg", "image/png");

        if (file == null || file.isEmpty()) return false;

        String mimeType = file.getContentType();
        return allowedMimeTypes.contains(mimeType);
    }

    public static String convertToBase64(MultipartFile file) {
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());
        } catch (Exception e) {
            log.info("error when convert image, {}", e.getMessage() );
            throw new CustomException(
                    "3000",
                    "Something error when convert image",
                    HttpStatus.CONFLICT
            );
        }
    }
}
