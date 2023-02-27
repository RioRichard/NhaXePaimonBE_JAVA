package com.paimon.QLBanVePaimon;

import java.nio.charset.StandardCharsets;

import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.hash.Hashing;

public class Helper {

    public static int minMaxOrNum(Integer num, int min, int max) {
        if (num == null) {
            return min;
        }
        if (num < min) {
            return min;
        }
        if (num > max) {
            return max;
        }
        return num.intValue();

    }

    public static int minOrNum(Integer num, int min) {
        if (num == null) {
            return min;
        }
        if (num < min) {
            return min;
        }
        return num.intValue();

    }

    public static int maxOrNum(Integer num, int max) {
        if (num == null) {
            return max;
        }
        if (num > max) {
            return max;
        }
        return num.intValue();

    }

    public static String getUriPagination(UriComponentsBuilder uBuilder, int page) {
        return uBuilder.replaceQueryParam("page", page).build().encode().toUriString();
    }

    public static String hash256(String plainString) {
        return Hashing.sha256()
                .hashString(plainString, StandardCharsets.UTF_8)
                .toString();
    }

}
