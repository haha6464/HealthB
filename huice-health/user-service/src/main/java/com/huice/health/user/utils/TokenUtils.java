package com.huice.health.user.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.huice.health.common.utils.ThreadLocalUtils;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/11 13:25
 */
public class TokenUtils {

    public static String getToken(String uid, String username) {
        String loginId = uid;
        StpUtil.login(loginId);
        return StpUtil.getTokenValueByLoginId(loginId);
    }

    public static void logout(String id, String username) {
        StpUtil.logout(id + "|" + username);
    }

    public static void logout() {
        logout(String.valueOf(ThreadLocalUtils.getUid()), ThreadLocalUtils.getUsername());
    }
}
