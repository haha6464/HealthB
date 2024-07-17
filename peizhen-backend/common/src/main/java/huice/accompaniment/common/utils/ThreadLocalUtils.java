package huice.accompaniment.common.utils;

import huice.accompaniment.common.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/11 10:58
 */
public class ThreadLocalUtils {
    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<HashMap<String,Object>> LOCAL_MAP = new ThreadLocal<>();

    public static void add(String uid,String username){
        User userDO = new User();
        userDO.setUid(uid);
        userDO.setUsername(username);
        add(userDO);
    }

    public static void add(User user){
        THREAD_LOCAL.set(user);
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }

    public static Long getUid(){
        String uid = THREAD_LOCAL.get().getUid();
        return  Long.valueOf(uid);
    }

    public static String getUsername(){
        return THREAD_LOCAL.get().getUsername();
    }

    public static void put(String key,Object val){
        HashMap<String,Object> map = LOCAL_MAP.get()==null?new HashMap<>(): LOCAL_MAP.get();
        map.put(key,val);
        LOCAL_MAP.set(map);
    }

    public static Object get(String key){
        return LOCAL_MAP.get().get(key);
    }
}
